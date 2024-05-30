// 导入请求工具
import request from "../ utils/request.ts";

// 注册接口函数
export const registerService = (data: object) => {
  const params = new URLSearchParams();
  for (const key in data){
    params.append(key, data[key]);
  }
  return request.post('/user/register', params);
}

// 登录接口函数
export const loginService = (data: object) => {
  const params = new URLSearchParams();
  for (const key in data){
    params.append(key, data[key]);
  }
  return request.post('/user/login', params);
}

// 用户列表
export const findAllUsersService = (page: number,pageSize: number) => {
  return request.get('/user',{params: {page,pageSize}});
}

// 用户基本信息
export const getUserInfoService = () => {
  return request.get('/user/userInfo');
}

// 修改用户信息
export const updateUserInfoService = (data: object) => {
  return request.put('/user/update', data);
}
