<script setup>
import HelloWorld from './components/HelloWorld.vue'
import Catalog from './components/Catalog.vue';
</script>

<template>
  <router-view /> <!-- 路由匹配的组件会渲染在这里 -->

  <!-- <Catalog class="catalog"/> -->
</template>

<style scoped>

.catalog {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1000; /* 确保Catalog在其他内容之上 */
  /* 可以根据需要添加其他样式，如宽度、背景等 */
  background-color: bisque;
}

.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
