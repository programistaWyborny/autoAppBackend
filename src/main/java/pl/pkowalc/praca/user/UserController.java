package pl.pkowalc.praca.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    @GetMapping("/login")
    void login() {
    }

    @PostMapping("/register")
    void register(@RequestBody RegisterUserRequest request) {
        userService.register(request);
    }

}
