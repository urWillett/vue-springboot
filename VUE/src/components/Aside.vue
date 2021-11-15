<template>
  <div>
    <el-col :span="24">
      <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
      >
        <el-sub-menu index="1">
          <template #title>
            <el-icon><location /></el-icon>
            <span>导航 One</span>
          </template>
          <el-menu-item-group title="组 One">
            <el-menu-item index="1-1">项目 one</el-menu-item>
            <el-menu-item index="1-2">项目 two</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="组 Two">
            <el-menu-item index="1-3">项目 three</el-menu-item>
          </el-menu-item-group>
          <el-sub-menu index="1-4">
            <template #title>项目 four</template>
            <el-menu-item index="1-4-1">子项目 one</el-menu-item>
          </el-sub-menu>
        </el-sub-menu>
        <el-menu-item index="2">
          <el-icon><icon-menu /></el-icon>
          <span>导航 Two</span>
        </el-menu-item>
        <el-menu-item index="3" disabled>
          <el-icon><document /></el-icon>
          <span>导航 Three</span>
        </el-menu-item>
        <el-menu-item index="4">
          <el-icon><setting /></el-icon>
          <span>导航 Four</span>
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
  Setting,
} from '@element-plus/icons'

export default {
  name: "Aside",
  components: {
    Location,
    Document,
    Setting,
    IconMenu,
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