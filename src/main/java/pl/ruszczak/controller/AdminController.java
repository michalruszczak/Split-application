package pl.ruszczak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/test")
    @ResponseBody
    public String test(Model model){
        return "test";
    }
}
