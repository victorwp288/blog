package com.example.blog.Controllers;

import com.example.blog.Models.Post;
import com.example.blog.Models.User;
import com.example.blog.Services.PostService;
import com.example.blog.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @GetMapping("/")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/")
    public String loginpage(@ModelAttribute User user, Model model) {
        User current = userService.authenticate(user.getUsername(), user.getPassword());
        if (current != null) {
            model.addAttribute("current", current);
            return "redirect:/home/" + current.getId();
        } else {
            return "login";
        }
    }

        @GetMapping("/home/{userId}")
        public String Home(Model model, @PathVariable int userId) {

            User user = userService.findById(userId);
            model.addAttribute("user", user);

            log.info("Home hentes i: " + this.getClass());
            List<Post> post = postService.fetchAllPost();
            model.addAttribute("post", post);

            return "home";

        }

        @GetMapping("home/subs/{userId}")
        public String subs(Model model, @PathVariable int userId){
            log.info("Getting user list in: " +  this.getClass());

            User user = userService.findById(userId);
            model.addAttribute("user", user);

            List<User> users = userService.fetchAllUsers();
            model.addAttribute("subs", users);

            return "subs";
        }

    }
