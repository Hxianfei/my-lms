package edu.kust.mylms.service.impl;

import edu.kust.mylms.mapper.BookCategoryMapper;
import edu.kust.mylms.mapper.CategoryMapper;
import edu.kust.mylms.pojo.entity.Category;
import edu.kust.mylms.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final BookCategoryMapper bookCategoryMapper;
    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper, BookCategoryMapper bookCategoryMapper) {
        this.categoryMapper = categoryMapper;
        this.bookCategoryMapper = bookCategoryMapper;
    }
    @Override
    public List<Category> categories() {
        return categoryMapper.categories();
    }

    @Override
    public void add(String name) {
        categoryMapper.add(name);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        bookCategoryMapper.deleteByCategoryId(id);
        categoryMapper.delete(id);
    }
}
