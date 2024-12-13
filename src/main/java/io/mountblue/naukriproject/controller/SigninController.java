package io.mountblue.naukriproject.controller;

import io.mountblue.naukriclone.dto.UserRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signin")
public class SigninController {
    @GetMapping
    public String showSigninForm(Model model) {
        model.addAttribute("userRequestDTO", new UserRequestDTO());
        return "login";
    }
}
