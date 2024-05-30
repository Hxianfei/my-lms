package edu.kust.mylms.service.impl;

import edu.kust.mylms.mapper.BookCategoryMapper;
import edu.kust.mylms.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryMapper bookCategoryMapper;
    @Autowired
    public BookCategoryServiceImpl(BookCategoryMapper bookCategoryMapper) {
        this.bookCategoryMapper = bookCategoryMapper;
    }
    @Override
    public void addCategory(Integer bookId, Integer categoryId) {
        bookCategoryMapper.add(bookId, categoryId);
    }
}
