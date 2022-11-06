package zizeaku.zongza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
public class ErrorHandleController {
    
    @GetMapping("/401")
    public String get401() {
        return "401";
    }

    @GetMapping("/404")
    public String get404() {
        return "404";
    }
    
    @GetMapping("/500")
    public String get500() {
        return "500";
    }
}
