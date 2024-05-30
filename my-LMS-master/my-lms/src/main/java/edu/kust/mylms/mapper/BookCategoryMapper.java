package edu.kust.mylms.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookCategoryMapper {
    @Select("insert into book_category(book_id,category_id) values(#{bookId},#{categoryId})")
    void add(Integer bookId, Integer categoryId);

    @Select("delete from book_category where category_id = #{id}")
    void deleteByCategoryId(Integer id);

    @Select("delete from book_category where book_id = #{id}")
    void deleteByBookId(Integer id);
}
