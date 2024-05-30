//路由配置
import {createRouter, createWebHashHistory, RouteRecordRaw} from 'vue-router'

//导入组件
import Admin from '../views/admin/Admin.vue'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import User from '../views/user/User.vue'

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        path: '/home',
        name: 'Home',
        component: Home,
    },
    {
        path: '/login',
        name: 'Login',
        component: Login,
    },
    {
        path: '/admin',
        name: 'Admin',
        component: Admin,
        redirect: '/admin/bookmanage',
        children: [
            {
                path: 'bookmanage',
                name: 'BookManage',
                component: () => import('../views/admin/bookmanage/BookManage.vue')
            },
            {
                path: 'bookcategory',
                name: 'BookCategory',
                component: () => import('../views/admin/bookmanage/BookCategory.vue')
            },
            {
                path: 'bookadd',
                name: 'BookAdd',
                component: () => import('../views/admin/bookmanage/BookAdd.vue')
            },
            {
                path: 'usermanage',
                name: 'UserManage',
                component: () => import('../views/admin/usermanage/UserManage.vue')
            },
            {
                path: 'borrowmanage',
                name: 'BorrowManage',
                component: () => import('../views/admin/borrowmanage/BorrowManage.vue')
            }
        ]
    },
    {
        path: '/user',
        name: 'User',
        component: User,
        redirect: '/user/userinfo',
        children: [
            {
                path: 'userinfo',
                name: 'UserInfo',
                component: () => import('../views/user/UserInfo.vue'),
            },
            {
                path: 'borrowedbook',
                name: 'BorrowedBook',
                component: () => import('../views/user/BorrowedBook.vue')
            },
            {
                path: 'borrowRecord',
                name: 'BorrowRecord',
                component: () => import('../views/user/BorrowRecord.vue')
            },


        ]
    }
]
const router = createRouter({
    history: createWebHashHistory(),
    routes
})
export default router;