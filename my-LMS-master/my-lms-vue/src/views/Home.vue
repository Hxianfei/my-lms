<script setup lang="ts">
import {findAllBooksService} from "../api/book.ts";
import {borrowBookService} from "../api/borrow.ts";
import {ref} from 'vue'
import {Search} from '@element-plus/icons-vue'

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

const tableData: Book[] = ref([]);
const input = ref('')
const select = ref(null)
const categories: Category[] = ref([])
const getCategory = async () => {
  const result = await getCategoriesService();
  categories.value = result.data
}
getCategory();
const getBooks = async () => {
  let result = await findAllBooksService(1, 100, input.value, select.value);
  tableData.value = result.data.items;
}
getBooks();

// 借阅图书
const borrowBook = async (row: Book) => {
  const result = await borrowBookService(row.id);
  ElMessage.success('借阅成功')
  getBooks();
}
// 轮播图
import img01 from '../assets/01.png'
import img02 from '../assets/02.png'
import img03 from '../assets/03.png'
import img04 from '../assets/04.png'
import img05 from '../assets/05.png'
import img06 from '../assets/06.png'
import {ElMessage} from "element-plus";
import {getCategoriesService} from "../api/category.ts";

const carouselImg = ref([
  img01,
  img02,
  img03,
  img04,
  img05,
  img06
])
</script>

<template>
  <div class="home">
    <el-container>
      <el-header class="home-header">
        <span class="home-title">KUST昆明理工大学图书馆</span>
        <span class="header-link">
        <router-link to="/user"> 个人中心</router-link>
        </span>
      </el-header>
      <el-main class="home-main">
        <!--        轮播图-->
        <el-carousel :interval="4000" type="card" height="250px">
          <el-carousel-item v-for="item in carouselImg" :key="item">
            <!--            设置背景图-->
            <img :src="item" style="width: 100%">
          </el-carousel-item>
        </el-carousel>
        <!--        搜索框-->
        <div class="home-input">
          <el-input
              v-model="input"
              style="max-width: 600px"
              placeholder="输入图书名称"
              class="input-with-select"
          >
            <template #prepend>
              <el-select v-model="select" placeholder="分类" style="width: 115px">
                <el-option label="全部" value=""/>
                <el-option
                    v-for="item in categories"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                />
              </el-select>
            </template>
            <template #append>
              <el-button :icon="Search" @click="getBooks"/>
            </template>
          </el-input>
        </div>
        <el-table class="table" :data="tableData" height="570" style="width: 100%; margin-top: 20px;">
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
          <el-table-column align="right">
            <template #default="scope">
              <el-button type="primary" @click="borrowBook(scope.row)"
                         style="margin-right: 15px">借阅
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
      <el-footer class="home-footer">
        <span class="home-footer-text">My-LMS © 2024 KUST</span>
        <br>
        <span class="home-footer-text">Designed by 计科211班 黄先非</span>
      </el-footer>
    </el-container>
  </div>
</template>

<style scoped>
.home {

}

.home-header {
  background-color: #409EFF;
}

.home-title {
  font-size: 30px;
  color: #fff;
}

.header-link {
  float: right;
  margin-top: 20px;
}

.home-main {
  background-color: #ecf5ff;
}

.home-footer {
  background-color: #337ecc;
  height: 100px;
  text-align: center;
}

.home-input {
  margin-top: 20px;
  text-align: center;
}
</style>