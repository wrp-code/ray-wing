<script setup>
import Menu from './components/Menu.vue'

</script>

<template>
 
  <div class="container">
    <Menu />
    <div class="content">
      <!-- 你的Markdown内容 -->
      <router-view /> <!-- 路由匹配的组件会渲染在这里 -->
    </div>

   
  </div>
</template>

<script>

</script>

<style scoped>
.container {
  height: 90vh;
  width: 60vw;
}

</style>
