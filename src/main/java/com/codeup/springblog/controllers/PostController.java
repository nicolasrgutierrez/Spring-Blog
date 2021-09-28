package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String indexPage(Model model) {
        List<Post> allPosts = postDao.findAll();
        model.addAttribute("posts", allPosts);
        return "post/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String viewPost(@PathVariable long id, Model model) {
        Post post = postDao.getById(id);
        model.addAttribute("post", post);
        return "post/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }
    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(@ModelAttribute Post post) {
        post.setOwner(userDao.getById(1L));
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Post post = postDao.getById(id);
        model.addAttribute("post", post);
        return "/post/edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String editPost(@ModelAttribute Post post) {
        postDao.save(post);

        return "redirect:/posts";
    }

    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id) {
        Post postToDelete = postDao.getById(id);
        postDao.delete(postToDelete);

        return "redirect:/posts";
    }

}
