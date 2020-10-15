package com.application.springbootjpademo.service;

import com.application.springbootjpademo.exception.BlogAlreadyExistsException;
import com.application.springbootjpademo.exception.BlogNotFoundException;
import com.application.springbootjpademo.model.Blog;
import com.application.springbootjpademo.repository.AppRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AppService {

    @Autowired
    private AppRepository repository;

    @Value("${app.exception.blogAlreadyAvailable}")
    private String exceptionMsgForBlogAlreadyAvailable;

    @Value("${app.exception.blogNotFound}")
    private String exceptionMsgForBlogNotFound;

    @Cacheable(value = "blogs")
    public List<Blog> getAllBlogs(){
        log.info("Inside getALlBlogs......");
        return (List<Blog>) repository.findAll();
    }

    @Cacheable(value="blog",key="blog.id")
    public Blog registerBlog(Blog blog) throws BlogAlreadyExistsException {
        if(repository.findByBlogName(blog.getBlogName()).isPresent())
        {
            throw new BlogAlreadyExistsException(exceptionMsgForBlogAlreadyAvailable);
        }
        return repository.save(blog);
    }

    @CacheEvict(value = "blog",key = "#blogId")
    public void deleteBlog(int blogId) throws BlogNotFoundException {
        if(repository.findById(blogId).isPresent()){
            repository.deleteById(blogId);
        }else
        {
            throw new BlogNotFoundException(exceptionMsgForBlogNotFound);
        }
    }

    @CachePut(value = "blog",key = "#blog.id")
    public Blog updateBlog(Blog blog, int blogId) throws BlogNotFoundException {
        if(repository.findById(blogId).isPresent()){
            Blog blogInDb = repository.findById(blogId).get();
            blogInDb.setBlogName(blog.getBlogName());
            blogInDb.setBlogDesc(blog.getBlogDesc());
            return repository.save(blogInDb);
        }
        throw new BlogNotFoundException(exceptionMsgForBlogNotFound);
    }

    @Cacheable(value = "blog",key = "#blogName")
    public Blog getBlogByName(String blogName) throws BlogNotFoundException {
        if(repository.findByBlogName(blogName).isPresent())
            return repository.findByBlogName(blogName).get();
        else
            throw new BlogNotFoundException(exceptionMsgForBlogNotFound);
    }
}
