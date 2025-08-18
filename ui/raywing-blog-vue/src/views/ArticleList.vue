<template>
  <div class="article-list-container">
    <h2 class="list-title">文章列表</h2>
    <ul v-if="list.length" class="article-list">
      <li v-for="article in list" :key="article.id" class="article-item">
        <div class="article-card" @click="getArticleDetail(article.id)">
          <h3 class="article-title">{{ article.title }}</h3>
          <div class="article-meta">
            <span class="article-id">ID: {{ article.id }}</span>
          </div>
        </div>
      </li>
    </ul>
    <div v-else class="empty-tip">暂无文章数据</div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue"
import { useRouter } from 'vue-router'
import { getArticleList } from '@/api/article.js'

const list = ref([])

onMounted(async () => {
  try {
    const data = await getArticleList('1957324507219648513')
    list.value = data || [] // 确保list始终是数组
  } catch (error) {
    console.error('获取文章列表失败:', error)
    list.value = [] // 出错时清空列表
  }
})

const router = useRouter()

function getArticleDetail (id) {
    // 跳转到文章详情
    router.push({
      path: '/article/detail',
      query: { id },
    });
}
</script>

<style lang="scss" scoped>
.article-list-container {
  padding: 20px;
  max-width: 800px;
  margin: 0 auto;
  
  .list-title {
    margin: 0 0 20px 0;
    font-size: 24px;
    color: #2c3e50;
    text-align: center;
    padding-bottom: 10px;
    border-bottom: 1px solid #eee;
  }
  
  .article-list {
    list-style: none;
    padding: 0;
    margin: 0;
    display: grid;
    gap: 16px;
    
    .article-item {
      .article-card {
        background: #fff;
        border-radius: 8px;
        padding: 16px;
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease;
        
        &:hover {
          transform: translateY(-2px);
          box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
        }
        
        .article-title {
          margin: 0 0 8px 0;
          font-size: 18px;
          color: #34495e;
        }
        
        .article-meta {
          display: flex;
          justify-content: space-between;
          align-items: center;
          
          .article-id {
            font-size: 12px;
            color: #95a5a6;
          }
        }
      }
    }
  }
  
  .empty-tip {
    text-align: center;
    padding: 40px 0;
    color: #95a5a6;
    font-size: 16px;
  }
}

@media (max-width: 768px) {
  .article-list-container {
    padding: 15px;
    
    .list-title {
      font-size: 20px;
    }
    
    .article-list {
      gap: 12px;
      
      .article-item .article-card {
        padding: 12px;
        
        .article-title {
          font-size: 16px;
        }
      }
    }
  }
}
</style>