<template>
  <h2>{{ article.title }}</h2>
  <div v-highlight v-html="compiledMarkdown"></div>
</template>

<script setup>
import { getArticleDetail } from '../api/article'
import { ref, onMounted, watch, nextTick } from 'vue'
import { marked } from 'marked'
import hljs from 'highlight.js'
import 'highlight.js/styles/github.css'

const article = ref({
  id: null,
  title: '',
  content: ''
})

const compiledMarkdown = ref('')

// 注册指令（局部）
const vHighlight = {
  mounted(el) {
    const blocks = el.querySelectorAll('pre code')
    blocks.forEach(block => hljs.highlightElement(block))
  },
  updated(el) {
    const blocks = el.querySelectorAll('pre code')
    blocks.forEach(block => hljs.highlightElement(block))
  }
}

// 获取文章数据并处理Markdown
const fetchArticle = async () => {
  const articleDetail = await getArticleDetail('1957292352636657665')
  article.value = {
    id: articleDetail.id,
    title: articleDetail.title,
    content: articleDetail.content
  }
  compiledMarkdown.value = marked(article.value.content)
}

// 监听article.content变化
watch(() => article.value.content, () => {
  compiledMarkdown.value = marked(article.value.content)
  nextTick(() => {
    // 确保DOM更新后重新高亮
    document.querySelectorAll('pre code').forEach(block => {
      hljs.highlightElement(block)
    })
  })
})

onMounted(async () => {
  await fetchArticle()
})
</script>

<style lang="scss" scoped></style>