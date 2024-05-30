package edu.kust.mylms.service;

import edu.kust.mylms.pojo.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> categories();

    void add(String name);

    void delete(Integer id);
}
