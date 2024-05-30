<script setup lang="ts">

import {ref} from "vue";
import {getBorrowsService, returnBookService} from "../../../api/borrow.ts";
import {ElMessage} from "element-plus";

interface borrow {
  id: number
  username: string
  title: string
  borrowTime: string
  returnTime: string
  status: number
}

const total = ref(0);
const page = ref(1);
const pageSize = ref(10);
const tableData: borrow[] = ref([]);
const getBorrows = async () => {
  const result = await getBorrowsService(page.value, pageSize.value);
  total.value = result.data.total;
  tableData.value = result.data.items;
}
getBorrows();
const onCurrentChange = (val: number) => {
  page.value = val;
  getBorrows();
}

// 归还图书
const returnBook = async (id: number) => {
  const result = await returnBookService(id);
  ElMessage.success('归还成功');
  getBorrows();
}
</script>

<template>
  <h3 style="color: gray">借阅信息表</h3>
  <hr>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column label="借阅人" prop="username"/>
    <el-table-column label="图书名称" prop="title"/>
    <el-table-column label="借阅时间" prop="borrowTime"/>
    <el-table-column label="归还时间" prop="returnTime"/>
    <el-table-column label="状态" prop="status">
      <template #default="scope">
        <el-tag v-if="scope.row.status === 1" type="success">已归还</el-tag>
        <el-tag v-else type="danger">未归还</el-tag>
      </template>
    </el-table-column>
    <el-table-column align="right">
      <template #default="scope">
        <el-button v-if="scope.row.status === 0" type="primary" @click="returnBook(scope.row.id)" size="small">
          <el-icon>
            <Check/>
          </el-icon>
          确认归还
        </el-button>
      </template>
    </el-table-column>
  </el-table>
  <!--  分页组件-->
  <el-pagination background layout="prev, pager, next" :total="total"
                 :page-size="pageSize" @current-change="onCurrentChange"
                 style="margin: 20px 40%"/>
</template>

<style scoped>

</style>