<template>
  <div class="markdown-toc">
    <div v-if="toc.length > 0" class="toc-container">
      <h3 class="toc-title">目录</h3>
      <ul class="toc-list">
        <li 
          v-for="(item, index) in toc" 
          :key="index" 
          :class="['toc-item', `toc-level-${item.level}`]"
        >
          <a 
            :id="item.level"
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
import { ref, onMounted, onUnmounted, watch } from 'vue';

const props = defineProps(['markdownContent'])

const toc = ref([])
const activeAnchor = ref('');

// 监听markdownContent变化
watch(() => props.markdownContent, () => {
  const headers = [];
  
  if (props.markdownContent && Array.isArray(props.markdownContent)) {
    let increacing = 1
    props.markdownContent.forEach(content => {
      if (content.level && content.title) {
        headers.push({
          level: content.level,
          title: content.title,
          anchor: content.level + (increacing++)
        });
      }
    });
  }
  
  toc.value = headers;
  if (window.location.hash) {
    const hash = window.location.hash.substring(1);
    activeAnchor.value = hash;
  }
}, { immediate: true, deep: true });


// 平滑滚动到锚点
const scrollToAnchor = (anchor) => {
  const element = document.getElementById(anchor);
  
  if (element) {
    // 更新活动锚点
    activeAnchor.value = anchor;
    
    // 计算精确的滚动位置
    const elementPosition = element.getBoundingClientRect().top + window.pageYOffset;
    const offsetPosition = elementPosition - 80; // 减去顶部导航栏高度
    
    // 平滑滚动
    window.scrollTo({
      top: offsetPosition,
      behavior: 'smooth'
    });
    
    // 更新URL hash（不触发滚动）
    history.replaceState(null, null, `#${anchor}`);
    
    // 滚动结束后再次确认位置（处理动态内容）
    setTimeout(() => {
      const finalPosition = element.getBoundingClientRect().top + window.pageYOffset - 80;
      if (Math.abs(window.pageYOffset - finalPosition) > 5) {
        window.scrollTo({
          top: finalPosition,
          behavior: 'smooth'
        });
      }
    }, 300);
  }
};

// 监听滚动，高亮当前可见的标题
const handleScroll = () => {
  if (toc.value.length === 0) return;
  
  const headers = toc.value.map(item => ({
    id: item.anchor,
    element: document.getElementById(item.anchor),
    level: item.level
  })).filter(item => item.element);
  
  if (headers.length === 0) return;
  
  // 找到最接近视口顶部的标题
  let closestHeader = null;
  let minDistance = Infinity;
  
  for (const header of headers) {
    const rect = header.element.getBoundingClientRect();
    const distance = Math.abs(rect.top);
    
    if (rect.top <= 100 && distance < minDistance) {
      minDistance = distance;
      closestHeader = header;
    }
  }
  
  // 如果没有标题在视口上方，选择第一个
  if (!closestHeader) {
    const firstVisible = headers.find(header => 
      header.element.getBoundingClientRect().top >= 0
    );
    closestHeader = firstVisible || headers[0];
  }
  
  if (closestHeader && closestHeader.id !== activeAnchor.value) {
    activeAnchor.value = closestHeader.id;
    history.replaceState(null, null, `#${closestHeader.id}`);
  }
};

// 防抖滚动监听
let scrollTimeout = null;
const debouncedHandleScroll = () => {
  clearTimeout(scrollTimeout);
  scrollTimeout = setTimeout(handleScroll, 50);
};

onMounted(() => {
  window.addEventListener('scroll', debouncedHandleScroll);
  
  // 初始检查hash
  if (window.location.hash) {
    const hash = window.location.hash.substring(1);
    scrollToAnchor(hash);
  }
});

onUnmounted(() => {
  window.removeEventListener('scroll', debouncedHandleScroll);
  clearTimeout(scrollTimeout);
});
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