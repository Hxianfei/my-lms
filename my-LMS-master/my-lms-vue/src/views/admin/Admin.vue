<script setup lang="ts">
import {useTokenStore} from "../../stores/token.ts";
import {useRouter} from "vue-router";
import {useUserInfoStore} from "../../stores/userInfo.ts";

const tokenStore = useTokenStore()
const router = useRouter()
const userInfoStore = useUserInfoStore()
// 退出登录
const logout = () => {
  router.push({name: 'Home'})
  tokenStore.removeToken()
  userInfoStore.removeUserInfo()
}
</script>

<template>
  <div class="admin">
    <el-container>
      <el-header class="admin-header">
        <span class="admin-title">昆明理工大学图书管理系统</span>
        <el-button type="primary" :plain="true" class="logout-btn" @click="logout">退出登录</el-button>
      </el-header>
      <el-container>
        <el-aside width="220px">
<!--          导航栏-->
              <el-menu class="admin-menu" router>
                <el-sub-menu>
                  <template #title>
                    <el-icon><Reading /></el-icon>
                    <span>图书管理</span>
                  </template>
                    <el-menu-item index="/admin/bookmanage"><el-icon><Memo /></el-icon>书籍信息</el-menu-item>
                    <el-menu-item index="/admin/bookcategory"><el-icon><Files /></el-icon>分类管理</el-menu-item>
                    <el-menu-item index="/admin/bookadd"><el-icon><DocumentAdd /></el-icon>新增图书</el-menu-item>
                </el-sub-menu>
                <el-menu-item index="/admin/borrowmanage">
                  <el-icon><Tickets /></el-icon>
                  <span>借阅管理</span>
                </el-menu-item>
                <el-menu-item index="/admin/usermanage">
                  <el-icon><User /></el-icon>
                  <span>用户管理</span>
                </el-menu-item>
              </el-menu>
        </el-aside>
        <el-main>
          <el-card class="box-card">
          <router-view></router-view>
          </el-card>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.admin-header {
  height: 60px;
  background-color: #409EFF;
}
.admin-menu {
  width: 200px;
  height: 100%;
}
.admin-title {
  color: #f0f2f5;
  font-size: 24px;
  float: left;
  margin-top:10px;
}
.logout-btn {
  float: right;
  margin-top: 15px;
}
.box-card {
  width: 99%;
  height: 640px;
}
</style>