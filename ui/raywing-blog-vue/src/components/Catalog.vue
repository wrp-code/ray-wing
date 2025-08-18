<template>
  <div class="catalog-container">
    <h2 class="catalog-title">文章目录</h2>
    <ul v-if="catalogList.length" class="catalog-list">
      <li v-for="item in catalogList" :key="item.id" class="catalog-item">
        <div class="item-header" @click="toggleItem(item)">
          <span>{{ item.name }}</span>
          <span v-if="item.child?.length" class="arrow-icon">
            {{ expandedItems.includes(item.id) ? '▼' : '►' }}
          </span>
        </div>
        <ul 
          v-if="item.child && item.child.length" 
          class="sub-catalog"
          :style="{ display: expandedItems.includes(item.id) ? 'block' : 'none' }"
        >
          <li v-for="child in item.child" :key="child.id" class="sub-item">
            {{ child.name }}
          </li>
        </ul>
      </li>
    </ul>
    <div v-else class="empty-tip">暂无目录数据</div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue"
import { catalogTree } from "../api/catalog"

const catalogList = ref([])
const expandedItems = ref([]) // 存储已展开的菜单项ID

onMounted(async () => {
  try {
    const data = await catalogTree()
    if (data && Array.isArray(data)) {
      catalogList.value = data
    }
  } catch (error) {
    console.error('获取目录数据失败:', error)
  }
})

// 切换菜单项展开/折叠状态
const toggleItem = (item) => {
  const index = expandedItems.value.indexOf(item.id)
  if (index === -1) {
    expandedItems.value.push(item.id) // 展开
  } else {
    expandedItems.value.splice(index, 1) // 折叠
  }
}
</script>

<style lang="scss" scoped>
.catalog-container {
  padding: 16px;
  background: #f8f8f8;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  
  .catalog-title {
    margin: 0 0 12px 0;
    font-size: 18px;
    color: #333;
  }
  
  .catalog-list {
    list-style: none;
    padding: 0;
    margin: 0;
    
    .catalog-item {
      padding: 8px 0;
      border-bottom: 1px solid #eee;
      
      &:last-child {
        border-bottom: none;
      }
      
      .item-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        cursor: pointer;
        padding: 4px 0;
        
        &:hover {
          color: #42b983;
        }
        
        .arrow-icon {
          font-size: 12px;
          margin-left: 8px;
        }
      }
    }
    
    .sub-catalog {
      padding-left: 16px;
      margin-top: 4px;
      transition: all 0.3s ease;
      
      .sub-item {
        padding: 4px 0;
        font-size: 14px;
        color: #666;
        cursor: pointer;
        
        &:hover {
          color: #42b983;
        }
      }
    }
  }
  
  .empty-tip {
    color: #999;
    font-size: 14px;
    text-align: center;
    padding: 16px 0;
  }
}
</style>