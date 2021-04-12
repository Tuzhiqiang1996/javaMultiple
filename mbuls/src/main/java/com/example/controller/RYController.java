package com.example.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.annoation.DataSource;
import com.example.common.lang.Result;
import com.example.entity.Blog;
import com.example.entity.RY;
import com.example.entity.User;
import com.example.enums.DataSourceEnum;
import com.example.service.BlogService;
import com.example.service.RYService;
import com.example.service.UserService;
import com.example.util.JwtUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

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
public class RYController {
    @Autowired
    RYService ryService;
    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;
    @Autowired
    JwtUtils jwtUtils;

    @DataSource(DataSourceEnum.DB2)
    @GetMapping("/index2")
    public Result index2(String value) {
        QueryWrapper<RY> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_id", value);
        List<RY> Blogs = ryService.list(queryWrapper);
        System.out.println(Blogs);
        return Result.succ("ok");
    }

    @DataSource(DataSourceEnum.DB2)
    @GetMapping("/index1")
    public Result index1(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 5);
        IPage userlists = ryService.page(page, new QueryWrapper<RY>().orderByDesc("user_id"));

        return Result.succ("ok", userlists);
    }

    @DataSource(DataSourceEnum.DB1)
    @GetMapping("/userLists")
    public Result userss(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 5);
        IPage userlists = userService.page(page, new QueryWrapper<User>().orderByDesc("created"));
        JSONObject jsonObject = new JSONObject(userlists); //可以将json格式的字符串变成json对象
        JSONArray jsonArray = (JSONArray) jsonObject.get("records");
//        System.out.println(jsonArray);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonData = (JSONObject) jsonArray.get(i);//得到对象中的第i条记录
            jsonData.remove("password");
        }
        return Result.succ("ok!", jsonObject);
    }
    @DataSource(DataSourceEnum.DB2)
    @GetMapping("/index6")
    public Result index6(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 5);
        IPage userlists = ryService.page(page, new QueryWrapper<RY>().orderByDesc("user_id"));

        return Result.succ("ok", userlists);
    }
    @DataSource(DataSourceEnum.DB1)
    @GetMapping("/blogs6")
    public Result blogs6(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.succ(pageData);
    }
    @DataSource(DataSourceEnum.DB1)
    @GetMapping("/blog6/{id}")
    public Result detail6(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该博客已删除！");
        return Result.succ(blog);
    }
}
