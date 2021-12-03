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
      <el-table-column prop="title" label="标题"  />
      <el-table-column prop="author" label="作者" />  <!--数据库中有_，查询出来显示驼峰-->
      <el-table-column prop="time" label="时间" />

      <!--      编辑和删除-->
      <el-table-column fixed="right" label="操作" >
        <template #default="scope">
          <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.id)" >  <!--  气泡确认框  -->
            <template #reference>
              <el-button type="danger" size="mini" >删除</el-button>
            </template>
          </el-popconfirm>
          <el-button  size="mini " @click="handleEdit(scope.row)">编辑</el-button>
          <el-button  size="mini " @click="details(scope.row)">详情</el-button>
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
        title="提示"
        width="50%"
        :before-close="handleClose"
    >
      <el-form :model="form" label-width="120px">

        <el-form-item label="标题">
          <el-input v-model="form.title" style="width: 80%"></el-input>
        </el-form-item>
<!--        <el-form-item label="作者">-->
<!--          <el-input v-model="form.author" style="width: 80%"></el-input>-->
<!--        </el-form-item>-->
        <div id="div1"></div>
<!--        <el-form-item label="内容">-->
<!--          <el-input v-model="form.content" style="width: 80%"></el-input>-->
<!--        </el-form-item>-->
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确认</el-button>
      </span>
      </template>
    </el-dialog>
    <el-button :plain="true" @click="open1">成功</el-button>

    <el-dialog
        v-model="vis"
        title="详情"
        width="50%"
        >
      <el-card>
        <div v-html="detail.content" style="min-height: 100px"></div>
      </el-card>

    </el-dialog>



  </div>
</template>

<script>
// @ is an alias to /src


import {ref,defineComponent} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
import request from "../utils/request";
import E from 'wangeditor';

let editor;

export default  {
  name: 'News',
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
      vis:false, //控制弹窗开启和关闭
      detail:{},
    }
  },
  created() {
    this.load()
  }, //页面预加载时调用
  mounted() {

  }, //页面所有元素执行完之后 才执行mounted里面的代码
  methods: {
    details(row){
      this.detail =row
      this.vis = true //开启弹窗
    },
    filesUploadSuccess(res){
      console.log(res)
      this.form.cover = res.data
    },
    load(){
      request.get("/api/news",{
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

      this.$nextTick(()=> {
            if (!editor) {
//关联弹窗里面的div，new一个editor对象来做文本编辑器
              editor = new E('#div1') // wangeditor

              // 配置 server 接口地址
              editor.config.uploadImgServer = 'http://localhost:9090/files/editor/upload'
              editor.config.uploadFileName = 'file'   //设置上传参数名称
              editor.create()   //加载wangeditor

            }
      })
    }, //打开新增弹窗
    save(){
      this.form.content = editor.txt.html()
      //获取编辑器里面的值 然后赋予到实体当中
      //使用 editor.txt.html() 获取 html 。
      // 编辑器中获取的 html 代码是不包含任何样式的纯 html，如果显示的时候需要对其中的 <table> <code> <blockquote> 等标签进行自定义样式


      if (this.form.id){ //更新
        request.put("/api/news",this.form).then(res => {
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
          editor.txt.clear() //清空编辑器数据
        })
      } else {   //新增

        let peopleStr = sessionStorage.getItem("people")||"{}"
        let people =JSON.parse(peopleStr)
        this.form.author = people.nickName
        //新增时获取登陆时people所保存的用户信息
        request.post("/api/news", this.form).then(res => {
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
          editor.txt.clear() //清空编辑器数据
        })
      }
      //.then  es6语法   前一步执行完成后，将返回结果放在.then里面，通过=> 将结果取到
      //post 方法只需要2个参数  url和请求参数
    },//将新增数据（form对象）保存到后台，传到后台之前，需要有一个进行数据交互的API(ajax)
    handleDelete(id) {
      console.log(id)
      request.delete("/api/news/"+id).then(res => {
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

      this.$nextTick(()=> {
            if (!editor) {
//关联弹窗里面的div，new一个editor对象来做文本编辑器
              editor = new E('#div1') // wangeditor
              editor.create()   //加载wangeditor
              // 配置 server 接口地址
              editor.config.uploadImgServer = 'http://localhost:9090/files/editor/upload'
              editor.config.uploadFileName = 'file'   //设置上传参数名称
              editor.txt.html(row.content)
            }
      })
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
