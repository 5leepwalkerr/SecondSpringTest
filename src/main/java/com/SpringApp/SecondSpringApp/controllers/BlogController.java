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
    } //просто переход по ссылке

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,@RequestParam String anons,@RequestParam String full_text, Model model){
        Users user = new Users(title,anons,full_text);
        usersRepository.save(user);
        return "redirect:/blog"; //тут происходит запись значений в бд
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") Long id, Model model){
        if(!usersRepository.existsById(id)) return "redirect:/blog";
       Optional<Users> user = usersRepository.findById(id); // Optional сохраняет ссылки на объекты, потом мы эти ссылки сохраняем в лист
        ArrayList<Users> res = new ArrayList<>();
        user.ifPresent(res::add);
       model.addAttribute("state",res); //state объект класса Users
       return "blog-details";
    }
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") Long id, Model model){
        if(!usersRepository.existsById(id)) return "redirect:/blog";
        Optional<Users> user = usersRepository.findById(id); // Optional сохраняет ссылки на объекты, потом мы эти ссылки сохраняем в лист
        ArrayList<Users> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("state",res); //state объект класса Users
        return "blog-edit";
    }
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id")long id, @RequestParam String title,@RequestParam String anons,@RequestParam String full_text,Model model){
        Users user = usersRepository.findById(id).orElseThrow();
        user.setTitle(title);
        user.setAnons(anons);
        user.setFullText(full_text);
        usersRepository.save(user);
        return "redirect:/blog"; //тут происходит запись значений в бд
    }


    @PostMapping("/blog/{id}/remove")
    public String blogPostRemove(@PathVariable(value = "id")long id, Model model){
        Users user = usersRepository.findById(id).orElseThrow();
        usersRepository.delete(user);
        return "redirect:/blog"; //тут происходит запись значений в бд
    }
}
