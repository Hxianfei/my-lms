package edu.kust.mylms.mapper;

import edu.kust.mylms.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Insert("insert into user(username, password, create_time, update_time) values(#{username}, #{md5Password},now(), now())")
    void add(String username, String md5Password);

    @Select("select * from user")
    ArrayList<User> findAll();

    @Update("update user set email = #{email}, phone = #{phone}, update_time = now() where id = #{id}")
    void update(User user);
}
