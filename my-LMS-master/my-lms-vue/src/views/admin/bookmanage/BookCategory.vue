<script setup lang="ts">
import { ref } from 'vue'
import {addCategoryService, deleteCategoryService, getCategoriesService} from "../../../api/category.ts";
import {ElMessage} from "element-plus";

const dialogFormVisible = ref(false)
interface Category {
  id: number
  name: string
}
const form = ref({
  name: ''
})
const tableData: Category[] = ref([])
const getCategory = async () => {
  const result = await getCategoriesService();
  tableData.value = result.data
}
getCategory();
const addCategory = async () => {
  const result = await addCategoryService(form.value);
  ElMessage.success('添加分类成功');
  getCategory();
}
const deleteCategory =async (id: number) => {
  const result =  await deleteCategoryService(id);
  ElMessage.success('删除分类成功');
  getCategory();
}
const rules = {
  name: [
    { required: true, message: '请输入分类名称', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
  ]
}
</script>

<template>

  <h3 style="color: gray; display: inline-block">图书分类表</h3>
  <el-button type="primary" @click="dialogFormVisible = true" style="float: right;margin-top: 20px;margin-right: 15px">
    <el-icon><Plus /></el-icon>添加分类
  </el-button>
  <hr>
  <!--  添加分类对话框-->
  <el-dialog v-model="dialogFormVisible" title="添加图书分类" width="500">
    <el-form :model="form" :rules="rules">
      <el-form-item label="分类名称" prop="name">
        <el-input v-model="form.name" autocomplete="off"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false; addCategory()">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
<!--  表格数据-->
    <el-table :data="tableData" style="width: 100%">
      <el-table-column label="分类编号" prop="id" />
      <el-table-column label="分类名称" prop="name" />
      <el-table-column align="right">
        <template #default="scope">
          <el-button size="small" type="danger" @click="deleteCategory(scope.row.id)">
            <el-icon><Delete /></el-icon>
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

</template>

<style scoped>

</style>