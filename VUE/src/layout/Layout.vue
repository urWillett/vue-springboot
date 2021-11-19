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

<script>
import Header from "@/components/Header";
import Aside from "@/components/Aside";

//导入Header

export default {
  name: "Layout",
  components:{
    Header,
    Aside
  }
}
</script>

<style scoped>

</style>