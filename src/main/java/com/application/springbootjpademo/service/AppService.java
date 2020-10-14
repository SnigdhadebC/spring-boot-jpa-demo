package com.application.springbootjpademo.service;

import com.application.springbootjpademo.exception.BlogAlreadyExistsException;
import com.application.springbootjpademo.exception.BlogNotFoundException;
import com.application.springbootjpademo.model.Blog;
import com.application.springbootjpademo.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {

    @Autowired
    private AppRepository repository;

    public List<Blog> getAllBlogs(){
        return (List<Blog>) repository.findAll();
    }

    public Blog registerBlog(Blog blog) throws BlogAlreadyExistsException {
        if(repository.findByBlogName(blog.getBlogName()).isPresent())
        {
            throw new BlogAlreadyExistsException("Blog already exists.....");
        }
        return repository.save(blog);
    }

    public void deleteBlog(int blogId) throws BlogNotFoundException {
        if(repository.findById(blogId).isPresent()){
            repository.deleteById(blogId);
        }else
        {
            throw new BlogNotFoundException("Blog "+blogId+ " is not found....");
        }
    }

    public Blog updateBlog(Blog blog, int blogId) throws BlogNotFoundException {
        if(repository.findById(blogId).isPresent()){
            Blog blogInDb = repository.findById(blogId).get();
            blogInDb.setBlogName(blog.getBlogName());
            blogInDb.setBlogDesc(blog.getBlogDesc());
            return repository.save(blogInDb);
        }
        throw new BlogNotFoundException("Blog "+blogId+ " is not found....");
    }

    public Blog getBlogByName(String blogName) throws BlogNotFoundException {
        if(repository.findByBlogName(blogName).isPresent())
            return repository.findByBlogName(blogName).get();
        else
            throw new BlogNotFoundException("Blog "+blogName+ " is not found....");
    }
}
