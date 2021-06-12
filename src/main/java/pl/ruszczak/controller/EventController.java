package pl.ruszczak.controller;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.joran.conditional.ElseAction;
import pl.ruszczak.UserService;
import pl.ruszczak.model.Bill;
import pl.ruszczak.model.Event;
import pl.ruszczak.model.Payment;
import pl.ruszczak.model.User;
import pl.ruszczak.repository.EventRepository;
import pl.ruszczak.repository.UserRepository;
import pl.ruszczak.repository.PaymentRepository;

import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {
    private final EventRepository eventRepository;
    private final UserRepository  userRepository;
    private final UserService service;
    private final PaymentRepository paymentRepository;
    
    @Autowired
    public EventController(EventRepository eventRepository, UserService service, UserRepository rep,
        PaymentRepository p) {
        this.eventRepository = eventRepository;
        this.service = service;
        this.userRepository = rep;
        this.paymentRepository = p;
    }

    @GetMapping("/add")
    public String addEvent(Model model){
        Event event = new Event();
        model.addAttribute("event", event);
        return "addEventForm";
    }
    
    @PostMapping("/add")
    public String addEvent(Model model, @ModelAttribute("event") Event event) {
        event.setCreator(service.getCurrentUser());
        event.setStatus("NOWY");
        eventRepository.save(event);

        return "addEventForm";
    }

    @GetMapping("/signin/{id}")
    public String signIn(@PathVariable("id") Long id) {
        Event e = eventRepository.findById(id).get();
        e.getParticipants().add(service.getCurrentUser());

        eventRepository.save(e);
        return "redirect:/event/list";
    }

    @GetMapping("/list")
    public String eventList(Model model){
        List<Event> eventList = eventRepository.findAll();

        eventList.stream().forEach(e -> {
            e.setParticipant(e.getParticipants().contains(service.getCurrentUser()) || e.getCreator().equals(service.getCurrentUser()));
        });

        model.addAttribute("events", eventList);
        return "eventList";
    }

    @GetMapping("/{id}")
    public String eventInfo(@PathVariable("id") Long id, Model model) {
    Event event = eventRepository.findById(id).get();
    if (event.getCreator().equals(service.getCurrentUser()) || event.getParticipants().contains(service.getCurrentUser()))
        event.setParticipant(true);

    event.setCloseable(
        event.getCreator().equals(service.getCurrentUser()) &&
        event.getStatus().equals("NOWY"));
    model.addAttribute("event", event);
    model.addAttribute("currentUser", service.getCurrentUser());

    return "eventInfo";
    }

    @GetMapping("/close/{id}")
    public String closeEvent(@PathVariable("id") Long id, Model model) {
    Event event = eventRepository.findById(id).get();
    event.setStatus("ZAMKNIETY");
    eventRepository.save(event);

    ArrayList<User> allParticipants = new ArrayList<User>();
    allParticipants.add(event.getCreator());
    allParticipants.addAll(event.getParticipants());

    double divide = allParticipants.size();

    HashMap<String, Double> map = new HashMap<String, Double>();

    for (Bill b: event.getBills())
    {
        String creatorName = b.getCreator().getUsername();
        Double amount = b.getAmount()/divide;
        for (User p: allParticipants)
        {
            if (!p.equals(b.getCreator()))
            {
                Double tempAmount = map.getOrDefault(creatorName + ";" + p.getUsername(), 0D);
                map.put(creatorName + ";" + p.getUsername(), tempAmount + amount);
            }
        }

    }

    HashSet<String> reversedChecked = new HashSet<String>();
    for (String key : map.keySet()) {
        if (reversedChecked.contains(key))
            continue;

        String [] splitted = key.split(";");
        String payer = splitted[0];
        String payee = splitted[1];

        Double amount = map.get(key);
        Double reversedAmout = map.getOrDefault(payee + ";" + payer, 0D);
        reversedChecked.add(payee + ";" + payer);

        if (amount > reversedAmout)
        {
            Payment p = new Payment();
            p.setFrom(userRepository.findByUsername(payee));
            p.setTo(userRepository.findByUsername(payer));
            p.setAmount(amount - reversedAmout);
            paymentRepository.save(p);
            event.getPayments().add(p);
            eventRepository.save(event);
        }
        else
        {
            Payment p = new Payment();
            p.setFrom(userRepository.findByUsername(payer));
            p.setTo(userRepository.findByUsername(payee));
            p.setAmount(reversedAmout - amount);
            paymentRepository.save(p);
            event.getPayments().add(p);
            eventRepository.save(event);
        }
    }

        return "redirect:/event/" + id;
    }

}


