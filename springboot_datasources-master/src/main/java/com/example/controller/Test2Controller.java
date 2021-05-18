package com.example.controller;

import com.baomidou.mybatisplus.annotations.DataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.po.UserPo;
import com.example.po.UserPo2;
import com.example.service.User1Service;
import com.example.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName TestController
 * @Description TODO
 * @Auther lbt
 * @Date 2019/6/28/028 14:13
 * @Version 1.0
 */
@RestController
public class Test2Controller {

    @Autowired
    private User1Service user1Service;

    @Autowired
    private User2Service user2Service;

//@DataSource(DataSource1Config)
    @RequestMapping("/user2")
    public Object user2Controller() {

        List<UserPo2> all = user2Service.findAll();
//        Page page = new Page(1, 5);
//        IPage userlists = user2Service.page(page, new QueryWrapper<UserPo2>() );

        return all;
    }
}
