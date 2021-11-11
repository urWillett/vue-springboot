<!--主体-->
<template>

  <div>
<!--功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary">新增</el-button>
      <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
    </div>
<!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="输入关键字" style="width: 20%"/>
      <el-button type="primary" style="margin-left: 5px">查询</el-button>
    </div>
<!--    数据显示-->
    <el-table :data="tableData" border style="width: 100%" stripe>

      <el-table-column prop="date" label="Date" sortable />
      <el-table-column prop="name" label="Name"  />
      <el-table-column prop="address" label="Address" />
<!--      编辑和删除-->
      <el-table-column fixed="right" label="操作" >
        <template #default="scope">
       <el-popconfirm title="确认删除吗？">  <!--  气泡确认框  -->
            <template #reference>
          <el-button type="danger" size="mini" @click="handleClick"
          >删除</el-button
          >
            </template>
          </el-popconfirm>
          <el-button  size="mini">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
<!--    分页-->
    <div class="demo-pagination-block">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[5,10,20]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src


import {ref} from "vue";

export default {
  name: 'Home',
  components: {

  },
  data(){
    return {
      currentPage:1,
      total:10,
      search:ref('' ),
      tableData:[
        {
          date: '2016-05-03',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-02',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-04',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
        {
          date: '2016-05-01',
          name: 'Tom',
          address: 'No. 189, Grove St, Los Angeles',
        },
      ],
    }
  },
methods: {
  handleClick() {
  },

  handleSizeChange() {
  },
  handleCurrentChange(){
  }
}
}
</script>
