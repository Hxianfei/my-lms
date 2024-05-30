package edu.kust.mylms.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
// 借阅记录
public class BorrowsVo {
    private Integer id;
    private String username;
    private String title;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;
    private Integer status;
}
