<script setup lang="ts">
import { ref} from 'vue'
import {findAllUsersService} from "../../../api/user.ts";

interface User {
  username: string
  email: string
  phone: string
  createTime: string
}

const search = ref('')

const handleEdit = (index: number, row: User) => {
  console.log(index, row)
}
const handleDelete = (index: number, row: User) => {
  console.log(index, row)
}

const tableData: User[] = ref([])
const total = ref(0);
const page = ref(1);
const pageSize = ref(10);
const findUsers = async () => {
  let result = await findAllUsersService(page.value, pageSize.value);
  total.value = result.data.total;
  tableData.value = result.data.items;
}
findUsers();
const onCurrentChange = (val: number) => {
  page.value = val;
  findUsers();
}
</script>

<template>
  <h3 style="color: gray">用户信息表</h3>
  <hr>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column label="用户名" prop="username"/>
    <el-table-column label="邮箱" prop="email">
      <template #default="scope">
        <el-tag v-if="scope.row.email == null" type="warning">未填写</el-tag>
        <el-tag v-else type="success">{{ scope.row.email }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="手机号" prop="phone">
      <template #default="scope">
        <el-tag v-if="scope.row.phone == null" type="warning">未填写</el-tag>
        <el-tag v-else type="success">{{ scope.row.phone }}</el-tag>
      </template>
    </el-table-column>
    <el-table-column label="注册时间" prop="createTime"/>
  </el-table>
  <!--  分页组件-->
  <el-pagination background layout="prev, pager, next" :total="total"
                 :page-size="pageSize" @current-change="onCurrentChange"
                 style="margin: 20px 40%"/>

</template>

<style scoped>

</style>