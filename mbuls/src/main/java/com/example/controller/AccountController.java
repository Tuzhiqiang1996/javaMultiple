package com.example.controller;

/**
 * @author Tu
 * @Package com.example.controller
 * @date 2021/2/1-11:39
 */

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
import com.example.entity.User;
import com.example.enums.DataSourceEnum;
import com.example.service.UserService;
import com.example.util.JwtUtils;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * 登录接口
 */
@Data
@RestController

public class AccountController {

    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;










    /**
     * [com.example.entity.User]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/24 14:45
     * @message 修改名称 邮箱
     */
    @RequiresAuthentication
    @PostMapping("/user/edit")
    public Result useredit(@Validated @RequestBody User user) {
        //根据当前id获取用户信息
        User dd = userService.getById(user.getId());
        //设置更改
        dd.setLastLogin(LocalDateTime.now());
        if (user.getUsername() != null && user.getUsername().length() != 0) {
            dd.setUsername(user.getUsername());
            System.out.println("name修改");
        }
        if (user.getEmail() != null && user.getEmail().length() != 0) {
            dd.setEmail(user.getEmail());
            System.out.println("邮箱修改");
        }

//        BeanUtil.copyProperties(user, dd);

        userService.saveOrUpdate(dd);
        JSONObject jsonObject = new JSONObject(dd);//可以将json格式的字符串变成json对象
        jsonObject.remove("password");//过滤的值
//        System.out.println(jsonObject);
        return Result.succ("修改成功！", jsonObject);
    }

    /**
     * [java.lang.Integer, java.lang.String, java.lang.String]
     *
     * @return com.example.common.lang.Result
     * @author Tu
     * @date 2021/3/25 14:11
     * @message 修改密码
     * 获取前端特定的 参数 前端用get方式的形势拼接
     * 后端用   @RequestParam(name = "id") Integer id 接收
     * <p>
     * http://localhost:8081/user/pass?id=1&newpassword="123"&password="123"
     * 将不需要的 敏感 数据进行  将json格式的字符串变成json对象
     * 再进行移除
     */

    @PostMapping("/user/pass")
    public Result userpass(@RequestBody @RequestParam(name = "id") Integer id, @RequestParam(name = "password") String password, @RequestParam(name = "newpassword") String newpassword) {
//        System.out.println(id + "" + password + "" + newpassword);
        User dd = userService.getById(id);
        if (password != null && password.length() != 0) {

            if (SecureUtil.md5(password).equals(dd.getPassword())) {
                dd.setPassword(SecureUtil.md5(newpassword));
                dd.setLastLogin(LocalDateTime.now());
                System.out.println("密码修改");
                userService.saveOrUpdate(dd);
            } else {
                System.out.println("密码效验错误");
                return Result.fail("密码效验错误！");
            }

        }
//        System.out.println(dd);
        JSONObject jsonObject = new JSONObject(dd);//可以将json格式的字符串变成json对象
        jsonObject.remove("password");//过滤的值
        return Result.succ("修改成功!", jsonObject);
    }

}
