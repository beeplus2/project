<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           router
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <b style="color: white; font-size: large">COLG SAM系统后台</b>
    </div>
    <el-menu-item index="/">
      <i class="el-icon-s-home"></i>
      <span slot="title">后台首页</span>
    </el-menu-item>
    <el-menu-item index="/type">
      <i class="el-icon-s-operation"></i>
      <span slot="title">社团类型管理</span>
    </el-menu-item>
    <el-menu-item index="/club">
      <i class="el-icon-menu"></i>
      <span slot="title">社团信息管理</span>
    </el-menu-item>
    <el-menu-item index="/user">
    <i class="el-icon-s-custom"></i>
    <span slot="title">用户信息管理</span>
    </el-menu-item>
    <el-menu-item index="check">
      <i class="el-icon-message"></i>
      <span slot="title">建团申请管理</span>
    </el-menu-item>
    <el-menu-item index="/activity">
      <i class="el-icon-s-flag"></i>
      <span slot="title">社团活动管理</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style scoped>

</style>