<template>
  <section class="hero">
                <h2>探索、学习与创造</h2>
                <p>这里是我分享知识、展示项目和创意作品的空间</p>
            </section>

            <section class="modules">
                <div class="module-card learning">
                    <div class="module-icon">
                        <i class="fas fa-book"></i>
                    </div>
                    <h3>学习笔记</h3>
                    <p>记录技术学习过程中的知识点、心得体会和教程分享</p>
                    <router-link to="/article/list" class="btn">查看笔记</router-link>
                </div>

                <div class="module-card projects">
                    <div class="module-icon">
                        <i class="fas fa-code"></i>
                    </div>
                    <h3>项目展示</h3>
                    <p>展示个人开发的项目，包括Web应用、移动应用和其他创意项目</p>
                     <router-link to="/project/list" class="btn">查看项目</router-link>
                </div>

                <!-- <div class="module-card drawing">
                    <div class="module-icon">
                        <i class="fas fa-paint-brush"></i>
                    </div>
                    <h3>创意绘图</h3>
                    <p>分享我的数字绘画作品、设计稿和创意视觉表达</p>
                    <a href="#" class="btn">查看作品</a>
                </div> -->
            </section>
</template>

<script setup>
import {} from "vue"
</script>

<style lang="scss" scoped>
.hero {
            text-align: center;
            margin-bottom: 3rem;
            padding: 2rem 0;
        }
        
        .hero h2 {
            font-size: 2.5rem;
            margin-bottom: 1rem;
            color: #2c3e50;
        }
        
        .hero p {
            font-size: 1.2rem;
            max-width: 700px;
            margin: 0 auto;
            color: #7f8c8d;
        }
        
        .modules {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 2rem;
            margin-bottom: 3rem;
        }
        
        .module-card {
            background: white;
            border-radius: 10px;
            padding: 2rem;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.08);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            text-align: center;
        }
        
        .module-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.12);
        }
        
        .module-icon {
            font-size: 2.5rem;
            margin-bottom: 1rem;
            color: #3498db;
        }
        
        .learning .module-icon {
            color: #e74c3c;
        }
        
        .projects .module-icon {
            color: #2ecc71;
        }
        
        .drawing .module-icon {
            color: #9b59b6;
        }
        
        .module-card h3 {
            font-size: 1.5rem;
            margin-bottom: 1rem;
            color: #2c3e50;
        }
        
        .module-card p {
            color: #7f8c8d;
            margin-bottom: 1.5rem;
        }
        
        .btn {
            display: inline-block;
            background: #3498db;
            color: white;
            padding: 0.7rem 1.5rem;
            border-radius: 30px;
            text-decoration: none;
            font-weight: 500;
            transition: all 0.3s ease;
        }
        
        .learning .btn {
            background: #e74c3c;
        }
        
        .projects .btn {
            background: #2ecc71;
        }
        
        .drawing .btn {
            background: #9b59b6;
        }
        
        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }
        
        @media (max-width: 768px) {
            .header-content {
                flex-direction: column;
                text-align: center;
            }
            
            nav ul {
                margin-top: 1.5rem;
            }
            
            nav li {
                margin: 0 0.75rem;
            }
            
            .hero h2 {
                font-size: 2rem;
            }
            
            .hero p {
                font-size: 1rem;
            }
        }
</style>
