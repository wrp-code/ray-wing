<!--
 * new page
 * @author: wrp
 * @since: 2025年08月20日 07:51:13
-->
<template>
    <div class="container">
        <header>
            <h1>Vue3 Markdown解析与目录导航</h1>
            <p class="description">使用marked和highlight.js解析Markdown并生成可交互目录</p>
        </header>

        <aside class="toc-container">
            <h2 class="toc-title">📑 目录</h2>
            <ul class="toc">
                <li v-for="item in tocItems" :key="item.id"
                    :class="['toc-item', 'level-' + item.level, { active: activeId === item.id }]">
                    <a href="javascript:void(0)" @click="scrollToHeading(item.id)">
                        {{ item.text }}
                    </a>
                </li>
            </ul>
        </aside>

        <main class="markdown-container">
            <div class="markdown-body" v-html="parseMarkdown()"></div>
        </main>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted, defineProps, computed, watch } from 'vue';
import { marked } from 'marked'
import hljs from 'highlight.js'
import 'highlight.js/styles/github.css'
import 'github-markdown-css/github-markdown.css'

const markdownContent = ref('');
const tocItems = ref([]);
const activeId = ref('');
const sampleMarkdown = ''

// 配置marked
marked.setOptions({
    highlight: function (code, lang) {
        if (lang && hljs.getLanguage(lang)) {
            return hljs.highlight(lang, code).value;
        }
        return hljs.highlightAuto(code).value;
    },
    langPrefix: 'hljs language-',
});

// 自定义渲染器为标题添加ID
const renderer = new marked.Renderer();
renderer.heading = function (text, level) {
    const id = text.toLowerCase().replace(/[^\w]+/g, '-');
    return `<h${level} id="${id}">${text}</h${level}>`;
};

// 解析Markdown
const parseMarkdown = () => {
    return marked.parse(markdownContent.value, { renderer });
};

// 提取目录
const extractToc = () => {
    const htmlContent = parseMarkdown();
    const parser = new DOMParser();
    const doc = parser.parseFromString(htmlContent, 'text/html');
    const headings = doc.querySelectorAll('h1, h2, h3, h4, h5, h6');
    const toc = [];

    headings.forEach(heading => {
        toc.push({
            id: heading.id,
            text: heading.textContent,
            level: parseInt(heading.tagName.substring(1))
        });
    });

    return toc;
};

// 滚动到指定标题
const scrollToHeading = (id) => {
    const element = document.getElementById(id);
    if (element) {
        const offset = 80;
        const elementPosition = element.getBoundingClientRect().top + window.pageYOffset;
        const offsetPosition = elementPosition - offset;

        window.scrollTo({
            top: offsetPosition,
            behavior: 'smooth'
        });
    }
};

// 处理滚动时的目录高亮
const handleScroll = () => {
    const headings = tocItems.value.map(item => document.getElementById(item.id)).filter(Boolean);
    const scrollPosition = window.scrollY + 100;

    for (let i = headings.length - 1; i >= 0; i--) {
        if (headings[i].offsetTop <= scrollPosition) {
            activeId.value = headings[i].id;
            break;
        }
    }
};

onMounted(() => {
    markdownContent.value = sampleMarkdown;
    tocItems.value = extractToc();

    window.addEventListener('scroll', handleScroll);
});

// 监听markdown内容变化
watch(markdownContent, () => {
    tocItems.value = extractToc();
});
</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

body {
    background-color: #1a1a2e;
    color: #e6e6e6;
    line-height: 1.6;
    padding: 20px;
}

.container {
    max-width: 1200px;
    margin: 0 auto;
    display: grid;
    grid-template-columns: 280px 1fr;
    gap: 30px;
}

header {
    grid-column: 1 / -1;
    text-align: center;
    padding: 20px;
    margin-bottom: 20px;
    background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
    border-radius: 12px;
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
}

h1 {
    font-size: 2.5rem;
    margin-bottom: 10px;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.description {
    font-size: 1.1rem;
    opacity: 0.9;
}

.toc-container {
    background: #2d2d44;
    border-radius: 12px;
    padding: 20px;
    height: fit-content;
    position: sticky;
    top: 20px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

.toc-title {
    font-size: 1.4rem;
    margin-bottom: 15px;
    padding-bottom: 10px;
    border-bottom: 2px solid #6a11cb;
    color: #fff;
}

.toc {
    list-style: none;
    padding-left: 15px;
}

.toc-item {
    margin: 8px 0;
    transition: all 0.3s ease;
}

.toc-item:hover {
    transform: translateX(5px);
}

.toc-item a {
    color: #b8b8d0;
    text-decoration: none;
    display: block;
    padding: 6px 10px;
    border-radius: 6px;
    transition: all 0.2s ease;
}

.toc-item a:hover {
    color: #fff;
    background: rgba(106, 17, 203, 0.2);
}

.toc-item.active a {
    color: #6a11cb;
    font-weight: 600;
    background: rgba(106, 17, 203, 0.1);
}

.toc-item.level-1 {
    font-weight: 600;
    margin-left: 0;
}

.toc-item.level-2 {
    margin-left: 15px;
    font-size: 0.95rem;
}

.toc-item.level-3 {
    margin-left: 30px;
    font-size: 0.9rem;
}

.markdown-container {
    background: #2d2d44;
    border-radius: 12px;
    padding: 30px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

.markdown-body {
    line-height: 1.8;
}

.markdown-body h1,
.markdown-body h2,
.markdown-body h3,
.markdown-body h4 {
    margin-top: 1.8em;
    margin-bottom: 0.8em;
    color: #fff;
    border-bottom: 1px solid #444;
    padding-bottom: 0.3em;
}

.markdown-body h1 {
    color: #6a11cb;
    font-size: 2.2rem;
}

.markdown-body h2 {
    color: #2575fc;
    font-size: 1.8rem;
}

.markdown-body h3 {
    color: #4ecdc4;
    font-size: 1.5rem;
}

.markdown-body p {
    margin-bottom: 1.2em;
}

.markdown-body code {
    background-color: #3a3a52;
    color: #ff9d5c;
    padding: 0.2em 0.4em;
    border-radius: 4px;
    font-family: 'Fira Code', monospace;
}

.markdown-body pre {
    background-color: #1e1e2e;
    border-radius: 8px;
    padding: 16px;
    overflow-x: auto;
    margin: 1.5em 0;
    box-shadow: inset 0 0 10px rgba(0, 0, 0, 0.3);
}

.markdown-body blockquote {
    border-left: 4px solid #6a11cb;
    background-color: rgba(106, 17, 203, 0.1);
    padding: 1em;
    margin: 1.5em 0;
    border-radius: 0 8px 8px 0;
}

.markdown-body ul,
.markdown-body ol {
    padding-left: 2em;
    margin: 1.2em 0;
}

.markdown-body li {
    margin: 0.5em 0;
}

.markdown-body a {
    color: #6a11cb;
    text-decoration: none;
}

.markdown-body a:hover {
    text-decoration: underline;
}

.markdown-body table {
    width: 100%;
    border-collapse: collapse;
    margin: 1.5em 0;
}

.markdown-body th,
.markdown-body td {
    padding: 0.75em;
    border: 1px solid #444;
}

.markdown-body th {
    background-color: rgba(106, 17, 203, 0.2);
    font-weight: 600;
}

.markdown-body tr:nth-child(even) {
    background-color: rgba(255, 255, 255, 0.05);
}

@media (max-width: 768px) {
    .container {
        grid-template-columns: 1fr;
    }

    .toc-container {
        position: relative;
        top: 0;
        margin-bottom: 20px;
    }
}
</style>
