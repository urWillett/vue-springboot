package com.example.springboot1;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot1.entity.People;
import com.example.springboot1.mapper.PeopleMapper;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class Springboot1ApplicationTests {

    @Resource
    PeopleMapper mapper;
    @Test
    void contextLoads() {
       People peo= mapper.selectById(1);
        QueryWrapper<People> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank("小"),"nick_name","小");
        mapper.selectList(queryWrapper).stream().forEach(System.out::println);

    }

    @Test
    void selectPage(){
        LambdaQueryWrapper<People> wrapper = Wrappers.<People>lambdaQuery().orderByAsc(People::getNickName);
        wrapper.likeRight(People::getNickName ,"a");
        IPage<People> page=mapper.selectPage(new Page<>(1,10),wrapper);
        System.out.println(page.getTotal());
        page.getRecords().forEach(System.out::println);
    }

}
