package Spike.springboot.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("username", "kim");
        return "greetings";
    }

    @GetMapping("/bye")
    public String bye(Model model) {
        model.addAttribute("username", "kim");
        return "goodbye";
    }
}
