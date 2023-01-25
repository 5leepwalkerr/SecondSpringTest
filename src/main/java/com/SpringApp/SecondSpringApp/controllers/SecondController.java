package com.SpringApp.SecondSpringApp.controllers;


import com.SpringApp.SecondSpringApp.models.Users;
import com.SpringApp.SecondSpringApp.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecondController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/blog")
    public String blog(Model model){
        Iterable<Users> users = usersRepository.findAll();
        model.addAttribute("users",users);
        return "secondPage";
    }
    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model){
        Users user = new Users(title,anons,full_text);
        usersRepository.save(user);
        return "redirect:/blog";
    }
}
