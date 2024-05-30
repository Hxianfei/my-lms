<script setup lang="ts">
import {ref} from 'vue'
import {addBookService} from "../../../api/book.ts";
import {ElMessage} from "element-plus";

// do not use same name with ref
const form = ref({
  title: '',
  author: '',
  isbn: '',
  publisher: '',
  publishYear: 0,
  quantity: 0
})

const addBook = async () => {
  const result = await addBookService(form.value)
  ElMessage.success('添加成功')
}
const rules = {
  title: [
    {required: true, message: '请输入图书名称', trigger: 'blur'},
    {min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur'}
  ],
  author: [
    {required: true, message: '请输入作者', trigger: 'blur'},
    {min: 2, max: 15, message: '长度在 2 到 15 个字符', trigger: 'blur'}
  ],
  isbn: [
    {required: true, message: '请输入ISBN', trigger: 'blur'},
    {pattern: /^[0-9]{13}$/, message: '请输入正确的ISBN', trigger: 'blur'}
  ],
  publisher: [
    {required: true, message: '请输入出版社', trigger: 'blur'},
    {min: 5, max: 15, message: '长度在 5 到 15 个字符', trigger: 'blur'}
  ],
  publishYear: [
    {required: true, message: '请输入出版年份', trigger: 'blur'},
      {type: 'number',min: 1900, max: 2023, message: '请输入正确的年份', trigger: 'blur'}
  ],
  quantity: [
    {required: true, message: '请输入数量', trigger: 'blur', type: 'number'}
  ],
}
</script>

<template>
  <h3 style="color: gray">添加图书</h3>
  <hr>
  <el-form :model="form" label-width="auto" style="max-width: 600px" :rules="rules">
    <el-form-item label="图书名称" prop="title">
      <el-input v-model="form.title"/>
    </el-form-item>
    <el-form-item label="作者" prop="author">
      <el-input v-model="form.author"/>
    </el-form-item>
    <el-form-item label="ISBN" prop="isbn">
      <el-input v-model="form.isbn"/>
    </el-form-item>
    <el-form-item label="出版社" prop="publisher">
      <el-input v-model="form.publisher"/>
    </el-form-item>
    <el-form-item label="出版年份" prop="publishYear">
      <el-input v-model="form.publishYear"/>
    </el-form-item>
    <el-form-item label="数量" prop="quantity">
      <el-input v-model="form.quantity"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="addBook"><el-icon><Plus /></el-icon>添加</el-button>
    </el-form-item>
  </el-form>
</template>

<style scoped>

</style>