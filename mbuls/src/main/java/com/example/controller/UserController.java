package com.example.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.User;
import com.example.service.BlogService;
import com.example.service.RYService;
import com.example.service.UserService;
import com.example.util.JwtUtils;
import lombok.Data;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2021-01-29
 */

/**
 * 测试结果
 */
@Data
@RestController
//@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RYService ryService;



}
