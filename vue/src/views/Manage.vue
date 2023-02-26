<template>
  <el-container style="min-height: 100%;">
    <el-aside
        style="width:200px; height: 700px; background-color: rgb(238, 241, 246); box-shadow: rgba(0, 21, 41, 0.35) 2px 0 6px ">
      <Aside :logoTextShow="logoTextShow"/>
    </el-aside>

    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px;">
        <Header/>
      </el-header>

      <el-main>
        <!-- 表示当前页面的子路由会在 <router-view />里面展示 -->
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>

import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'Manage',
  data() {
    return {
      logoTextShow: true
    }
  },
  components: {
    Aside,
    Header
  }
}
</script>

<style>
.headerBg {
  background: #ccc !important;
}
</style>