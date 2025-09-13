package ba.sum.fpmoz.minibiblioteka.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        // samo vraća Thymeleaf template "login.html"
        return "login";
    }
}
