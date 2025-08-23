<template>
  <div class="markdown-toc">
    <div v-if="toc.length > 0" class="toc-container">
      <h3 class="toc-title">目录</h3>
      <ul class="toc-list">
        <li 
          v-for="(item, index) in toc" 
          :key="index" 
          :class="['toc-item', `toc-level-${item.level}`]"
          :style="{ paddingLeft: `${item.level * 12}px` }"
        >
          <a 
            :href="`#${item.anchor}`" 
            @click.prevent="scrollToAnchor(item.anchor)"
            :class="{ active: activeAnchor === item.anchor }"
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
import { ref, onMounted, onUnmounted, computed, onUpdated } from 'vue';

const props = defineProps(['markdownContent'])

const toc = ref([])

onUpdated(async()=> {
  generateTOC()
})

const activeAnchor = ref('');

// 解析Markdown生成目录
const generateTOC = () => {
  const headers = [];
  
  props.markdownContent.map(content => {
    headers.push({
          level: content.level,
          title: content.title,
          anchor: content.title
        });
  });
  
  toc.value = headers;
};

// 平滑滚动到锚点
const scrollToAnchor = (anchor) => {
  const element = document.getElementById(anchor);
  if (element) {
    activeAnchor.value = anchor;
    window.scrollTo({
      top: element.offsetTop - 20,
      behavior: 'smooth'
    });
    
    // 更新URL hash（不触发滚动）
    history.pushState(null, null, `#${anchor}`);
  }
};

// 监听滚动，高亮当前可见的标题
const handleScroll = () => {
  const headers = toc.value.map(item => ({
    id: item.anchor,
    element: document.getElementById(item.anchor)
  })).filter(item => item.element);
  
  let currentActive = '';
  for (const header of headers) {
    const rect = header.element.getBoundingClientRect();
    if (rect.top >= 0 && rect.top <= 200) {
      currentActive = header.id;
      break;
    }
  }
  
  if (currentActive && currentActive !== activeAnchor.value) {
    activeAnchor.value = currentActive;
  }
};

onMounted(() => {
  // generateTOC();
  window.addEventListener('scroll', handleScroll);
  
  // 检查初始hash
  if (window.location.hash) {
    const anchor = window.location.hash.substring(1);
    scrollToAnchor(anchor);
  }
});

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll);
});


</script>

<style scoped>
.markdown-toc {
  position: sticky;
  top: 20px;
  max-height: calc(100vh - 40px);
  overflow-y: auto;
  padding: 10px;
  border-left: 1px solid #eee;
  width: 250px;
}

.toc-container {
  padding: 10px;
}

.toc-title {
  margin: 0 0 10px 0;
  font-size: 1em;
  font-weight: bold;
}

.toc-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.toc-item {
  margin: 4px 0;
  transition: all 0.2s ease;
}

.toc-item a {
  color: #666;
  text-decoration: none;
  display: block;
  padding: 4px 8px;
  border-radius: 4px;
  transition: all 0.2s ease;
}

.toc-item a:hover {
  color: #333;
  background-color: #f5f5f5;
}

.toc-item a.active {
  color: #42b983;
  font-weight: bold;
  background-color: #f0fff0;
}

.no-toc {
  color: #999;
  font-style: italic;
}

/* 不同级别标题的样式 */
.toc-level-1 {
  font-weight: bold;
}

.toc-level-2 {
  font-weight: normal;
}

.toc-level-3 {
  font-size: 0.9em;
}

.toc-level-4 {
  font-size: 0.85em;
  opacity: 0.9;
}

.toc-level-5, .toc-level-6 {
  font-size: 0.8em;
  opacity: 0.8;
}
</style>