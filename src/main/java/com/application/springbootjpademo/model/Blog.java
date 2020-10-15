package com.application.springbootjpademo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private int id = 0;
    @Column(name = "blog_name")
    private String blogName;
    @Column(name = "blog_desc")
    private String blogDesc;
}
