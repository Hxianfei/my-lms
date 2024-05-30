<script setup lang="ts">
import {getUserBorrowedsService} from "../../api/borrow.ts";
import {ref} from "vue";
import {getBookService} from "../../api/book.ts";

const dialogFormVisible = ref(false);

interface userBorrow {
  bookId: number
  title: string
  borrowTime: string
}

const total = ref(0);
const page = ref(1);
const pageSize = ref(8);
const tableData: userBorrow[] = ref([]);
const getUserBorroweds = async () => {
  const result = await getUserBorrowedsService(page.value, pageSize.value);
  total.value = result.data.total;
  tableData.value = result.data.items;
}
getUserBorroweds();
const onCurrentChange = (val: number) => {
  page.value = val;
  getUserBorroweds();
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
  <h3 style="color: gray">待归还的图书</h3>
  <hr>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column label="图书名称" prop="title"/>
    "
    <el-table-column label="借阅时间" prop="borrowTime"/>
    "
    <el-table-column align="right">
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
  <!--  弹窗-->
  <el-dialog v-model="dialogFormVisible" title="图书信息" width="500">
    <el-descriptions column="1">
      <el-descriptions-item label="图书名称">{{ book.title }}</el-descriptions-item>
      <el-descriptions-item label="图书编号">{{ book.id }}</el-descriptions-item>
      <el-descriptions-item label="图书作者">{{ book.author }}</el-descriptions-item>
      <el-descriptions-item label="ISBN">{{ book.isbn }}</el-descriptions-item>
      <el-descriptions-item label="出版社">{{ book.publisher }}</el-descriptions-item>
      <el-descriptions-item label="出版年份">{{ book.publishYear }}</el-descriptions-item>
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