package ba.sum.fpmoz.minibiblioteka.web;

import ba.sum.fpmoz.minibiblioteka.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthMvcController {

    private final UserService userService;

    public AuthMvcController(UserService userService) {
        this.userService = userService;
    }

    // Registracija preko HTML forme (x-www-form-urlencoded)
    @PostMapping("/register")
    public String register(
            @RequestParam String username,
            @RequestParam String password
    ) {
        userService.register(username, password); // isti servis kao za REST
        return "redirect:/login?registered";
    }
}
