package edu.kust.mylms.controller;

import edu.kust.mylms.pojo.result.Result;
import edu.kust.mylms.service.BookCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookcategory")
@Validated
@Slf4j
public class BookCategoryController {
    private final BookCategoryService bookCategoryService;
    @Autowired
    public BookCategoryController(BookCategoryService bookCategoryService) {
        this.bookCategoryService = bookCategoryService;
    }

    // 为图书添加分类
    @PostMapping()
    public Result addCategory(Integer bookId, Integer categoryId) {
        log.info("添加图书分类，图书ID：{}，分类ID：{}", bookId, categoryId);
        bookCategoryService.addCategory(bookId, categoryId);
        return Result.success();
    }
}
