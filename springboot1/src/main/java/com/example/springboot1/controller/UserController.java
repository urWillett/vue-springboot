package com.example.springboot1.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot1.common.Result;
import com.example.springboot1.entity.People;
import com.example.springboot1.mapper.PeopleMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin  //浏览器跨域注释
@RestController   //1.定义  这是返回json 的controller
@RequestMapping("/people")  //2.定义路由   接口统一路由 数据库表
public class UserController {             //实现前后台数据交互

    @Resource        //将mapper引入到controller 这种方法不规范    一般controller引入service类，service引入mapper
    PeopleMapper peopleMapper;

    //登录接口
    @PostMapping("/login")
    public Result <?>login(@RequestBody People people)
    {
       People res=peopleMapper.selectOne(Wrappers.<People>lambdaQuery().eq(People::getUsername,people.getUsername()).eq(People::getPassword,people.getPassword()));
       if (res ==null){
           return Result.error("-1","用户名或密码错误");
       }
        return Result.success();
    }

    //新增接口
    @PostMapping  //3.定义一个post接口
    public Result <?>save(@RequestBody People people) //RequestBody 将前台的传过来的json传化为Java对象
    {           //<?>  给Result一个泛型  ? 表示任何一种类型
        if (people.getPassword() == null){
            people.setPassword("123456");
        }
        peopleMapper.insert(people)  ;//通过peopleMapper实现数据库的新增 peopleMapper.insert  mybatis-plus提供的方法
        return Result.success();  //返回一个信息  success
    }

    //删除接口
    @DeleteMapping("/{id}")      //  /{id} 占位符  多个的话  /{id} /{aa}
    public Result <?>delete(@PathVariable Long id) {      //多个的话  @PathVariable Long id @PathVariable String aa
        peopleMapper.deleteById(id);
        return Result.success();
    }

    //更新接口
    @PutMapping
    public Result <?>update(@RequestBody People people) {
        peopleMapper.updateById(people);
        return Result.success();
    }
    //查询接口
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,/**当前页,数据页面当前翻到的页码*/
                              @RequestParam(defaultValue = "10") Integer pageSize   /**每一页多少条 10页每条 还是5页每条 */
                             ,@RequestParam(defaultValue = "") String search   /** 模糊查询*/
    ){
        LambdaQueryWrapper<People> wrapper = Wrappers.<People>lambdaQuery().orderByDesc(People::getNickName);
        if (StrUtil.isNotBlank(search)){
            wrapper.like(People::getNickName ,search);
        }

        Page<People> peoplePage = peopleMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

         return Result.success(peoplePage);
    }


}
