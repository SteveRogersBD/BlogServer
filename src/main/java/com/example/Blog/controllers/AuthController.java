package com.example.Blog.controllers;

import com.example.Blog.models.User;
import com.example.Blog.requests.LogInRequest;
import com.example.Blog.requests.RegisterRequest;
import com.example.Blog.responses.ApiResponse;
import com.example.Blog.responses.AuthResponse;
import com.example.Blog.seervices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ApiResponse<?>logInUser(@RequestBody LogInRequest req)
    {
        try{
            User user = userService.logIn(req.getEmail(), req.getPassword());
            AuthResponse response = new AuthResponse(
                    user.getId(), user.getEmail(), user.getPassword()
            );
            return ApiResponse.success("Log in Successful",response);
        }catch(Exception e)
        {
            return ApiResponse.error("Invalid email or password");
        }
    }

    @PostMapping("/register")
    public ApiResponse<?>registerUser(@RequestBody RegisterRequest req)
    {
        try{
            User newUser = new User();
            newUser.setEmail(req.getEmail());
            newUser.setPassword(req.getPassword());
            newUser.setUsername(req.getUsername());
            User user = userService.register(newUser);
            AuthResponse response = new AuthResponse(
                    user.getId(), user.getUsername(),user.getEmail()

            );
            return ApiResponse.success("Registration successful!!!",response);
        }catch (Exception e)
        {
            return ApiResponse.error("Email already exists!!!");
        }
    }




}
