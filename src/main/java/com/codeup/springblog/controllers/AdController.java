package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;

import com.codeup.springblog.repos.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdController {

    private final AdRepository adDao;

    public AdController(AdRepository adDao) {
        this.adDao = adDao;
    }

    @GetMapping("/ads")
    public String showAds(Model model) {

        List<Ad> adsToShow = adDao.findAll();

        model.addAttribute("ads", adsToShow);
        return "ad/index";
    }

    @GetMapping("/ad/{id}")
    public String showOneAd(@PathVariable int id, Model model) {

        Ad ad = new Ad("Fun title", "Fun body");
        model.addAttribute("adsId", id);
        model.addAttribute("ads", ad);

        return "ad/show";
    }


    @ResponseBody
    public String showCreateAdForm() {
        return "ad/create";
    }


   @PostMapping
    public String createAd(@RequestParam(name = "title") String title,
                           @RequestParam(name = "description") String description
   ) {
      Ad adToSubmitToDB = new Ad(title, description);
      adDao.save(adToSubmitToDB);

      return"redirect:/ads";
    }
}
