package com.example.springboot1.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot1.common.Result;
import com.example.springboot1.entity.User;
import com.example.springboot1.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


@RestController   //1.定义  这是返回json 的controller
@RequestMapping("/user")  //2.定义路由   接口统一路由 数据库表
public class UserController {             //实现前后台数据交互

    @Resource        //将mapper引入到controller 这种方法不规范    一般controller引入service类，service引入mapper
    UserMapper userMapper;

    //新增
    @PostMapping  //3.定义一个post接口
    public Result <?>save(@RequestBody User user) //RequestBody 将前台的传过来的json传化为Java对象
    {           //<?>  给Result一个泛型  ? 表示任何一种类型
        userMapper.insert(user)  ;//通过userMapper实现数据库的新增 userMapper.insert  mybatis-plus提供的方法
        return Result.success();  //返回一个信息  success
    }

    //查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,/**当前页,数据页面当前翻到的页码*/
                              @RequestParam(defaultValue = "10") Integer pageSize ,  /**每一页多少条 10页每条 还是5页每条 */
                              @RequestParam(defaultValue = "''") String search   /** 模糊查询*/
    ){

         Page<Map<String, Object>> userPage= userMapper.selectMapsPage( new Page<>(pageNum,pageSize), Wrappers.<User>lambdaQuery().like(User::getNick_name,search));
         return Result.success(userPage);
    }


}
