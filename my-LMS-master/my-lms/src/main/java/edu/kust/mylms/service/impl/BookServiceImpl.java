package edu.kust.mylms.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.kust.mylms.mapper.BookCategoryMapper;
import edu.kust.mylms.mapper.BookMapper;
import edu.kust.mylms.pojo.entity.Book;
import edu.kust.mylms.pojo.result.PageBean;
import edu.kust.mylms.pojo.vo.BookVo;
import edu.kust.mylms.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper;
    private final BookCategoryMapper bookCategoryMapper;
    @Autowired
    public BookServiceImpl(BookMapper bookMapper, BookCategoryMapper bookCategoryMapper) {
        this.bookMapper = bookMapper;
        this.bookCategoryMapper = bookCategoryMapper;
    }

    @Override
    public PageBean<BookVo> books(Integer page, Integer pageSize, String start, Integer categoryId) {
        PageBean<BookVo> pageBean = new PageBean<>();
        PageHelper.startPage(page, pageSize);
        List<BookVo> books = bookMapper.findAll(start, categoryId);
        Page<BookVo> pageInfo = (Page<BookVo>) books;
        pageBean.setTotal((int) pageInfo.getTotal());
        pageBean.setItems(books);
        return pageBean;
    }

    @Override
    public void add(Book book) {
        bookMapper.add(book);
    }

    @Override
    @Transactional // 添加事务
    public void delete(Integer id) {
        bookCategoryMapper.deleteByBookId(id);
        bookMapper.delete(id);
    }

    @Override
    public void addCategory(Integer bookId, Integer categoryId) {

    }

    @Override
    public Book findById(Integer id) {
        return bookMapper.findById(id);
    }
}
