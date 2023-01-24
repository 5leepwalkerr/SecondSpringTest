package com.SpringApp.SecondSpringApp.controllers;


import com.SpringApp.SecondSpringApp.models.Users;
import com.SpringApp.SecondSpringApp.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
