import request from "../ utils/request.ts";

// 查询图书信息表
export const findAllBooksService=(page:number, pageSize:number,start:string,categoryId:any)=>{
    return request.get('/book', {params: {page, pageSize,start,categoryId}})
}

// 添加图书信息
export const addBookService=(data:object)=>{
    return request.post('/book', data)
}

// 删除图书信息
export const deleteBookService=(id:number)=>{
    return request.delete('/book', {params: {id}})
}

// 获取图书信息
export const getBookService=(id:number)=>{
    return request.get('/book/findbyid', {params: {id}})
}