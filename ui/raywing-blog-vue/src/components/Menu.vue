<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    router
  >
    <el-menu-item index="0">
      <img
        style="width: 100px"
        src="/public/vite.svg"
        alt="Element logo"
      />
    </el-menu-item>
    <el-menu-item index="/">首页</el-menu-item>
    <el-menu-item index="/article/list">笔记</el-menu-item>
    <el-menu-item index="/project/list">项目</el-menu-item>
    <el-menu-item index="/drawing">画册</el-menu-item>
    <el-menu-item index="/about">介绍</el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const activeIndex = computed(() => route.path)
</script>

<style scoped>
.el-menu--horizontal > .el-menu-item:nth-child(1) {
  margin-right: auto;
}
</style>