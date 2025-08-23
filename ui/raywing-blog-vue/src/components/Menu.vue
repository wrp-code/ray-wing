<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
  >
    <el-menu-item index="0">
      <img
        style="width: 100px"
        src="/public/vite.svg"
        alt="Element logo"
      />
    </el-menu-item>
    <router-link to="/"><el-menu-item index="1">首页</el-menu-item></router-link>
    <router-link to="/article/list"><el-menu-item index="2">专栏</el-menu-item></router-link>
    <router-link to="/project/list"><el-menu-item index="3">项目</el-menu-item></router-link>
    <router-link to="/drawing"><el-menu-item index="4">绘图</el-menu-item></router-link>
    <router-link to="/about"><el-menu-item index="5">介绍</el-menu-item></router-link>
    
  </el-menu>
</template>

<script lang="ts" setup>
import { ref, onBeforeMount } from 'vue'
import { useRoute } from 'vue-router'

const activeIndex = ref('1')
const route = useRoute()

onBeforeMount(()=> {
  const path = route.path;
  if(path == '/') {
    activeIndex.value = '1'
  } else if(path == '/article/list') {
    activeIndex.value = '2'
  } else if(path == '/project/list') {
    activeIndex.value = '3'
  } else if(path == '/drawing') {
    activeIndex.value = '4'
  } else if(path == '/about') {
    activeIndex.value = '5'
  }
})

</script>

<style scoped>
.el-menu--horizontal > .el-menu-item:nth-child(1) {
  margin-right: auto;
}
</style>
