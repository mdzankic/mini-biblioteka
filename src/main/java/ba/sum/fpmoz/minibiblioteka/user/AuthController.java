package ba.sum.fpmoz.minibiblioteka.user;

import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

record RegisterRequest(@NotBlank String username, @NotBlank String password) {}

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) { this.userService = userService; }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        userService.register(req.username(), req.password());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(java.security.Principal principal) {
        return ResponseEntity.ok(principal == null ? "anonymous" : principal.getName());
    }
}
