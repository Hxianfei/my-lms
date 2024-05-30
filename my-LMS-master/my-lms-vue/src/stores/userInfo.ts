import {defineStore} from "pinia";
import {ref} from "vue";

export const useUserInfoStore = defineStore('userInfo', () => {
    const userInfo = ref({
        id: 0,
        username: '',
        password: '',
        email: '',
        phone: '',
        role: '',
        createTime: '',
        updateTime: ''
    })
    const setUserInfo = (user: object) => {
        userInfo.value = user
    }
    const removeUserInfo = () => {
        userInfo.value = {
            id: 0,
            username: '',
            password: '',
            email: '',
            phone: '',
            role: '',
            createTime: '',
            updateTime: ''
        }
    }
    return {userInfo, setUserInfo, removeUserInfo}
}, {
    persist: true
})
