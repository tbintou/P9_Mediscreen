package com.bintou.mediscreen.front.controller;

import com.bintou.mediscreen.front.model.User;
import com.bintou.mediscreen.front.service.PasswordValidator;
import com.bintou.mediscreen.front.service.UserService;
import com.bintou.mediscreen.front.service.auth.UserDetailsAuthenticate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    @Autowired
    UserDetailsAuthenticate userDetailsAuthenticate;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String registrationView(Model model) {
        model.addAttribute(new User());
        return "registration";
    }

    @PostMapping
    public String registrationUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        String registrationError = null;
        String patternError = null;
        // Validate username
        User userExist = userService.findByUsername(user.getUsername());
        if (userExist !=null) {
            registrationError = "Ce nom d'utilisateur existe";
            model.addAttribute("registrationError", true);
        }
        // Validate password
        String passwordUser = user.getPassword();
        boolean validated = PasswordValidator.isValid(passwordUser);
        if (!validated){
            patternError = "Votre mot de passe n'est pas valide";
            model.addAttribute("patternError", true);
        }
        if (registrationError == null && patternError == null && (!bindingResult.hasErrors())){
            userService.save(user);
            redirectAttributes.addFlashAttribute("message", "Vous vous êtes inscrit avec succès, veuillez vous connecter");
            return "redirect:/login";
        }

        return "registration";
    }
}
