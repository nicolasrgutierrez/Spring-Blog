package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class HelloController {

    //Get Mapping Similar to doGet in a Servlet
//    @GetMapping("/")
//    @ResponseBody
//    public String helloFromSpring(){
//        return "hello from the world of Spring Boot!";
//    }

    @GetMapping("/helloWorld/{username}")
    @ResponseBody
    public String helloWorld(@PathVariable String username){
        return "hello "+ username + "!";
    }

    @GetMapping("/happy_birthday/{username}/{age}")
    @ResponseBody
    public String helloWorld(
            @PathVariable String username,
            @PathVariable int age
    ){
        return "Happy Birthday "+ username +  "You are now " + age + " years old!";
    }

//    @GetMapping("/random/number")
//    @ResponseBody
//    public String RandomNumber(
//            Random r = new Random()
//            int low = 10;
//            int high = 100;
//            return r.nextInt(high-low) + low;
//    )
}
