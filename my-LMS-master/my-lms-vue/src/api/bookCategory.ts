import request from "../ utils/request.ts";

// 为图书添加分类
export const addBookCategoryService = (data: object) => {
  const params = new URLSearchParams();
  for (const key in data){
    params.append(key, data[key]);
  }
  return request.post('/bookcategory', params);
}