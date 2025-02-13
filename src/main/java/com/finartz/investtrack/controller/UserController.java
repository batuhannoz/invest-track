package com.finartz.investtrack.controller;

import com.finartz.investtrack.model.User;
import com.finartz.investtrack.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(
            HttpServletRequest request,
            @RequestBody User updatedUser
    ) {
        Integer uid = (Integer) request.getAttribute("uid");
        User updated = userService.updateUser(uid, updatedUser);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(HttpServletRequest request) {
        Integer uid = (Integer) request.getAttribute("uid");
        return ResponseEntity.ok(userService.getUserById(uid));
    }

}
