package com.SpringApp.SecondSpringApp.controllers;


import com.SpringApp.SecondSpringApp.models.Users;
import com.SpringApp.SecondSpringApp.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/blog")
    public String blog(Model model){
        Iterable<Users> users = usersRepository.findAll();
        model.addAttribute("users",users);
        return "blogController";
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

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") Long id, Model model){
        if(!usersRepository.existsById(id)) return "redirect:/blog";
       Optional<Users> user = usersRepository.findById(id);
        ArrayList<Users> res = new ArrayList<>();
        user.ifPresent(res::add);
       model.addAttribute("state",res);
       return "blog-details";
    }
}
