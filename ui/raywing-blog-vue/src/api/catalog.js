import request from '@/utils/request'

// 获取目录结构树
export const catalogTree = () => {
  return request.get('/catalog/tree')
}

// 获取目录下的书籍
export const getBooks = (id) => {
  return request.get(`/catalog/books?id=${id}`)
}