
[开发地址](https://juejin.cn/post/6844903823966732302)

[github](https://github.com/MarkerHub/vueblog)

[视频地址](https://www.bilibili.com/video/BV1PQ4y1P7hZ/)

### docker学习
[docker学习](https://usthe.com/2017/12/docker_learn/)

### mybatis plus
[MyBatis-Plus](https://mp.baomidou.com/guide/install.html)
![](https://upload-images.jianshu.io/upload_images/16598307-1ab9b66561007142.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1000/format/webp)

##开始
创建 springboot 项目
新建项目 - develop-1.2+web-1+mysql

### 问题

 启动是报错
将解决办法：跨域配置报错，将.allowedOrigins替换成.allowedOriginPatterns即可。

### 访问
登录
http://localhost:8081/login


### 项目结构
```
com.example   根目录
        ├─ common 公共类 用于存放配置类，工具类
                ├─ dto
                ├─ exception
                └─ lang 
        ├─ config 配置信息类
                ├─ CoreConfig.java
                ├─ MybatisPlusConfig.java
                └─ShiroConfig.lava
        ├─ controller 前端控制器 处理用户输入请求
                    
        ├─ entity 模型层，存放实体类
        ├─ mapper dao层 
        ├─ service 数据服务层 
                └─impl 数据服务实现层
        ├─ shiro        
        ├─ util 工具类
        ├─CondeGenerator
        └─VueblogApplication.java 工程启动类
resources
        ├─ mapper
                ├─
        ├─ MATA-INF
                ├─
        ├─ application.properties 核心配置文件
        ├─ 
        
       
          
          
```

### 在以上的基础上进行修改 实现多数据源连接
在项目中新增了如下文件
[参考连接](https://blog.csdn.net/zhangcongyi420/article/details/103229930/?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_title-0&spm=1001.2101.3001.4242)

```js

com.example
        |-annoation
            DataSoure.java  用于aop类中当作切入点来选择数据源 该注解用于AOP选择数据源的时候做标识，
即我们这里                       通过AOP读取到自定义的注解决定选择数据源，这也是本文配置多数据源的关键
        |-aop
            DataSoureAspect.java 编写aop类
        |-common
            |-DataSCH
                DataSoureContextHolder.java 用于设置，获取，清空 当前线程内的数据源变量
                                        该类的作用是持有当前线程环境下的数据源，并切换数据源
        |enums
            DataSourceEnum.JAVA 用于存放数据源名称
        |-config
            transaction
                mulction.java  这个类统一管理数据源的配置，比如连接池、sqlSession、扫描mapper等,是最核心的一个类,这个配置类中有一处需要重点关注，将留在后面说
                mufactory.java
        Muconfig.java
        MultipleDataSource.JAVA 该类起到路由数据源的作用

application-default.yml ->修改数据源文件
```
实现在不同实现类中数据源切换 
注解
@DataSource(DataSourceEnum.DB1) 数据库1....多个数据库配置可
...
将注解放在每个接口实现层
例如
```js
 @DataSource(DataSourceEnum.DB1)
    @GetMapping("/668")
    public Result richu(Integer currentPage){
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ("ok",pageData);
    }
```

问题
这样是如此的麻烦
哎

说说数据库的配置
```js
# DataSource Config
spring:
  datasource:
    druid:
#      defaultDs: db1
        db1:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3306/tudemo?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai
          username: root
          password:
        db2:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3306/ry-vue?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai
          username: root
          password:
shiro-redis:
  enabled: true
  redis-manager:
    host: 127.0.0.1:6379

```
#### 不理想
接口实现只能在一个类中实现 并没有给真正实现动态切换数据源
现在只能将全部请求放在RYController中才会起到作用

###  上诉问题 是因为无法进行动态切换
### 改进  在service/impl 中一一指定数据库

[参考](https://gitee.com/wbsxch/ssm)

未实现

现今只能
 将接口放在一个类中 在类中去调用其他类的方法

