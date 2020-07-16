package dev.emiliofranco.expense_tracker_api.controller;

import dev.emiliofranco.expense_tracker_api.model.User;
import dev.emiliofranco.expense_tracker_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public User registerUser(@RequestBody Map<String, Object> userMap){
        String firstName = (String) userMap.get("firstName");
        String lastName = (String) userMap.get("lastName");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        String test = System.getenv("SPRING_DATASOURCE_URL");
        System.out.println();


        return userService.registerUser(firstName, lastName, email, password);
    }
}
