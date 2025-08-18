import request from '@/utils/request'

// 根据Id获取文章详情
export const getArticleDetail = (articleId) => {
  return request.get(`/article/${articleId}`)
}


// 根据catalogId获取文章列表
export const getArticleList = (cataId) => {
  return request.get(`/article/list?catalogId=${cataId}`)
}
