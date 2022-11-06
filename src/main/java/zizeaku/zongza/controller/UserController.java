package zizeaku.zongza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/password")
    public String password() {
        return "password";
    }

    @GetMapping("tables")
    public String tables() {
        return "tables";
    }
}
