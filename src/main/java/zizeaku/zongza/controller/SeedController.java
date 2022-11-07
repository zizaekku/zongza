package zizeaku.zongza.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import zizeaku.zongza.domain.Seed;
import zizeaku.zongza.service.SeedService;

@Controller
@RequestMapping(value = "/seeds")
public class SeedController {
    private final SeedService seedService;
    
    public SeedController(SeedService seedService) {
        this.seedService = seedService;
    }

    @GetMapping("/new")
    public String createForm() {
        return "seeds/createSeedForm";
    }

    @PostMapping("/new")
    public String create(SeedForm form) {
        Seed seed = new Seed();
        seed.setName(form.getName());
        seed.setScientificName(form.getScientificName());
        seed.setIntroNum(form.getIntroNum());
        seedService.join(seed);
        return "redirect:/seeds/new";
    }
}
