<script setup lang="ts">
import {useTokenStore} from "../../stores/token.ts";
import {useRouter} from "vue-router";
import {useUserInfoStore} from "../../stores/userInfo.ts";
import {getUserInfoService} from "../../api/user.ts";

const tokenStore = useTokenStore()
const userInfoStore = useUserInfoStore()
const router = useRouter()
const getUserInfo = async ()=>{
  let result = await getUserInfoService();
  userInfoStore.setUserInfo(result.data)
}
getUserInfo();
// 退出登录
const logout = () => {
  router.push({name: 'Home'})
  tokenStore.removeToken()
  userInfoStore.removeUserInfo()
}
</script>

<template>
  <div class="user">
    <el-container>
      <el-header class="user-header">

        <span class="user-title">
          <router-link class="home-link" to="/home">MY-LMS图书馆：</router-link>
          个人中心</span>
        <el-button type="primary" :plain="true" class="logout-btn" @click="logout">退出登录</el-button>
      </el-header>
      <el-container>
        <el-aside width="220px">
          <!--          导航栏-->
          <el-menu class="user-menu" router>
            <el-menu-item index="/user/userinfo">
              <el-icon><User /></el-icon>
              <span>个人信息</span>
            </el-menu-item>
            <el-menu-item index="/user/borrowedbook">
              <el-icon><Reading /></el-icon>
              <span>已借阅图书</span>
            </el-menu-item>
            <el-menu-item index="/user/borrowRecord">
              <el-icon><Tickets /></el-icon>
              <span>借阅记录</span>
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
.user-header {
  height: 60px;
  background-color: #409EFF;
}
.user-menu {
  width: 200px;
  height: 100%;
}
.user-title {
  color: #f0f2f5;
  font-size: 24px;
  float: left;
  margin-top:10px;
}
.home-link{
  color: #f0f2f5;
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