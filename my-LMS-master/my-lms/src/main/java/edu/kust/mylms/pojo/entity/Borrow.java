package edu.kust.mylms.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Borrow {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;
    private Integer status;
}
