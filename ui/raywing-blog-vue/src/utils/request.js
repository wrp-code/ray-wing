// src/utils/request.js
import axios from 'axios'

// 创建 axios 实例
const service = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL, // 从环境变量读取
  timeout: 10000, // 请求超时时间（10s）
  headers: {
    'Content-Type': 'application/json',
  },
})

// // 请求拦截器
// service.interceptors.request.use(
//   (config) => {
//     // 在发送请求前可以做一些处理（如添加 token）
//     const token = localStorage.getItem('token')
//     if (token) {
//       config.headers.Authorization = `Bearer ${token}`
//     }
//     return config
//   },
//   (error) => {
//     // 请求错误处理
//     return Promise.reject(error)
//   }
// )

// 响应拦截器
service.interceptors.response.use(
  (response) => {
    const { data, config } = response
    
    // 情况1：请求成功但业务逻辑失败（如 code != 0）
    if (data?.code !== undefined && data.code !== 0) {
      const errorMsg = data.message || '业务逻辑错误'
      
      // 可选：特定错误码特殊处理（如 token 过期）
      // if (data.code === 401) {
      //   localStorage.removeItem('token')
      //   window.location.href = '/login'
      // }
      
      return Promise.reject(new Error(errorMsg))
    }
    
    // 情况2：请求成功且业务成功，返回核心数据（兼容不同后端格式）
    return data.data ?? data // 如果 data.data 存在则返回，否则返回整个 data
  },
  (error) => {
    // 情况3：HTTP 请求失败（如 404/500）
    const errorResponse = error.response
    
    // 401 未授权处理
    if (errorResponse?.status === 401) {
      localStorage.removeItem('token')
      window.location.href = '/login'
      return Promise.reject(new Error('登录已过期，请重新登录'))
    }
    
    // 其他错误（如网络问题、服务器错误）
    const errorMsg = errorResponse?.data?.message || 
                    error.message || 
                    `请求失败: ${errorResponse?.status || '未知错误'}`
    
    return Promise.reject(new Error(errorMsg))
  }
)

export default service