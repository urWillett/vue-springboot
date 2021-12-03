package com.example.springboot1.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot1.common.Result;
import com.example.springboot1.entity.Book;
import com.example.springboot1.mapper.BookMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin  //浏览器跨域注释
@RestController   //1.定义  这是返回json 的controller
@RequestMapping("/book")  //2.定义路由   接口统一路由 数据库表
public class BookController {             //实现前后台数据交互

    @Resource        //将mapper引入到controller 这种方法不规范    一般controller引入service类，service引入mapper
    BookMapper bookMapper;


    //新增接口
    @PostMapping  //3.定义一个post接口
    public Result <?>save(@RequestBody Book book) //RequestBody 将前台的传过来的json传化为Java对象
    {           //<?>  给Result一个泛型  ? 表示任何一种类型

        bookMapper.insert(book)  ;//通过BookMapper实现数据库的新增 BookMapper.insert  mybatis-plus提供的方法
        return Result.success();  //返回一个信息  success
    }

    //删除接口
    @DeleteMapping("/{id}")      //  /{id} 占位符  多个的话  /{id} /{aa}
    public Result <?>delete(@PathVariable Long id) {      //多个的话  @PathVariable Long id @PathVariable String aa
        bookMapper.deleteById(id);
        return Result.success();
    }

    //更新接口
    @PutMapping
    public Result <?>update(@RequestBody Book Book) {
        bookMapper.updateById(Book);
        return Result.success();
    }
//未知
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        return Result.success(bookMapper.selectById(id));
    }
    
    //查询接口
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,/**当前页,数据页面当前翻到的页码*/
                              @RequestParam(defaultValue = "10") Integer pageSize   /**每一页多少条 10页每条 还是5页每条 */
                             ,@RequestParam(defaultValue = "") String search   /** 模糊查询*/
    ){
        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery().orderByDesc(Book::getName);
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Book::getName ,search);
        }

        Page<Book> BookPage = bookMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);

         return Result.success(BookPage);
    }


}
