package com.example.Blog.seervices;

import com.example.Blog.models.Blog;
import com.example.Blog.models.User;
import com.example.Blog.repositories.BlogRepository;
import com.example.Blog.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Blog createBlog(Blog blog, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        blog.setUser(user);
        user.getBlogs().add(blog);
        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> getBlogsByUserId(Long userId) {
        return blogRepository.findByUserId(userId);
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }
}

