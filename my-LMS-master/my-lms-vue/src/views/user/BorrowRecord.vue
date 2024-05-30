<script setup lang="ts">

import {ref} from "vue";
import { getUserBorrowsService} from "../../api/borrow.ts";
import {getBookService} from "../../api/book.ts";

const dialogFormVisible = ref(false);
interface userBorrow {
  bookId: number
  title: string
  borrowTime: string
  returnTime: string
}
const total = ref(0);
const page = ref(1);
const pageSize = ref(8);
const tableData: userBorrow[] = ref([]);
const getUserBorrows = async () => {
  const result = await getUserBorrowsService(page.value, pageSize.value);
  total.value = result.data.total;
  tableData.value = result.data.items;
}
getUserBorrows();
const onCurrentChange = (val: number) => {
  page.value = val;
  getUserBorrows();
}
const searchBook = async (bookId: number) => {
  const result = await getBookService(bookId);
  book.value = result.data;
}
const book = ref({
  id: 0,
  title: "",
  author: "",
  isbn: "",
  publisher: "",
  publishYear: "",
})
</script>

<template>
  <h3 style="color: gray">我的借阅历史</h3>
  <hr>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column label="图书名称" prop="title"/>"
    <el-table-column label="借阅时间" prop="borrowTime"/>"
    <el-table-column label="归还时间" prop="returnTime"/>"
    <el-table-column align="right">
      <template #header>
        <el-input size="small" placeholder="Type to search"/>
      </template>
      <template #default="scope">
        <el-button type="primary" @click="dialogFormVisible = true;searchBook(scope.row.bookId)">查看详情</el-button>
      </template>
    </el-table-column>
    <template #empty>
      <el-empty description="什么都没有找到~~"/>
    </template>
  </el-table>
  <!--  分页组件-->
  <el-pagination background layout="prev, pager, next" :total="total"
                 :page-size="pageSize" @current-change="onCurrentChange"
                 style="margin: 20px 40%"/>
<!--弹窗-->
  <el-dialog v-model="dialogFormVisible" title="图书信息" width="500">
    <el-descriptions column="1">
      <el-descriptions-item label="图书名称">{{book.title}}</el-descriptions-item>
      <el-descriptions-item label="图书编号">{{book.id}}</el-descriptions-item>
      <el-descriptions-item label="图书作者">{{book.author}}</el-descriptions-item>
      <el-descriptions-item label="ISBN">{{book.isbn}}</el-descriptions-item>
      <el-descriptions-item label="出版社">{{book.publisher}}</el-descriptions-item>
      <el-descriptions-item label="出版年份">{{book.publishYear}}</el-descriptions-item>
    </el-descriptions>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="dialogFormVisible = false">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>

</template>

<style scoped>

</style>