package com.scm.controller;

import com.scm.form.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @RequestMapping( value = "/about")
    public String about() {
        return "about";
    }

    @RequestMapping( value = "/service")
    public String service() {
        return "contact";
    }

    @RequestMapping( value = "/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        return "register";
    }

    @RequestMapping( value = "/do-register", method = RequestMethod.POST)
    public String prosesseRegister() {
        System.out.println("Register");
        //UserForm
        return "redirect:/register";
    }
}
