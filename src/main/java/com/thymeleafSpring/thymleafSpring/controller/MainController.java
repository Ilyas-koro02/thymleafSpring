package com.thymeleafSpring.thymleafSpring.controller;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.thymeleafSpring.thymleafSpring.entity.Admin;
import com.thymeleafSpring.thymleafSpring.entity.Users;
import com.thymeleafSpring.thymleafSpring.repository.AdminRepo;
import com.thymeleafSpring.thymleafSpring.repository.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.function.Supplier;

@Controller
public class MainController {

    private final UserRepo userRepo;
    private final AdminRepo adminRepo;
    private Admin admin123;
    private Boolean ddd;
    private int id;

    public MainController(UserRepo userRepo, AdminRepo adminRepo) {
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
    }

//    @RequestMapping("/")
//    public String firstPage(@RequestParam("fName") String fName, Model model){
//        model.addAttribute("title1", fName);
////        model.addAttribute("title2", lName);
//        model.addAttribute("users", userRepo.findAll());
//        return "/index";
//    }

//    @GetMapping("/userss")
//    public String greetingForm(Model model) {
//        model.addAttribute("userss", new Users());
//        return "index";
//    }

    @PostMapping("/")
    public String greetingSubmit(@ModelAttribute Users users, Model model, HttpServletResponse response) {
        model.addAttribute("users", users);
        userRepo.save(users);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginAu(@ModelAttribute Admin admin, Model model, HttpServletResponse response){
        model.addAttribute("admin", admin);
        if (Optional.ofNullable(adminRepo.getByLoginAndPasswordAdmin(admin.getLogin(), admin.getPasswordAdmin())).isPresent()){
            Cookie theCookie = new Cookie("Name", "koro");
            theCookie.setMaxAge(60*60*24);
            response.addCookie(theCookie);
            admin123 = adminRepo.getByLoginAndPasswordAdmin(admin.getLogin(), admin.getPasswordAdmin());
            return "redirect:/";
        }else {
            model.addAttribute("errorLogin", "error");
            return "login";
        }
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/login")
    public String loginAut(Model model, HttpServletRequest request, HttpServletResponse response){
        Arrays.asList(Optional.ofNullable(request.getCookies()).orElseGet(() -> new Cookie[0])).forEach(cookie -> { cookie.setMaxAge(0); response.addCookie(cookie); });
        model.addAttribute("admin", new Admin());
        return "login.html";
    }

    @GetMapping("/")
    public String loginPage(Model model, HttpServletRequest request){
        if(Optional.ofNullable(request.getCookies()).isPresent()){
            List<Users> users = userRepo.findAll();
            model.addAttribute("title", "Login page");
            model.addAttribute("users", new Users());
            model.addAttribute("myAdmin", Optional.ofNullable(admin123).map(Admin::getName).orElse("kill"));
            model.addAttribute("users123", Optional.ofNullable(users).orElseGet(ArrayList::new));
            return "/index";
        }else {
            return "redirect:/error";
        }
    }
}
