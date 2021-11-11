<template>
  <div>
<!--    头部表单-->
    <Header />
<!--    主体-->
    <div style="display: flex">
<!--      侧边栏-->
      <Aside />
<!--      内容区域-->
      <router-view style="flex: 1"/>
    </div>
  </div>

</template>

<style>

</style>

<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";
//alt+enter 导入Header

//导入Header
export default {
  name:"layout",
  components:{
    Header,
    Aside
  }
}
</script>