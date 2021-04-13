package com.example.controller;


import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.annoation.DataSource;
import com.example.common.dto.LoginDto;
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
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        JSONObject jsonObject = new JSONObject(userlists);
        JSONArray jsonArray = (JSONArray) jsonObject.get("records");

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonData = (JSONObject) jsonArray.get(i);
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
    @PostMapping("/logining")
    public Result logining(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response, HttpServletRequest request) {

        User user = userService.getOne(new QueryWrapper<User>().eq("userName", loginDto.getUsername()));

/**
 * [com.example.common.dto.LoginDto, javax.servlet.http.HttpServletResponse, javax.servlet.http.HttpServletRequest]
 * @author Tu
 * @date 2021/4/13 17:10
 * @message 出现错误 及时 return
 * @return com.example.common.lang.Result
 */
        if (user == null) {
            System.out.println("用户不存在");

            //Assert.notNull(user, "用户不存在");
            return Result.fail("用户不存在", "-2");
        } else if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            System.out.println("密码不正确");
            return Result.fail("密码不正确");
        }


        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .put("status", user.getStatus())
                .put("created", user.getCreated())
                .map()
        );
    }
    /**
     * [java.lang.Integer]
     * @author Tu
     * @date 2021/4/13 17:18
     * @message  @RequiresAuthentication效验token
     * 问题 加了这个全都会失效 呃呃呃
     * @return com.example.common.lang.Result
     */
    @DataSource(DataSourceEnum.DB1)
    @GetMapping("/userList")
//    @RequiresAuthentication
        public Result users(Integer currentPage) {
            /**
             * [java.lang.Integer]
             * @author Tu
             * @date 2021/3/5 14:33
             * @message
             * @return com.example.common.lang.Result
             * 将用户列表返回
             * 将密码过滤掉返回
             * 参考：https://blog.csdn.net/dudufine/article/details/52218463
             */
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
//            System.out.println("data[" + i + "]:" + jsonData.remove("password"));
            }
//        System.out.println(String.valueOf(jsonObject));
//        return Result.succ(userlists);
            //过滤后的
            return Result.succ(jsonObject);
        }




}
