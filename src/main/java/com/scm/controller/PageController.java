package com.scm.controller;

import com.scm.entity.User;
import com.scm.form.UserForm;
import com.scm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final UserService userService;

    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }

    @RequestMapping(value = "/service")
    public String service() {
        return "contact";
    }

    @RequestMapping(value = "/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        return "register";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute("userForm") UserForm userForm, Model model) {
        System.out.println("Registering user: " + userForm.getName());

        try {
            // Check if email already exists
            if (userService.isUserExistByEmail(userForm.getEmail())) {
                model.addAttribute("error", "Email already registered");
                return "register"; // return to registration page with error
            }

            // Create User entity from form
            User user = new User();
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword()); // Ideally, hash the password before saving

            // Save user
            userService.createUser(user);

            model.addAttribute("success", "Registration successful!");
            return "redirect:/login"; // redirect to login page

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error during registration: " + e.getMessage());
            return "register"; // return to registration page with error
        }
    }
}
