package com.application.springbootjpademo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@IdClass(BookId.class)
public class Book {
    @Id
    private String bookTitle;

    @Id
    private String bookName;
}
