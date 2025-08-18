<template>
  <div class="article-container">
    <h2 class="article-title">{{ article.title }}</h2>
    <div 
      v-highlight 
      v-html="compiledMarkdown" 
      class="markdown-body"
    ></div>
  </div>
</template>

<script setup>
import { getArticleDetail } from '../api/article'
import { ref, onMounted, watch, nextTick } from 'vue'
import { marked } from 'marked'
import hljs from 'highlight.js'
import 'highlight.js/styles/github.css'
import 'github-markdown-css/github-markdown.css'

// 初始化marked配置
marked.setOptions({
  breaks: true,  // 换行符转换为<br>
  gfm: true      // 启用GitHub风格的Markdown
})

const article = ref({
  id: null,
  title: '',
  content: ''
})

const compiledMarkdown = ref('')

// 注册指令（带复制功能）
const vHighlight = {
  mounted(el) {
    highlightCodeBlocks(el)
    addCopyButtons(el)
  },
  updated(el) {
    highlightCodeBlocks(el)
    addCopyButtons(el)
  }
}

// 高亮代码块
const highlightCodeBlocks = (el) => {
  const blocks = el.querySelectorAll('pre code')
  blocks.forEach(block => {
    hljs.highlightElement(block)
    block.parentElement.style.position = 'relative'
  })
}

// 添加复制按钮
const addCopyButtons = (el) => {
  const blocks = el.querySelectorAll('pre')
  blocks.forEach(block => {
    if (!block.querySelector('.copy-btn')) {
      const btn = document.createElement('button')
      btn.className = 'copy-btn'
      btn.textContent = '复制'
      btn.onclick = () => {
        const code = block.querySelector('code').innerText
        navigator.clipboard.writeText(code)
          .then(() => {
            btn.textContent = '已复制!'
            setTimeout(() => btn.textContent = '复制', 2000)
          })
      }
      block.appendChild(btn)
    }
  })
}

// 获取文章数据
const fetchArticle = async () => {
  const articleDetail = await getArticleDetail('1957292352636657665')
  article.value = {
    id: articleDetail.id,
    title: articleDetail.title,
    content: articleDetail.content
  }
  compiledMarkdown.value = marked(article.value.content)
}

// 监听内容变化
watch(() => article.value.content, () => {
  compiledMarkdown.value = marked(article.value.content)
  nextTick(() => {
    highlightCodeBlocks(document.querySelector('.markdown-body'))
  })
})

onMounted(async () => {
  await fetchArticle()
})
</script>

<style lang="scss" scoped>
.article-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.article-title {
  color: #333;
  border-bottom: 1px solid #eee;
  padding-bottom: 10px;
}

.markdown-body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif;
  font-size: 16px;
  line-height: 1.6;
  word-wrap: break-word;

  :deep(pre) {
    position: relative;
    background-color: #f6f8fa;
    border-radius: 6px;
    padding: 16px;
    overflow: auto;
    margin: 16px 0;
    
    code {
      background-color: transparent;
      padding: 0;
    }
  }

  :deep(.copy-btn) {
    position: absolute;
    right: 8px;
    top: 8px;
    padding: 4px 8px;
    background: #e1e4e8;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 12px;
    transition: all 0.2s;
    
    &:hover {
      background: #d1d5da;
    }
  }

  :deep(code) {
    padding: 0.2em 0.4em;
    margin: 0;
    font-size: 85%;
    background-color: rgba(175,184,193,0.2);
    border-radius: 6px;
  }
}
</style>