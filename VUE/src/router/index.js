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
      }
    ]
  },

  {
    path: '/login',
    name: 'Login',
    component: () => import("@/views/Login")
  //  导入组件的另一种模式
  },
]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
