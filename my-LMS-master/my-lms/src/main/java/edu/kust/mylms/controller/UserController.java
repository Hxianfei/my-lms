package edu.kust.mylms.controller;

import edu.kust.mylms.pojo.entity.User;
import edu.kust.mylms.pojo.result.PageBean;
import edu.kust.mylms.pojo.result.Result;
import edu.kust.mylms.service.UserService;
import edu.kust.mylms.utils.JwtUtil;
import edu.kust.mylms.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import edu.kust.mylms.utils.Md5Util;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 用户注册
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^[a-zA-Z0-9_-]{2,16}$") String username,
                           @Pattern(regexp = "^[a-zA-Z0-9_-]{6,16}$") String password) {
        log.info("调用注册接口：参数=>用户名：{}，密码：{}", username, password);
        // 判断用户名是否已存在
        if (userService.findByUsername(username) != null) {
            return Result.error("用户名已存在");
        }
        userService.register(username, password);
        return Result.success();
    }

    // 用户登录
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{2,16}$") String username,
                        @Pattern(regexp = "^[a-zA-Z0-9_-]{6,16}$") String password,
                        Integer role) {
        log.info("调用登录接口：参数=>用户名：{}，密码：{}，角色：{}", username, password, role);
        // 判断用户名是否存在
        User user = userService.findByUsername(username);
        if (user == null) {
            return Result.error("用户名不存在");
        }
        // 判断密码是否正确
        if(!Md5Util.getMD5String(password).equals(user.getPassword())){
            return Result.error("密码错误");
        }
        // 判断权限
        if (user.getRole() != role) {
            return Result.error("权限错误");
        }
        // 登录成功，生成token
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", username);
        String jwt = JwtUtil.getToken(claims);
        return Result.success(jwt);
    }

    // 所有用户信息
    @GetMapping()
    public Result<PageBean<User>> users(Integer page, Integer pageSize) {
        log.info("调用所有用户信息接口");
        return Result.success(userService.users(page, pageSize));
    }

    // 用户个人信息
    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        log.info("调用用户个人信息接口");
        Map<String, Object> claims = ThreadLocalUtil.get();
        User user = userService.findByUsername((String) claims.get("username"));
        return Result.success(user);
    }

    // 更新用户基本信息
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        log.info("调用更新用户基本信息接口：参数=>{}", user);
        Map<String, Object> claims = ThreadLocalUtil.get();
        user.setId((Integer) claims.get("id"));
        userService.update(user);
        return Result.success();
    }
}
