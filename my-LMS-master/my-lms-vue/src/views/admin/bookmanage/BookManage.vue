<script setup lang="ts">
import {ref} from 'vue'
import { deleteBookService, findAllBooksService} from "../../../api/book.ts";
import {ElMessage} from "element-plus";
import {getCategoriesService} from "../../../api/category.ts";
import {addBookCategoryService} from "../../../api/bookCategory.ts";

const dialogFormVisible = ref(false)
interface Book {
  id: number,
  title: string,
  author: string,
  isbn: string,
  publisher: string,
  publishYear: number,
  categoryName: string,
  quantity: number
}

interface Category {
  id: number
  name: string
}
// 抽屉
const drawer = ref(false)

const tableData: Book[] = ref([]);
const total = ref(0);
const page = ref(1);
const pageSize = ref(7);
const getBooks = async () => {
  let result = await findAllBooksService(page.value, pageSize.value,'', null);
  total.value = result.data.total;
  tableData.value = result.data.items;
}
getBooks();

const deleteBook = async (id: number) => {
  const result = await deleteBookService(id);
  ElMessage.success('删除成功')
  getBooks();
}
const onCurrentChange = (val: number) => {
  page.value = val;
  getBooks();
}
const categories: Category[] = ref([])
const getCategory = async () => {
  const result = await getCategoriesService();
  categories.value = result.data
}
getCategory();
const categoryForBook = ref({
  bookId:'',
  categoryId:''
})
const addBookCategory = async () => {
  const result = await addBookCategoryService(categoryForBook.value);
  ElMessage.success('添加成功')
  getBooks();
}
</script>

<template>
  <h3 style="color: gray">图书信息表</h3>
  <hr>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column label="书名" prop="title"/>
    <el-table-column label="作者" prop="author"/>
    <el-table-column label="ISBN" prop="isbn"/>
    <el-table-column label="出版社" prop="publisher"/>
    <el-table-column label="出版年份" prop="publishYear"/>
    <el-table-column label="分类" prop="quantity">
      <template #default="scope">
      <el-tag v-if="scope.row.categoryName == null" type="warning">未分类</el-tag>
      <el-tag v-else type="success">{{ scope.row.categoryName }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="库存" prop="quantity"/>
    <el-table-column align="right" width="160px">
      <template #default="scope">
        <el-button size="small" @click="dialogFormVisible = true; categoryForBook.bookId = scope.row.id">
          <el-icon><Edit /></el-icon>
          分类
        </el-button>
        <el-button size="small" type="danger" @click="deleteBook(scope.row.id)">
          <el-icon><Delete /></el-icon>
          删除
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <!--  分页组件-->
  <el-pagination background layout="prev, pager, next" :total="total"
                 :page-size="pageSize" @current-change="onCurrentChange"
                 style="margin: 20px 40%"/>
  <el-drawer v-model="drawer" title="编辑图书信息" :with-header="true">
    <span>Hi there!</span>
  </el-drawer>
  <el-dialog v-model="dialogFormVisible" title="为图书添加分类" width="500">
    <el-form :model="categoryForBook" label-width="auto" style="max-width: 600px" :rules="rules">
      <el-form-item label="图书分类" prop="email">
        <el-select v-model="categoryForBook.categoryId" placeholder="分类" style="width: 115px">
          <el-option
              v-for="item in categories"
              :key="item.id"
              :label="item.name"
              :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false;addBookCategory()">确定</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<style scoped>

</style>