<template>
  <div class="article-list-container">
    <div class="query-param">
      目录分类：<el-cascader :options="options" @change="bookList" />
    </div>

    <div v-if="!showList" class="book-context">
        <el-card class="book-item" v-for="book in books" :key="book.id" 
        @click="queryArticleList(book.id)">
          <div>{{ book.name }}</div>
          <div style="font-size: 18px;color: grey;">文章数量：{{ book.articleCount }} 篇</div>
        </el-card>
    </div>

    <div  v-if="showList">
      <h2 class="list-title">文章列表</h2>
      <ul v-if="list.length > 0" class="article-list">
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
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue"
import { useRouter } from 'vue-router'
import { getArticleList } from '@/api/article.js'
import {getBooks, catalogTree} from '@/api/catalog.js'

const list = ref([])
const options = ref([])
const showList = ref(false)
const books = ref([])

onMounted(async () => {
  // 默认查询所有的博客
  await bookList(null)

  // 博客目录分类
  const tree = await catalogTree()
  const res = tree.map(e => (convert(e)))
  res.unshift({
    value: '',
    label: '全部',
    childreen: []
  })
  options.value = res
})

// 转为前端需要的格式
function convert(catalog) {
  if(catalog != null) {
    return {
      value: catalog.id,
      label: catalog.name,
      childreen: convert(catalog.child)
    }
  }
}

// 查询博客列表
async function bookList(e) {
  showList.value = false
  books.value = await getBooks(e == null ? '':e[0])
}

// 查询文章列表
async function queryArticleList(bookId) {
  try {
    const data = await getArticleList(bookId)
    list.value = data || [] // 确保list始终是数组
    if(data.length > 0) {
      showList.value = !showList.value
    }
  } catch (error) {
    console.error('获取文章列表失败:', error)
    list.value = [] // 出错时清空列表
  }
}

const router = useRouter()

// 调转到文章详情
function getArticleDetail (id) {
    // 跳转到文章详情
    router.push({
      path: '/article/detail',
      query: { id },
    });
}
</script>

<style lang="scss" scoped>
.query-param {
  margin-bottom: 20px;
}
.book-context {
  display: flex;
  justify-content: center;        /* 居中对齐 */
  align-content: center;
}

.book-item {
  margin: 10px 10px;
  font-size: 26px;
}
.book-item:hover {
    transform: scale(1.05);
    box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);
    cursor: pointer;
}


.article-list-container {
  padding: 20px;
  height: 80vh;
  width: 800px;
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
        min-width: 800px;
        cursor: pointer;
        
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