package edu.kust.mylms.service;

import edu.kust.mylms.pojo.entity.User;
import edu.kust.mylms.pojo.result.PageBean;

import java.util.ArrayList;

public interface UserService {
    User findByUsername(String username);

    void register(String username, String password);

    PageBean<User> users(Integer page,Integer pageSize);

    void update(User user);
}
