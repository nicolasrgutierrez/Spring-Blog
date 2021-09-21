package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice(Model model) {
        return "rolldice";
    }
    @GetMapping("/roll-dice/{num}")
    public String rollDiceResponse(@PathVariable int num, Model model) {
        model.addAttribute("number", num);
        Random random = new Random();
        int randomNum = random.nextInt(6 - 1 + 1) + 1;
        model.addAttribute("correct", randomNum);
        if (randomNum == num) {
            model.addAttribute("goodguess");
        } else {
            model.addAttribute("badguess");
        }
        return "roll_dice_guess";
    }
}
