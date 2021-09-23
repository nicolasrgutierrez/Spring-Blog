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
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{num}")
    public String rollDiceGuess(@PathVariable int num, Model model) {
        model.addAttribute("number", num);
        Random random = new Random();
        int randomNum = random.nextInt((6 - 1) + 1) + 1;
        model.addAttribute("diceRoll", randomNum);
        model.addAttribute("userGuess", num);
        model.addAttribute("isCorrect", randomNum == num);
        return "roll-dice";
    }
}
