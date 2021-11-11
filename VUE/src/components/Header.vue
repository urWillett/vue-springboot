<!--头部菜单-->
<template>
 <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
<!--   头部格式：高，行高，边框，下拉框，布局-->
    <div style="width: 200px;padding-left: 30px;font-weight: bold;color: dodgerblue">后台管理</div>
    <div style="flex: 1"></div>
    <div style="width: 150px">
<!--      <el-dropdown>-->
<!--    <span class="el-dropdown-link">-->
<!--      小汤-->
<!--      <el-icon class="el-icon&#45;&#45;right">-->
<!--        <arrow-down />-->
<!--      </el-icon>-->
<!--    </span>-->
<!--        <template #dropdown>-->
<!--          <el-dropdown-menu>-->
<!--            <el-dropdown-item>个人信息</el-dropdown-item>-->
<!--            <el-dropdown-item>退出系统</el-dropdown-item>-->
<!--          </el-dropdown-menu>-->
<!--        </template>-->
<!--      </el-dropdown>-->
      <el-dropdown split-button type="primary" @click="handleClick">
        小汤
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>18</el-dropdown-item>
            <el-dropdown-item>女</el-dropdown-item>
            <el-dropdown-item>学生</el-dropdown-item>
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
  components:{
    ArrowDown,
  },
  setup() {
    const handleClick = () => {
      alert('点我干嘛')
    }
    return {
      handleClick,
    }
  },
}
</script>

<style scoped>

</style>