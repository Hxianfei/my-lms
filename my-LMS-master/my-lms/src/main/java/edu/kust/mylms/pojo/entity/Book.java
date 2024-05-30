package edu.kust.mylms.pojo.entity;

import lombok.Data;

@Data
public class Book {
    private Integer id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Integer publishYear;
    private Integer quantity;
}
