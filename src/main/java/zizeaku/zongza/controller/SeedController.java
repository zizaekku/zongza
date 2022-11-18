package zizeaku.zongza.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import zizeaku.zongza.domain.Generic;
import zizeaku.zongza.domain.Seed;
import zizeaku.zongza.service.GenericService;
import zizeaku.zongza.service.SeedService;

@Controller
@RequestMapping(value = "/seeds")
public class SeedController {
    private final SeedService seedService;
    private final GenericService genericService;
    
    public SeedController(SeedService seedService, GenericService genericService) {
        this.seedService = seedService;
        this.genericService = genericService;
    }

    @GetMapping("/new")
    public String createForm() {
        return "seeds/createSeedForm";
    }

    @PostMapping("/new")
    public String create(SeedForm form) {
        System.out.println(form);
        System.out.println(form.getGeneric());
        Optional<Generic> generic = genericService.findGeneric(Long.parseLong(form.getGeneric()));
        System.out.println("===========");
        System.out.println(generic);
        Seed seed = new Seed();
        seed.setName(form.getName());
        seed.setScientificName(form.getScientificName());
        seed.setIntroNum(form.getIntroNum());
        // seed.setGeneric(form.getGeneric());
        seed.setGeneric(generic.get());
        seedService.join(seed);
        return "redirect:/seeds/list";
    }

    @GetMapping("/list")
    public String list(Model model) {
        Iterable<Seed> seeds = seedService.findAllSeeds();
        model.addAttribute("seeds", seeds);
        // System.out.println(seeds.getClass().getField(null)));
        return "seeds/seedList";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("seedId") Long seedId, Model model) {
        Optional<Seed> seed = seedService.findSeed(seedId);
        model.addAttribute("seedId", seedId);
        model.addAttribute("seed", seed.get());
        return "seeds/detail";
    }

    @GetMapping("/update")
    public String update(@RequestParam("seedId") Long seedId, Model model) {
        Optional<Seed> seed = seedService.findSeed(seedId);
        model.addAttribute("seedId", seedId);
        model.addAttribute("seed", seed.get());
        return "seeds/update";
    }

    @PostMapping("/update")
    public String updateSeed(@RequestParam("seedId") Long seedId, Model model, SeedForm form) {
        Optional<Seed> seed = seedService.findSeed(seedId);
        seed.get().setName(form.getName());
        seed.get().setScientificName(form.getScientificName());
        seed.get().setIntroNum(form.getIntroNum());
        seedService.join(seed.get());
        return "redirect:/seeds/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("seedId") Long seedId) {
        seedService.deleteSeed(seedId);
        return "redirect:/seeds/list";
    }

}
