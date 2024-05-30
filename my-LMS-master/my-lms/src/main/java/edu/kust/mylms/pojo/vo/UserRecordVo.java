package edu.kust.mylms.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
// 用户借阅信息
public class UserRecordVo {
    private Integer bookId;
    private String title;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;
}
