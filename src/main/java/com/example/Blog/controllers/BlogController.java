package com.example.Blog.controllers;

import com.example.Blog.models.Blog;

import com.example.Blog.seervices.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping({"/user/{userId}"})
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog,
                                           @PathVariable Long userId) {
        return ResponseEntity.ok(blogService.createBlog(blog,userId));
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Blog>> getBlogsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(blogService.getBlogsByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable Long id) {
        Blog blog = blogService.getBlogById(id);
        if (blog != null) {
            return ResponseEntity.ok(blog);
        }
        return ResponseEntity.notFound().build();
    }
}
