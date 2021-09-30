package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public UsersController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

//    @GetMapping("/user/create")
//    public String createUserForm(Model model){
//        model.addAttribute("user", new User());
//        return "user/create";
//    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "user/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/user/{username}/posts")
    public String showUserPosts(
            @PathVariable String username,
            Model model
    ){
        User userToDisplay = userDao.getByUsername(username);

        model.addAttribute("user",userToDisplay);

        return "user/displayPosts";
    }

//    @PostMapping("/user/create")
//    @ResponseBody
//    public String createUser(@ModelAttribute User user){
//        userDao.save(user);
//        return "redirect:/posts";
//    }

    // Anything that has to do with users will go in here.

}


