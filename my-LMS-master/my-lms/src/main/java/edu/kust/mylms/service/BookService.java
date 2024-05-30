package edu.kust.mylms.service;

import edu.kust.mylms.pojo.entity.Book;
import edu.kust.mylms.pojo.result.PageBean;
import edu.kust.mylms.pojo.vo.BookVo;

import java.util.ArrayList;

public interface BookService {
    PageBean<BookVo> books(Integer page, Integer pageSize, String start, Integer categoryId);

    void add(Book book);

    void delete(Integer id);

    void addCategory(Integer bookId, Integer categoryId);

    Book findById(Integer id);
}
