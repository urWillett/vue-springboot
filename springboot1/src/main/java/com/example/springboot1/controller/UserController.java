package com.example.springboot1.controller;


import com.example.springboot1.common.Result;
import com.example.springboot1.entity.User;
import com.example.springboot1.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController   //1.定义  这是返回json 的controller
@RequestMapping("/user")  //2.定义路由   接口统一路由
public class UserController {

    @Resource        //将mapper引入到controller 这种方法不规范    一般controller引入service类，service引入mapper
    UserMapper userMapper;
    @PostMapping  //3.定义一个post接口
    public Result <?>save(@RequestBody User user) //RequestBody 将前台的传过来的json传化为Java对象
    {           //<?>  给Result一个泛型  ? 表示任何一种类型
        userMapper.insert(user)  ;//通过userMapper实现数据库的新增
        return Result.success();  //返回一个信息  success
    }
}
