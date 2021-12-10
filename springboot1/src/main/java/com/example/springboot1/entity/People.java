package com.example.springboot1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@TableName("people")  //数据库名称
@Data  //lombok注释
public class People {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String nickName;
    private String password;
    private Integer age;
    private String sex;                //使用了lombokJava简化工具，不用get/set
    private String address;
    private Integer role;


    @TableField(exist = false)
    private List<Book> bookList;
}
