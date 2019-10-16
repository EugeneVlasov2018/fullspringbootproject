package ua.vlasoveugene.fullspringbootproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.vlasoveugene.fullspringbootproject.entity.User;
import ua.vlasoveugene.fullspringbootproject.service.RegistrationService;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService service;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(User innerUser,
                             Model model){

        if(!service.saveNewUser(innerUser)){
            model.addAttribute("existUser", "User with this login is exist((");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activateUser(Model model, @PathVariable String code){
        boolean isActivated = service.activateUser(code);

        if(isActivated){
            model.addAttribute("message", "User succesfuly activated");
        } else {
            model.addAttribute("message", "The User with this activation code is not exist");
        }
        return "login";
    }
}
