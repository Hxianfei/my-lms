package edu.kust.mylms.mapper;

import edu.kust.mylms.pojo.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select * from category")
    List<Category> categories();

    @Select("insert into category(name) values(#{name})")
    void add(String name);

    @Select("delete from category where id = #{id}")
    void delete(Integer id);
}
