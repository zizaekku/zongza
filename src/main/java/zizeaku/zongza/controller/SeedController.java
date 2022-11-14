package zizeaku.zongza.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/list")
    public String list(Model model) {
        List<Seed> seeds = seedService.findAllSeeds();
        model.addAttribute("seeds", seeds);
        return "seeds/seedList";
    }

    @GetMapping("/detail")
    public String paramTest(@RequestParam("seedId") Long seedId, Model model) {
        Seed seed = seedService.findSeed(seedId);
        System.out.println("여기여기여기");
        System.out.println(seed);
        model.addAttribute("seedId", seedId);
        model.addAttribute("seed", seed);
        return "seeds/detail";
    }

    // @RequestMapping(value = "/read", method = RequestMethod.GET) // GET 방식으로 페이지 호출
	//   public void read(@RequestParam("bno")int bno, Model model) throws Exception{
	// 	  // 인자값은 파라미터 값으로 기본키인 글번호를 기준으로 Model을 사용하여 불러옴
	// 	 model.addAttribute(seedService.read(bno)); // read 서비스 호출
	//   }
}
