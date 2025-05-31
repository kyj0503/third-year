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
            add(new Banner("다음", "daum","http://www.daum.com"));
            add(new Banner("google", "google.jpg","http://google.co.kr"));
        }};
        bannerRepository.saveAll(banners);

        List<Banner> saveBanners = bannerRepository.findAll();

        model.addAttribute("banners", saveBanners);
        return "banner";
    }
}
