package com.application.springbootjpademo.repository;

import com.application.springbootjpademo.model.Blog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRepository extends CrudRepository<Blog,Integer> {
    //@Query(value = "select * from blogs b where b.blog_name = ?1",nativeQuery = true)
    public Optional<Blog> findByBlogName(String blogName);
}
