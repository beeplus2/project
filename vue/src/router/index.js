import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
    // 后台
    {
        path: '/',
        component: () => import('../views/Manage.vue'),
        redirect: "/home",
        children: [
            {path: 'home', name: '后台首页', component: () => import('../views/Home.vue')},
            {path: 'type', name: '社团类型管理', component: () => import('../views/Type.vue')},
            {path: 'club', name: '社团信息管理', component: () => import('../views/Club.vue')},
            {path: 'user', name: '用户信息管理', component: () => import('../views/User.vue')},
            {path: 'check', name: '成团申请管理', component: () => import('../views/Apply.vue')},
            {path: 'activity', name: '社团活动管理', component: () => import('../views/Activity.vue')}
        ]
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('../views/Login.vue')
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('../views/Register.vue')
    },

    // 前台
    {
        path: '/front',
        name: 'Front',
        redirect: "/front/frontHome",
        component: () => import('../views/front/Front.vue'),
        children: [
            {path: 'frontHome', name: 'FrontHome', component: () => import('../views/front/FrontHome.vue')},
            {path: 'clubList', name: 'ClubList', component: () => import('../views/front/ClubList.vue')},
            {path: 'myClub', name: 'MyClub', component: () => import('../views/front/MyClub.vue')},
            {path: 'clubInfo', name: 'ClubInfo', component: () => import('../views/front/ClubInfo.vue')},
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
    localStorage.setItem("currentPathName", to.name)    // 设置当前的路由名称,为了在Header组件中去使用
    store.commit("setPath")     // 触发store的数据更新
    next()      // 放行
})

export default router
