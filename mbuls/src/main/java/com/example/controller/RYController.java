package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.annoation.DataSource;
import com.example.common.lang.Result;
import com.example.entity.Blog;
import com.example.entity.RY;
import com.example.enums.DataSourceEnum;
import com.example.service.RYService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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


}
