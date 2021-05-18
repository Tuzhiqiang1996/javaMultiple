package com.example.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName UserPo
 * @Description TODO
 * @Auther lbt
 * @Date 2019/6/28/028 10:09
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPo2 {

    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String age;

    /**
     * 创建时间
     */
    private String subject;
}
