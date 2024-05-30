package edu.kust.mylms.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import edu.kust.mylms.mapper.UserMapper;
import edu.kust.mylms.pojo.entity.User;
import edu.kust.mylms.pojo.result.PageBean;
import edu.kust.mylms.service.UserService;
import edu.kust.mylms.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        // 加密密码
        String Md5Password = Md5Util.getMD5String(password);
        userMapper.add(username, Md5Password);
    }

    @Override
    public PageBean<User> users(Integer page,Integer pageSize) {
        PageBean<User> pageBean = new PageBean<>();
        PageHelper.startPage(page,pageSize);
        Page<User> users = (Page<User>) userMapper.findAll();
        pageBean.setTotal((int) users.getTotal());
        pageBean.setItems(users);
        return pageBean;
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }
}
