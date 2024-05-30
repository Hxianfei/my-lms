package edu.kust.mylms.pojo.vo;

import edu.kust.mylms.pojo.entity.Book;
import lombok.Data;

@Data
// 图书信息视图
public class BookVo extends Book {
    private String categoryName;
}
