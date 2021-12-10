<!--头部菜单-->
<template>
 <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
<!--   头部格式：高，行高，边框，下拉框，布局-->
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: dodgerblue">后台管理</div>
    <div style="flex: 1"></div>
    <div style="width: 150px">
      <el-dropdown split-button type="primary">
        <span >
          {{ people.username }} <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
<!--        小汤-->
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/person')">个人信息</el-dropdown-item>
<!--            @click="$router.push('/person')"-->
            <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
<!--   表单的左中右 三部分-->
 </div>
</template>

<script>
import { ArrowDown } from '@element-plus/icons'

export default {
  name: "Header",
  data() {
    return {
      people: {}
    }
  },
  created() {
    let str = sessionStorage.getItem("people") || "{}"
    this.people = JSON.parse(str)
  }

}
</script>

<style scoped>

</style>