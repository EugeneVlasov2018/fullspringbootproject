package ua.vlasoveugene.fullspringbootproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.vlasoveugene.fullspringbootproject.entity.Role;
import ua.vlasoveugene.fullspringbootproject.entity.User;
import ua.vlasoveugene.fullspringbootproject.service.RegistrationService;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.USER);

        User userFromDb = service.getCurrentUser(innerUser.getUsername());
        if(userFromDb!=null){
            model.addAttribute("existUser", "User with this login is exist((");
            return "registration";
        }

        innerUser.setActive(true);
        innerUser.setRoles(Collections.singleton(Role.USER));
        service.saveNewUser(innerUser);
        return "redirect:/login";
    }
}
