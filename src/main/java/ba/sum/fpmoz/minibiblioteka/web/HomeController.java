package ba.sum.fpmoz.minibiblioteka.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Nakon logina vodi na listu knjiga (ili registraciju ako si anonimna)
        return "redirect:/books";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }
}
