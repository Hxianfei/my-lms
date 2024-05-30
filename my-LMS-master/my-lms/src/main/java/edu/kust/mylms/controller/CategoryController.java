package edu.kust.mylms.controller;

import edu.kust.mylms.pojo.entity.Category;
import edu.kust.mylms.pojo.result.Result;
import edu.kust.mylms.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/category")
@Validated
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    // 获取所有分类
    @GetMapping()
    public Result<List<Category>> categories() {
        log.info("获取所有分类");
        return Result.success(categoryService.categories());
    }

    // 添加图书分类
    @PostMapping()
    public Result add(@Pattern(regexp = "^\\S{2,10}$") String name) {
        log.info("添加图书分类：参数=>" + name);
        categoryService.add(name);
        return Result.success();
    }

    // 删除图书分类
    @DeleteMapping()
    public Result delete(Integer id) {
        log.info("删除图书分类：参数=>" + id);
        categoryService.delete(id);
        return Result.success();
    }
}
