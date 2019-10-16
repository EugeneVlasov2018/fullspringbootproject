package ua.vlasoveugene.fullspringbootproject.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.vlasoveugene.fullspringbootproject.entity.Role;
import ua.vlasoveugene.fullspringbootproject.entity.User;
import ua.vlasoveugene.fullspringbootproject.service.UserService;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Юзается для работы с множеством ролей при авторизации
 */
@Controller
@AllArgsConstructor
public class UserController {
    private final UserService service;
    private final static String USER_PAGE = "userlist";
    private final static String TO_USERPAGE = "redirect:/users";

    @GetMapping("/users")
    public String userList(Model model){
        model.addAttribute("users", service.getAllUsers());
        return USER_PAGE;
    }

    @GetMapping("/users/{user}")
    public String editUser (@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping("/users")
    public String updateUser(@RequestParam("userId") User user,
                             @RequestParam Map<String,String> form,
                             @RequestParam String username){
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                            .map(Role::name)
                            .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key: form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        service.saveUser(user);
        return TO_USERPAGE;
    }
}
