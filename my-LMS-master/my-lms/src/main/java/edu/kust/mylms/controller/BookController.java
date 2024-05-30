package edu.kust.mylms.controller;

import edu.kust.mylms.pojo.entity.Book;
import edu.kust.mylms.pojo.result.PageBean;
import edu.kust.mylms.pojo.result.Result;
import edu.kust.mylms.pojo.vo.BookVo;
import edu.kust.mylms.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/book")
@Validated
@Slf4j
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 查询所有书籍
    @GetMapping()
    public Result<PageBean<BookVo>> books(Integer page, Integer pageSize, String start, Integer categoryId) {
        log.info("调用查询书籍接口：参数=>" + page + ", " + pageSize + ", " + start + ", " + categoryId);
        PageBean<BookVo> pageBean = bookService.books(page, pageSize, start, categoryId);
        return Result.success(pageBean);
    }

    // 添加图书
    @PostMapping()
    public Result add(@RequestBody Book book) {
        log.info("调用添加书籍接口：参数=>" + book);
        bookService.add(book);
        return Result.success();
    }

    // 删除图书
    @DeleteMapping()
    public Result delete(Integer id) {
        log.info("调用删除书籍接口：参数=>" + id);
        bookService.delete(id);
        return Result.success();
    }

    // 查询图书
    @GetMapping("/findbyid")
    public Result<Book> findById(Integer id) {
        log.info("调用查询书籍接口：参数=>" + id);
        Book book = bookService.findById(id);
        return Result.success(book);
    }
}
