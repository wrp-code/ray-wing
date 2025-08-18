import request from '@/utils/request'

// 根据Id获取文章详情
export const getArticleDetail = (articleId) => {
  return request.get(`/article/${articleId}`)
}
