<template>
<!--  <div style="background: url(../assets/Tencent.png) no-repeat;height: 100vh;width: 100%; overflow: hidden">-->
  <div style="width: 100%;height: 100vh;background: black;overflow: hidden">
    <div style="width: 400px;margin: 100px auto">
      <div style="color: #cccccc;font-size: 30px;text-align: center;padding: 30px 0">欢迎注册</div>
      <el-form ref="form" :model="form" :rules="rules" size="normal" >
        <el-form-item prop="username">
          <el-input  class="el-icon-user-solid" placeholder="用户名" v-model="form.username">
<!--            <el-icon><avatar style="width: 1em; height: 1em; margin-right: 8px;"/> </el-icon>-->
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input  class="el-icon-lock" placeholder="密码" v-model="form.password" show-password>
<!--            <el-icon><lock style="width: 1em; height: 1em; margin-right: 8px;"/> </el-icon>-->
          </el-input>
        </el-form-item>

        <el-form-item prop="confirm">
          <el-input  class="el-icon-lock" placeholder="密码" v-model="form.confirm" show-password>
            <!--            <el-icon><lock style="width: 1em; height: 1em; margin-right: 8px;"/> </el-icon>-->
          </el-input>
        </el-form-item>

        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="register">注册</el-button>
        </el-form-item>
        </el-form>
    </div>

  </div>
</template>

<script>


import request from "../utils/request";
import {ElMessage} from "element-plus";


export default {
  name: "Login",
  components:{

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
        confirm: [
          {
            required: true,
            message: '请确认密码',
            trigger: 'blur',
          },
        ],

      }
    }
  },
  methods:{
    register(){
      if (this.form.password !== this.form.confirm) {
        ElMessage.error({
          type: "error",
          message: '2次密码不匹配'
        })
        return
      }

      this.$refs['form'].validate((valid) => {
        if (valid) {
          request.post("/api/people/register",this.form).then(res => {
            if (res.code === '0') {
              ElMessage.success({
                type: "success",
                message: "注册成功"
              })
              this.$router.push("/login")  //注册成功后 路由到登录界面
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

</style>