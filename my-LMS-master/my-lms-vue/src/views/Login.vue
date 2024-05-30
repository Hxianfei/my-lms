<script setup lang="ts">
import {reactive, ref} from 'vue'
import {registerService} from "../api/user.ts";
import {loginService} from "../api/user.ts";
import {useRouter} from "vue-router";
import {useTokenStore} from "../stores/token.ts";
import {ElMessage} from "element-plus";

// 控制注册与登录表单的显示， 默认显示登录
const isLogin = ref(true)
const router = useRouter();
const tokenStore = useTokenStore();

// 登录表单
const loginForm = reactive({
  username: '',
  password: '',
  role: null,
})
// 提交登录表单
const login = async () => {
  let result = await loginService(loginForm);
  ElMessage.success('登录成功')
  // 登录成功
  console.log(result.data);
  tokenStore.setToken(result.data);
  if (loginForm.role == 1) {
    router.push('/admin');
  } else {
    router.push('/home');
  }
}
// 注册表单
const registerForm = reactive({
  username: '',
  password: '',
  rePassword: '',
})
// 提交注册表单
const register = async () => {
  await registerService(registerForm);
  ElMessage.success('注册成功')
  isLogin.value = true
}

// 表单校验规则
// 校验密码的函数
const checkRePassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请再次确认密码'))
  } else if (value !== registerForm.password) {
    callback(new Error('请确保两次输入的密码一样'))
  } else {
    callback()
  }
}
const rules = reactive({
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'},
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'},
  ],
  rePassword: [
    {required: true, message: '请再次确认密码', trigger: 'blur'},
    {validator: checkRePassword, trigger: 'blur'},
  ],
})
</script>

<template>
  <div class="login">
    <el-container>
      <el-header class="login-header">
        <RouterLink to="/"class="title">MY-LMS图书管理系统</RouterLink>
      </el-header>
      <el-main class="login-main">
        <!--        登录表单-->
        <el-form class="form" :model="loginForm" label-width="auto" style="max-width: 400px" v-if="isLogin" :rules="rules">
          <el-form-item label="用户名" prop="name">
            <el-input v-model="loginForm.username" placeholder="输入用户名" prefix-icon="User"/>
          </el-form-item>
          <el-form-item label="密 码" prop="password">
            <el-input v-model="loginForm.password" placeholder="输入密码" prefix-icon="Lock" type="password"/>
          </el-form-item>
          <el-form-item label="登录角色">
            <el-select v-model="loginForm.role" placeholder="--选择角色--">
              <el-option label="借阅者" value="0" default/>
              <el-option label="管理员" value="1"/>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button class="login-btn" type="primary" @click="login">登录</el-button>
            <el-link class="login-link" type="info" @click="isLogin = false">没有账号？去注册
              <el-icon>
                <Right/>
              </el-icon>
            </el-link>
          </el-form-item>
        </el-form>
        <!--        注册表单-->
        <el-form class="form" v-else :model="registerForm" label-width="auto" style="max-width: 400px" :rules="rules">
          <el-form-item label="用户名" prop="name">
            <el-input v-model="registerForm.username" placeholder="输入用户名" prefix-icon="User"/>
          </el-form-item>
          <el-form-item label="密 码" prop="password">
            <el-input v-model="registerForm.password" placeholder="输入密码" prefix-icon="Lock" type="password"/>
          </el-form-item>
          <el-form-item label="确认密码" prop="rePassword">
            <el-input v-model="registerForm.rePassword" placeholder="再次输入密码" prefix-icon="Lock" type="password"/>
          </el-form-item>
          <el-form-item>
            <el-button class="login-btn" type="primary" @click="register">注册</el-button>
            <el-link class="login-link" type="info" @click="isLogin = true">已有账号？去登录
              <el-icon>
                <Right/>
              </el-icon>
            </el-link>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<style scoped>
.login {
  height: 600px;
  width: 600px;
  margin: 0 auto;

}

.login-header {
  height: 60px;
  background-color: #409EFF;
  text-align: center;
}

.login-main {
  height: calc(100% - 60px);
  background-color: #f0f2f5;
}
.form {
  margin-left: 80px;
}
.title {
  color: #fff;
  font-size: 24px;
}

.login-btn {
  float: left;
  width: 100%;
}

.login-link {
  float: right;
}
</style>