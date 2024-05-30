// 请求工具
import axios from 'axios'
import {useTokenStore} from "../stores/token.ts"
import router from "../router";
import {ElMessage} from "element-plus";

const request = axios.create({
  baseURL: '/api',
  timeout: 5000
})

// 添加响应拦截器
request.interceptors.response.use(
    result => {
      if(result.data.code === 0){
        return result.data
      } else {
          ElMessage.error(result.data.msg?result.data.msg:'服务异常')
        return Promise.reject(result.data)
      }
    },
    error => {
        // 未登录状态，跳转登录
        if(error.response.status === 401){
            ElMessage.warning('未登录, 请先登录')
            router.push('/login')
        }else {
            ElMessage.error('服务异常')
        }
      return Promise.reject(error)
    }
)

// 添加请求拦截器
request.interceptors.request.use(
    config => {
        const tokenStore = useTokenStore()
        if(tokenStore.token){
          config.headers.Authorization = 'Bearer ' + tokenStore.token
        }
      return config
    },
    error => {
      // 对请求错误做些什么
      return Promise.reject(error)
    }
)

export default request
