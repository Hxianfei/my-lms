import request from "../ utils/request.ts";

// 获取分类
export const getCategoriesService = () => {
    return request.get('/category')
}

// 添加分类
export const addCategoryService = (data: object) => {
    const params = new URLSearchParams();
    for (const key in data){
        params.append(key, data[key]);
    }
    return request.post('/category', params)
}

// 删除分类
export const deleteCategoryService = (id: number) => {
    return request.delete('/category', {params: {id}})
}