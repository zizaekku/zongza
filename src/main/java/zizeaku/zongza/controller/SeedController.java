package zizeaku.zongza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SeedController {
    @GetMapping("/seeds/new")
    public String createForm() {
        return "seeds/createSeedForm";
    }

    // @PostMapping("/seeds/new")
    // public String create(SeedForm form) {
    //     Seed seed = new 
    // }
}
