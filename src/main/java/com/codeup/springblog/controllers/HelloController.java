package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String helloFromSpring(){
        return "Hello from the world of Spring Boot.";
    }

    @GetMapping("/helloWorld/{username}")
    @ResponseBody
    public String helloWorld(@PathVariable String username){
        return "Hello "+ username +"!";
    }

    @GetMapping("/happy_birthday/{username}/{age}")
    public String helloWorld(
            @PathVariable String username,
            @PathVariable int age,
            Model model
    ){
        model.addAttribute("name", username);
        model.addAttribute("age", age);

        return "happy_birthday";
    }

    @GetMapping("/random/number")
    public String RandomNumber(Model model){
        Random r = new Random();
        int low = 1;
        int high = 100;
        int randomNumber = r.nextInt(high-low) + low;
        model.addAttribute("num",randomNumber);
        return "random";

    }

    @GetMapping("/weather")
    public String viewWeather(Model model){
        String[] weatherInfo = {"Hot", "Humid", "Cloudy", "Hazy","98F"};

        model.addAttribute("weatherStats",weatherInfo);
        return "WeatherPage";
    }



}