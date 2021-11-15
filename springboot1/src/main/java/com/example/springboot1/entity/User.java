package com.example.springboot1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")  //数据库名称
@Data  //lombok注释
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String nick_name;
    private String password;
    private Integer age;
    private String sex;                //使用了lombokJava简化工具，不用get/set
    private String address;
}
