<template>
  <div>
    <el-col :span="24">
      <el-menu
          :default-active="path"
          router
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
      >
<!--   加入router   根据index中属性进行跳转
        default-active="home"  高亮显示
        : 是简化v-bind 写法-->
        <el-sub-menu index="1">
          <template #title>
            <el-icon><location /></el-icon>
            <span>系统管理</span>
          </template>
        </el-sub-menu>
        <el-menu-item index="/home">
          <el-icon><user-filled /></el-icon>
          <span>用户管理</span>
        </el-menu-item>
        <el-menu-item index="3" disabled>
          <el-icon><document /></el-icon>
          <span>导航 Three</span>
        </el-menu-item>
        <el-menu-item index="/book">
          <el-icon><icon-menu /></el-icon>
          <span>书籍管理</span>
        </el-menu-item>

        <el-menu-item index="/news">
          <el-icon><message /></el-icon>
          <span>新闻管理</span>
        </el-menu-item>

      </el-menu>
    </el-col>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import {
  Location,
  Document,
  Menu as IconMenu,
  Setting, Avatar, UserFilled, Menu, Message,

} from '@element-plus/icons'
import request from "@/utils/request";

export default {
  name: "Aside",
  components: {
    Location,
    Document,
    Setting,
    IconMenu,
    Avatar,
    UserFilled,
    Menu,  //图标
    Message,

  },
  data(){
    return {
      user: {},  //权限管理 根据v-if="people.role === 1" 来判断是否显示
      path: this.$route.path  //设置默认高亮的菜单
    }
  },
  //权限  查询信息
  created() {
    let peopleStr = sessionStorage.getItem("people") || "{}"
    this.people = JSON.parse(peopleStr)

    //请求服务端，确认当前登录用户的 合法信息
    request.get("/people/"+ this.people.id).then(res =>{
      if (res.code === '0'){
        this.people = res.data
      }
    })
  },
  setup() {
    const handleOpen = (key, keyPath) => {
      console.log(key, keyPath)
    }
    const handleClose = (key, keyPath) => {
      console.log(key, keyPath)
    }
    return {
      handleOpen,
      handleClose,
    }
  },

}
</script>

<style scoped>

</style>