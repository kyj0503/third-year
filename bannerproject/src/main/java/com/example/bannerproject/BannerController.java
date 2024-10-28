package com.example.bannerproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BannerController {
    @Autowired
    BannerRepository bannerRepository;

    @GetMapping("/banner")
    public String banner(Model model) {
        List<Banner> banners = new ArrayList<>() {{
            add(new Banner("다음", "/img/Daum.png", "http://www.daum.net"));
            add(new Banner("google", "/img/Google.png", "http://google.co.kr"));
        }};
        bannerRepository.saveAll(banners);
        List<Banner> savedBanner = bannerRepository.findAll();
        model.addAttribute("banners", savedBanner);
        return "banner";
    }
}
