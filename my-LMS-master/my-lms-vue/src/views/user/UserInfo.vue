<script setup lang="ts">

import {useUserInfoStore} from "../../stores/userInfo.ts";
import {reactive, ref} from "vue";
import {updateUserInfoService} from "../../api/user.ts";
import {ElMessage} from "element-plus";

const dialogFormVisible = ref(false)

const updateForm = ref({
  id: 0,
  username: '',
  password: '',
  email: '',
  phone: '',
  role: '',
  createTime: '',
  updateTime: ''
})
const userInfoStore = useUserInfoStore();
const userInfo = ref(userInfoStore.userInfo);
const updateUserInfo = async () => {
  const result =await updateUserInfoService(updateForm.value)
  ElMessage.success('修改成功')
}
const rules = reactive({
  email: [
    {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']},
  ],
  phone: [
    {pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur'},
  ]
})
</script>

<template>
  <h3 style="color: gray; display: inline-block">我的基本信息</h3>
  <el-button type="primary" @click="dialogFormVisible = true" style="float: right;margin-top: 20px;margin-right: 15px">
    <el-icon><Refresh /></el-icon>修改信息
  </el-button>
  <hr>
  <el-dialog v-model="dialogFormVisible" title="修改基本资料" width="500">
    <el-form :model="updateForm" label-width="auto" style="max-width: 600px" :rules="rules">
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="updateForm.email" :placeholder="userInfo.email"/>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="updateForm.phone" :placeholder="userInfo.phone"/>
      </el-form-item>
      <el-form-item>
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="dialogFormVisible = false;updateUserInfo()">确定</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
  <!--  用户信息描述表-->
  <el-descriptions
      class="userinfo-descriptions"
      :column="1"
      :size="32"
      border
  >
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <user/>
          </el-icon>
          用户名
        </div>
      </template>
      {{ userInfo.username }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Message /></el-icon>
          邮箱
        </div>
      </template>
      <el-tag v-if="userInfo.email == null" type="warning">未填写</el-tag>
      <el-tag v-else type="success">{{ userInfo.email }}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon>
            <iphone/>
          </el-icon>
          手机号
        </div>
      </template>
      <el-tag v-if="userInfo.phone == null" type="warning">未填写</el-tag>
      <el-tag v-else type="success">{{ userInfo.phone }}</el-tag>
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Clock /></el-icon>
          注册时间
        </div>
      </template>
      {{ userInfo.createTime }}
    </el-descriptions-item>
    <el-descriptions-item>
      <template #label>
        <div class="cell-item">
          <el-icon><Clock /></el-icon>
          更新时间
        </div>
      </template>
      {{ userInfo.updateTime }}
    </el-descriptions-item>
  </el-descriptions>
</template>

<style scoped>

</style>