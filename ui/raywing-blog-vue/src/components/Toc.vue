<template>
  <div class="markdown-toc">
    <div v-if="toc.length > 0" class="toc-container">
      <h3 class="toc-title">目录</h3>
      <ul class="toc-list">
        <li 
          v-for="(item, index) in toc" 
          :key="index" 
          :class="['toc-item', `toc-level-${item.level}`]"
          @click="scrollToAnchor(item.anchor)"
        >
          <a 
            :href="`#${item.anchor}`" 
            :class="{ active: activeAnchor == item.anchor }"
          >
            {{ item.title }}
          </a>
        </li>
      </ul>
    </div>
    <div v-else class="no-toc">
      暂无目录
    </div>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()
const props = defineProps(['markdownContent'])

const toc = ref([])
const activeAnchor = ref('');

// 监听markdownContent变化
watch(() => props.markdownContent, () => {
  const headers = [];
  if (props.markdownContent && Array.isArray(props.markdownContent)) {
    props.markdownContent.forEach(content => {
      if (content.level && content.title) {
        headers.push({
          level: content.level,
          title: content.title,
          anchor: content.anchor
        });
      }
    });
  }
  toc.value = headers;
}, { immediate: true, deep: true });

function scrollToAnchor(anchor) {
  activeAnchor.value = anchor
  router.push(route.path + `#${anchor}`)

  const destination = document.getElementById(anchor)
   window.scrollTo({
        top: destination.offsetTop,
        behavior: 'smooth'
    });
}

</script>

<style scoped>
.markdown-toc {
  position: sticky;
  top: 80px;
  max-height: calc(100vh - 100px);
  overflow-y: auto;
  padding: 10px;
  border-left: 2px solid #e1e4e8;
  width: 280px;
  background: #fafbfc;
  border-radius: 0 8px 8px 0;
}

.toc-container {
  padding: 10px 5px;
}

.toc-title {
  margin: 0 0 15px 0;
  font-size: 1.1em;
  font-weight: 600;
  color: #24292e;
  padding-bottom: 8px;
  border-bottom: 1px solid #e1e4e8;
}

.toc-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.toc-item {
  margin: 6px 0;
  line-height: 1.4;
  transition: all 0.2s ease;
  border-left: 2px solid transparent;
}

.toc-item:hover {
  border-left-color: #42b983;
}

.toc-item a {
  color: #586069;
  text-decoration: none;
  display: block;
  padding: 6px 12px;
  border-radius: 6px;
  transition: all 0.2s ease;
  font-size: 0.95em;
}

.toc-item a:hover {
  color: #24292e;
  background-color: #f6f8fa;
  transform: translateX(2px);
}

.toc-item a.active {
  color: #42b983;
  font-weight: 600;
  background-color: #f0fff4;
  border-left-color: #42b983;
  transform: translateX(4px);
}

.no-toc {
  color: #959da5;
  font-style: italic;
  padding: 20px;
  text-align: center;
}

/* 不同级别标题的缩进和样式 */
.toc-level-1 {
  margin-left: 0;
  font-weight: 600;
}

.toc-level-2 {
  margin-left: 12px;
  font-weight: 500;
}

.toc-level-3 {
  margin-left: 24px;
  font-size: 0.9em;
}

.toc-level-4 {
  margin-left: 36px;
  font-size: 0.85em;
  opacity: 0.9;
}

.toc-level-5 {
  margin-left: 48px;
  font-size: 0.8em;
  opacity: 0.8;
}

.toc-level-6 {
  margin-left: 60px;
  font-size: 0.75em;
  opacity: 0.7;
}

/* 滚动条样式 */
.markdown-toc::-webkit-scrollbar {
  width: 4px;
}

.markdown-toc::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 4px;
}

.markdown-toc::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 4px;
}

.markdown-toc::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}
</style>