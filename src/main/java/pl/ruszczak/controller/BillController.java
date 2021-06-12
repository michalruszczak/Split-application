package pl.ruszczak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.ruszczak.UserService;
import pl.ruszczak.model.Bill;
import pl.ruszczak.model.Event;
import pl.ruszczak.model.Payment;
import pl.ruszczak.repository.BillRepository;
import pl.ruszczak.repository.EventRepository;
import pl.ruszczak.repository.PaymentRepository;

@Controller
@RequestMapping("/bill")
public class BillController {
    private final EventRepository eventRepository;
    private final UserService service;
    private final BillRepository billRepository;
    private final PaymentRepository paymentRepository;
    
    @Autowired
    public BillController(EventRepository eventRepository, UserService service, BillRepository billRepository,
        PaymentRepository p) {
        this.eventRepository = eventRepository;
        this.service = service;
        this.billRepository = billRepository;
        this.paymentRepository = p;
    }

    @GetMapping("/add/{event_id}")
    public String addBill(@PathVariable("event_id") Long id,  Model model) {
        model.addAttribute("bill", new Bill());

        return "addBillForm";
    }
    
    @PostMapping("/add/{event_id}")
    public String addBill(@PathVariable("event_id") Long id, @ModelAttribute("bill") Bill bill) {
        Event e = eventRepository.findById(id).get();
        bill.setCreator(service.getCurrentUser());
        billRepository.save(bill);
        e.getBills().add(bill);
        eventRepository.save(e);
        return "redirect:/event/" + id;
    }


}
