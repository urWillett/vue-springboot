<!--主体-->
<template>

  <div>
<!--功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
<!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="输入昵称关键字" style="width: 20%" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>
<!--    数据显示-->
    <el-table :data="tableData" border style="width: 100%" stripe>

      <el-table-column prop="id" label="ID" sortable />
      <el-table-column prop="username" label="用户名"  />
     <el-table-column prop="nickName" label="昵称" />  <!--数据库中有_，查询出来显示驼峰-->
      <el-table-column prop="age" label="年龄" />
      <el-table-column prop="sex" label="性别" />
      <el-table-column prop="address" label="地址" />
<!--      编辑和删除-->
      <el-table-column fixed="right" label="操作" >
        <template #default="scope">
       <el-popconfirm title="确认删除吗？" @click="handleDelete(scope.row.id)">  <!--  气泡确认框  -->
            <template #reference>
          <el-button type="danger" size="mini">删除</el-button>
            </template>
          </el-popconfirm>
          <el-button  size="mini " @click="handleEdit(scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
<!--    分页-->
    <div class="demo-pagination-block">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[5,10,20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>

<!--    弹出框-->
    <el-dialog
        v-model="dialogVisible"
        title="新增"
        width="30%"
        :before-close="handleClose"
    >
      <el-form :model="form" label-width="120px">

        <el-form-item label="用户名">
          <el-input v-model="form.username" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickName" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age" style="width: 80%"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="form.sex" label="男"> 男</el-radio>
          <el-radio v-model="form.sex" label="女"> 女</el-radio>
          <el-radio v-model="form.sex" label="未知"> 未知</el-radio>
        </el-form-item>
        <el-form-item label="地址">
          <el-input type="textarea" v-model="form.address" style="width: 80%"></el-input>
        </el-form-item>
          </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确认</el-button>
      </span>
      </template>
    </el-dialog>
    <el-button :plain="true" @click="open1">成功</el-button>



  </div>
</template>

<script>
// @ is an alias to /src


import {ref,defineComponent} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import request from "../utils/request";

export default  {
  name: 'Home',
  components: {

  },
  data(){
    return {
      open1() {
        ElMessage.success({
          message: '恭喜你，这是一条成功消息',
          type: 'success'
        });
      },

      form:{},
      handleClose:1,
      dialogVisible:false,
      currentPage:1,
      pageSize:10,
      total:0,
      search:ref('' ),
      tableData:[
      ],
    }
  },
  created() {
    this.load()
  }, //页面预加载时调用
  methods: {
    load(){
      request.get("/api/people",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add(){
    this.dialogVisible=true
    this.form={}  //清空表单，之前的新增记录
        this.handleClose = (done) => {
          ElMessageBox.confirm('你确定要关闭这个弹出框吗?')
              .then(() => {
                    done()})}

    }, //打开新增弹窗
  save(){
      if (this.form.id){ //更新
        request.put("/api/people",this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            ElMessage.success({

              message: '更新成功',
              type: 'success',
            })
          } else {
            ElMessage.error({
              type: 'error',
              message: res.msg
            })
          }
      this.load()  //刷新表格的数据
          this.dialogVisible = false  //关闭弹窗
        })
        } else {   //新增
        request.post("/api/people", this.form).then(res => {
          console.log(res)  //打印返回结果
          if (res.code === '0') {

            ElMessage.success({
              message: "新增成功",
              type: "success",

            })

          } else {
            ElMessage.error({
              type: "error",
              message: res.msg
            })
          }
          this.load()  //刷新表格的数据
          this.dialogVisible = false  //关闭弹窗
        })
      }
    //.then  es6语法   前一步执行完成后，将返回结果放在.then里面，通过=> 将结果取到
    //post 方法只需要2个参数  url和请求参数
  },//将新增数据（form对象）保存到后台，传到后台之前，需要有一个进行数据交互的API(ajax)
  handleDelete(id) {
    console.log(id)
    request.delete("/api/user/"+id).then(res => {
    //映射后台接口  UserController
      if (res.code === '0') {
        ElMessage.success({
          type: "success",
          message: "删除成功"
        })
      } else {
        ElMessage.error({
          type: "error",
          message: res.msg
        })
      }
      this.load()  //删除之后重新加载表格
    })
  },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
  },


  handleSizeChange(pageSize) {// 改变当前每页的个数触发
      this.pageSize = pageSize
      this.load()
  },
  handleCurrentChange(pageNum){ //改变当前页码触发
      this.currentPage=pageNum
      this.load()
  }
}
}
</script>
