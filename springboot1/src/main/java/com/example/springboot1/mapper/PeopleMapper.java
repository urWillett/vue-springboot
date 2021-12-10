package com.example.springboot1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot1.entity.People;

public interface PeopleMapper extends BaseMapper<People> {
    // 一对多查询
    Page<People> findPage(Page<People> page);
}
