package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    @Autowired
    private UserRepository userDao;

    @GetMapping("/user/create")
    public String createUserForm(Model model){
        model.addAttribute("user", new User());
        return "user/create";
    }


    @PostMapping("/user/create")
    @ResponseBody
    public String createUser(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/posts";
    }

    // Anything that has to do with users will go in here.

}


