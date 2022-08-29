package com.example.employemanagementsystem.web;

import com.example.employemanagementsystem.model.binding.UserRegisterBindingModel;
import com.example.employemanagementsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registerForm(Model model) {
        model.addAttribute("userRegModel", new UserRegisterBindingModel());
        return "user-register";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid UserRegisterBindingModel bindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", bindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel"
                    , bindingResult);
            return "redirect:/users/registration";
        }
        if (!userService.passwordsChecker(bindingModel)) {
            redirectAttributes.addFlashAttribute("userModel", bindingModel);
            redirectAttributes.addFlashAttribute("passwordsChecker", true);
        }

        if (!userService.isUserNameFree(bindingModel.getUserName())) {
            redirectAttributes.addFlashAttribute("userModel", bindingModel);
            redirectAttributes.addFlashAttribute("userNameOccupied", true);
            return "redirect:/users/registration";
        }

        userService.registerUser(bindingModel);
        return "redirect:/registration?success";
    }
}
