package com.example.springboot1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName("book")  //数据库名称
@Data  //lombok注释
public class Book {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private BigDecimal price;
    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")  //日期格式化
    private Date createTime;
    private String cover;

    private Integer peopleId;
}

                   //使用了lombokJava简化工具，不用get/set

