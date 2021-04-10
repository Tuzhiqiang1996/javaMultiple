package com.example.annoation;

/**
 * @author Tu
 * @Package com.example.annoation
 * @date 2021/4/10-9:57
 * @message  创建注解 DataSource，该注解用于AOP选择数据源的时候做标识，即我们这里通过AOP读取到自定义的注解决定选择数据源，
 * 这也是本文配置多数据源的关键
 */
import com.example.enums.DataSourceEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    DataSourceEnum value() default DataSourceEnum.DB1;

}
