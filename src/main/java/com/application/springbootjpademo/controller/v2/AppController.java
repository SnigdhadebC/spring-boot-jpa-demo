package com.application.springbootjpademo.controller.v2;

import com.application.springbootjpademo.exception.BlogAlreadyExistsException;
import com.application.springbootjpademo.exception.BlogNotFoundException;
import com.application.springbootjpademo.model.Blog;
import com.application.springbootjpademo.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "newAPI")
@RequestMapping("/api/v2/blog")
public class AppController {

    @Autowired
    private AppService service;

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs(){
        return ResponseEntity.ok(service.getAllBlogs());
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<Blog> getBlogByName(@PathVariable("blogId") String blogName) throws BlogNotFoundException {
        return ResponseEntity.ok(service.getBlogByName(blogName));
    }

    @PostMapping
    public ResponseEntity<?> registerBlog(@RequestBody Blog blog) throws BlogAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerBlog(blog));
    }

    @DeleteMapping("/{blogId}")
    public ResponseEntity<?> deleteBlog(@PathVariable("blogId") int blogId) throws BlogNotFoundException {
        service.deleteBlog(blogId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{blogId}")
    public ResponseEntity<?> updateBlog(@RequestBody Blog blog,@PathVariable("blogId") int blogId) throws BlogNotFoundException {
        return ResponseEntity.ok(service.updateBlog(blog,blogId));
    }
}
