import request from '@/utils/request'

// 获取目录结构树
export const catalogTree = () => {
  return request.get('/catalog/tree')
}
