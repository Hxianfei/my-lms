package edu.kust.mylms.mapper;

import edu.kust.mylms.pojo.entity.Book;
import edu.kust.mylms.pojo.vo.BookVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface BookMapper {
    ArrayList<BookVo> findAll(String start, Integer categoryId);

    @Select("insert into book(title,author,publisher,isbn,publish_year,quantity) values(#{title},#{author},#{isbn},#{publisher},#{publishYear},#{quantity})")
    void add(Book book);

    @Select("select * from book where id = #{bookId}")
    Book findById(Integer bookId);

    @Select("update book set title = #{title},author = #{author},isbn = #{isbn},publisher = #{publisher},publish_year = #{publishYear},quantity = #{quantity} where id = #{id}")
    void update(Book book);

    @Select("delete from book where id = #{id}")
    void delete(Integer id);
}
