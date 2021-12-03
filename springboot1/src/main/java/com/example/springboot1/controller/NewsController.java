package com.example.springboot1.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot1.common.Result;
import com.example.springboot1.entity.News;
import com.example.springboot1.mapper.NewsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@CrossOrigin  //浏览器跨域注释
@RestController   //1.定义  这是返回json 的controller
@RequestMapping("/news")  //2.定义路由   接口统一路由 数据库表
public class NewsController {             //实现前后台数据交互

    @Resource        //将mapper引入到controller 这种方法不规范    一般controller引入service类，service引入mapper
    NewsMapper newsMapper;


    //新增接口
    @PostMapping  //3.定义一个post接口
    public Result <?>save(@RequestBody News news)
    {
        news.setTime(new Date());  //服务端设置当前时间 前台自动获取当前值
        newsMapper.insert(news)  ;
        return Result.success();
    }

    //删除接口
    @DeleteMapping("/{id}")      //  /{id} 占位符  多个的话  /{id} /{aa}
    public Result <?>delete(@PathVariable Long id) {      //多个的话  @PathVariable Long id @PathVariable String aa
        newsMapper.deleteById(id);
        return Result.success();
    }

    //更新接口
    @PutMapping
    public Result <?>update(@RequestBody News news) {
        newsMapper.updateById(news);
        return Result.success();
    }
//未知
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(newsMapper.selectById(id));
    }
    
    //查询接口
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,/**当前页,数据页面当前翻到的页码*/
                              @RequestParam(defaultValue = "10") Integer pageSize   /**每一页多少条 10页每条 还是5页每条 */
                             ,@RequestParam(defaultValue = "") String search   /** 模糊查询*/
    ){
        LambdaQueryWrapper<News> wrapper = Wrappers.<News>lambdaQuery().orderByDesc(News::getTitle);
        if (StrUtil.isNotBlank(search)){
            wrapper.like(News::getTitle ,search);
        }

        Page<News> newsPage = newsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

         return Result.success(newsPage);
    }


}
