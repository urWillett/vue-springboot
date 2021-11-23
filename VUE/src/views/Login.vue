<template>
  <div style="width: 100%;height: 100vh;background: black;overflow: hidden">
    <div style="width: 400px;margin: 100px auto">
      <div style="color: #cccccc;font-size: 30px;text-align: center;padding: 30px 0">欢迎登陆</div>
      <el-form ref="form" :model="form" size="normal" >
        <el-form-item>
          <el-input  class="el-icon-user-solid" placeholder="用户名" v-model="form.username">
<!--            <el-icon><avatar style="width: 1em; height: 1em; margin-right: 8px;"/> </el-icon>-->
          </el-input>
        </el-form-item>
        <el-form-item >
          <el-input  class="el-icon-lock" placeholder="密码" v-model="form.password" show-password>
<!--            <el-icon><lock style="width: 1em; height: 1em; margin-right: 8px;"/> </el-icon>-->
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="login">登录</el-button>
        </el-form-item>
        </el-form>
    </div>

  </div>
</template>

<script>


import Avatar from '@/components/Avatar'

import request from "../utils/request";
import Lock from "@element-plus/icons/lib/Lock";
import {ElMessage} from "element-plus";


export default {
  name: "Login",
  components:{

  },
  data(){
    return{
      form:{}
    }
  },
  methods:{
    login(){
      request.post("/api/people/login",this.form).then(res => {
        if (res.code === '0') {
          ElMessage.success({
            type: "success",
            message: "登录成功"
          })
          this.$router.push("/")  //登陆成功之后进行页面的跳转，跳转到主页面
        } else {
          ElMessage.error({
            type: "error",
            message: res.msg
          })
        }
      })
    }
  }

}
</script>

<style scoped>

</style>