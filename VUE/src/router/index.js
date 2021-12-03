import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Layout from "@/layout/Layout";

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/home",     //重定向路由
    children:[
      {
        path:'home',
        name:'Home',
        component: () => import("@/views/Home")
      },

      {
        path:'book',
        name:'Book',
        component: () => import("@/views/Book")
      },

      {
        path:'news',
        name:'News',
        component: () => import("@/views/News")
      }

    ]
  },

  {
    path: '/login',
    name: 'Login',
    component: () => import("@/views/Login")
  //  导入组件的另一种模式
  },

  {
    path: '/register',
    name: 'Register',
    component: () => import("@/views/Register")
    //  导入组件的另一种模式
  },

    //个人信息
  {
    path: '/person',
    name: 'Person',
    component: () => import("@/views/Person")
    //  导入组件的另一种模式
  },
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
