package edu.kust.mylms.pojo.entity;

import lombok.Data;

@Data
public class BookCategory {
    private Integer id;
    private Integer bookId;
    private Integer categoryId;
}
