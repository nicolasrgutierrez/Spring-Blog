package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
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
        model.addAttribute("postId", id);
        model.addAttribute("post", post);
        return "post/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String createForm(Model model) {
        return "post/create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String createPost(@PathVariable String title, @PathVariable String body, Model model) {
        Post post = new Post(title, body);
        postDao.save(post);
        return "post/index";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable long id, Model model) {
        Post postToEdit = postDao.getById(id);
        model.addAttribute("post", postToEdit);
        return "post/edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String editPost(
            @PathVariable long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
    ) {
        Post editedPost = new Post(id, title, body);
        postDao.save(editedPost);

        return "redirect:/posts";
    }

//    @PostMapping("/posts/delete/{id}")
//    public String deletePost(@PathVariable long id) {
//        Post postToDelete = new Post(id);
//        postDao.save(editedPost);
//
//        return "redirect:/posts";
//    }

}
