package com.example.service;

import com.example.mapper.test2.IUser2Mapper;
import com.example.po.UserPo;
import com.example.po.UserPo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName User1Service
 * @Description TODO
 * @Auther lbt
 * @Date 2019/6/28/028 10:12
 * @Version 1.0
 */
@Service
@SuppressWarnings("all")
public class User2Service {

    @Autowired
    private IUser2Mapper user2Mapper;

    public List<UserPo2> findAll() {

        return user2Mapper.findAll();
    }
}
