import request from "../ utils/request.ts";

// 获取借书信息
export const getBorrowsService = (page: number, pageSize: number) => {
  return request.get('/borrow', {params: {page, pageSize}});
}

// 获取用户个人借阅记录（已归还）
export const getUserBorrowsService = (page: number, pageSize: number) => {
  return request.get('/borrow/userRecord', {params: {page, pageSize}});
}

// 获取用户个人借阅记录（未归还）
export const getUserBorrowedsService = (page: number, pageSize: number) => {
  return request.get('/borrow/userBorrowed', {params: {page, pageSize}});
}

// 添加借书信息
export const borrowBookService = (id: number) => {
  return request.get('/borrow/add', {params: {id}});
}

// 归还书
export const returnBookService = (id: number) => {
  return request.get('/borrow/return', {params: {id}});
}