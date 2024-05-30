package edu.kust.mylms.mapper;

import edu.kust.mylms.pojo.entity.Borrow;
import edu.kust.mylms.pojo.vo.BorrowsVo;
import edu.kust.mylms.pojo.vo.UserRecordVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BorrowMapper {
    @Select("select br.id,u.username,b.title,br.borrow_time,br.return_time,br.status " +
            "from borrow br join user u on br.user_id=u.id " +
            "join book b on br.book_id=b.id " +
            "order by br.borrow_time desc ")
    List<BorrowsVo> borrows();

    @Select("insert into borrow(book_id,user_id,borrow_time,status) values(#{bookId},#{userId},now(),0)")
    void addBorrow(Integer userId, Integer bookId);

    @Select("select b.id as book_id, b.title,br.borrow_time,br.return_time " +
            "from borrow br join book b on br.book_id = b.id " +
            "where br.user_id = #{userId} and br.status = #{status}")
    List<UserRecordVo> userRecord(Integer userId, Integer status);

    @Select("update borrow set return_time = now(),status = 1 where id = #{borrowId}")
    void returnBook(Integer borrowId);

    @Select("select * from borrow where id = #{borrowId} ")
    Borrow findById(Integer borrowId);


}
