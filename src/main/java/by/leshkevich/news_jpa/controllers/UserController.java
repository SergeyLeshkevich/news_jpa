package by.leshkevich.news_jpa.controllers;

import by.leshkevich.news_jpa.model.beans.User;
import by.leshkevich.news_jpa.model.enums.Roles;
import by.leshkevich.news_jpa.services.UserService;
import by.leshkevich.news_jpa.util.AuthenticationUser;
import by.leshkevich.news_jpa.util.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    @ModelAttribute("auth_user")
    public User getUser() {
        return AuthenticationUser.getAuthenticationUser();
    }

    @GetMapping("/reg")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "reg";
    }

    @PostMapping("/reg")
    public String registration(@RequestParam("file") MultipartFile files, @ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "reg";

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            userService.registration(user, files, Roles.ROLE_USER.name());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return "redirect:/user/auth";
    }

    @GetMapping("/add/author")
    public String addAuthor(@ModelAttribute("user") User user) {
        return "addauthor";
    }

    @PostMapping("/add/author")
    public String addAuthor(@RequestParam("file") MultipartFile files, @ModelAttribute("user") @Valid User user,
                            BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "reg";

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            userService.registration(user, files, Roles.ROLE_AUTHOR.name());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return "redirect:/news/home";
    }

    @GetMapping("/auth")
    public String authPage() {
        return "auth";
    }

    @GetMapping("/cabinet")
    public String cabinet(Model model) {

        User user = AuthenticationUser.getAuthenticationUser();
        model.addAttribute("user", user);
        return "cabinet";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("file") MultipartFile file,
                             @ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {

        User authUser = AuthenticationUser.getAuthenticationUser();

        if (!authUser.getEmail().equals(user.getEmail())) {
            userValidator.validate(user, bindingResult);
        }

        if (bindingResult.hasErrors())
            return "cabinet";

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.registration(user, file, authUser.getRole());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/user/cabinet";
    }
}

