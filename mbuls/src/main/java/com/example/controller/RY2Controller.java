package com.example.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.annoation.DataSource;
import com.example.common.lang.Result;
import com.example.entity.RY;
import com.example.entity.User;
import com.example.enums.DataSourceEnum;
import com.example.service.RYService;
import com.example.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class RY2Controller {
//    @Autowired
//    UserService userService;
//
//
//    @DataSource(DataSourceEnum.DB1)
//    @GetMapping("/gits")
//    public Result gits() {
//        System.out.println("12");
//        return Result.succ("12",userService);
//    }
//
//    @GetMapping("/userList")
//    public Result users(Integer currentPage) {
//        if (currentPage == null || currentPage < 1) {
//            currentPage = 1;
//        }
//        Page page = new Page(currentPage, 5);
//        IPage userlists = userService.page(page, new QueryWrapper<User>().orderByDesc("created"));
//        JSONObject jsonObject = new JSONObject(userlists); //可以将json格式的字符串变成json对象
//        JSONArray jsonArray = (JSONArray) jsonObject.get("records");
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JSONObject jsonData = (JSONObject) jsonArray.get(i);//得到对象中的第i条记录
//            jsonData.remove("password");
//        }
//        return Result.succ(jsonObject);
//    }
}
