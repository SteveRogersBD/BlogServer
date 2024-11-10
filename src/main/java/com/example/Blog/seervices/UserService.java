package com.example.Blog.seervices;

import com.example.Blog.models.User;
import com.example.Blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User logIn(String email, String password)
    {
        User user = userRepository.findByEmail(email);
        if(user!=null && user.getPassword().equals(password))
        {
            return user;
        }
        throw new RuntimeException("Invalid email or password");
    }

    public User register(User user)
    {
        if(userRepository.existsByEmail(user.getEmail()))
        {
            throw new RuntimeException("Email already registered!!!");
        }

        return userRepository.save(user);
    }
}
