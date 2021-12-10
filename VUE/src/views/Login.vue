<template>
<!--  <div style="background: url(../assets/Tencent.png) no-repeat;height: 100vh;width: 100%; overflow: hidden">-->
  <div style="width: 100%;height: 100vh;background: black;overflow: hidden">
    <div style="width: 400px;margin: 100px auto">
      <div style="color: #cccccc;font-size: 30px;text-align: center;padding: 30px 0">欢迎登陆</div>
      <el-form ref="form" :model="form" :rules="rules" size="normal" >
        <el-form-item prop="username">
          <el-input class="demo-type"  placeholder="用户名" v-model="form.username">
            <el-icon><avatar /></el-icon>
          </el-input>
        </el-form-item>
        <el-form-item  prop="password">
          <el-input  class="el-icon-lock" placeholder="密码" v-model="form.password" show-password>
            <el-icon><lock /></el-icon>
          </el-input>
        </el-form-item>

        <el-form-item>
          <div style="display: flex">
             <el-input prefix-icon="el-icon-key" v-model="form.validCode" style="width: 50%;" placeholder="请输入验证码"></el-input>
                <ValidCode @input="createValidCode"></ValidCode>
          </div>
        </el-form-item>

        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="login">登录</el-button>
        </el-form-item>
        </el-form>
    </div>

  </div>
</template>

<script>

import {Avatar, Lock, UserFilled} from '@element-plus/icons'

import request from "../utils/request";

import {ElMessage} from "element-plus";
import ValidCode from "@/components/ValidCode";


export default {
  name: "Login",
  components:{
    ValidCode
  },
  data(){
    return{
      form:{},

      rules: {
        username: [
          {
            required: true,
            message: '请输入用户名',
            trigger: 'blur',
          },],
        password: [
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          },
        ],

      },
      validCode: ''
      // 加背景图片
      // bg: {
      //   backgroundImage: "url(" + require("@/assets/bg.jpg") + ")",
      //   backgroundRepeat: "no-repeat",
      //   backgroundSize: "100% 100%"
      // }
    }
  },
  created() {
    sessionStorage.removeItem("people")
  },
  methods:{
    //接收验证码组件提交的 4位验证码
    createValidCode(data){
      this.validCode = data
    },
    login(){
      this.$refs['form'].validate((valid) => {
        if (valid) {
            if(!this.form.validCode){
              ElMessage.error("请填写验证码")
              return
            }
            if (this.form.validCode.toLowerCase()!== this.validCode.toLowerCase()){
              ElMessage.error("验证码错误")
            return
          }
          request.post("/api/people/login",this.form).then(res => {
            if (res.code === '0') {
              ElMessage.success({
                type: "success",
                message: "登录成功"
              })
              sessionStorage.setItem("people", JSON.stringify(res.data))  // 缓存用户信息
              this.$router.push("/")  //登陆成功之后进行页面的跳转，跳转到主页面
            } else {
              ElMessage.error({
                type: "error",
                message: res.msg
              })
            }
          })
        }
      })
    }
  }

}
</script>

<style scoped>

/*.ValidCode{*/
/*  display: flex;*/
/*  justify-content: center;*/
/*  align-items: center;*/
/*  cursor: pointer;*/
/*  span{*/
/*    display: inline-block;*/
/*  }*/
/*}*/


</style>