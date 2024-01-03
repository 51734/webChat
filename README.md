# webChat
## 1、项目为个人正在进行中项目，前端参考layui，该系统主要使用sprintboot+netty+mybatis+redis等技术。主要实现网页端点对点聊天、群聊、互加好友、发帖、回复、个人动态展示、个人主页等功能。系统主要使用ajax进行数据交互，数据库为mysql。项目完善中……已实现聊天、群聊、加好友以及登录注册等。
## 2、主要技术：
### 1）聊天功能主要属于netty整合websocket实现；
### 2）系统采用redis存储用户未收到的消息，方便登陆时接收；
### 3）ajax实现数据交互；
### 4）rocketMq存储好友申请的消息
### 5）前端展示借鉴了layui的社区网页。

<template>
  <div>
    <el-button type="primary" @click="handleAdd"> 新增 </el-button>
  </div>
  <div>
    <el-table :data="tableData" style="width: 100%" @cell-click="showUnitInput">
      <el-table-column
        :prop="item.prop"
        :label="item.label"
        v-for="(item, index) in tableHeader"
        :key="item.prop"
      >
        <template #default="{ row, column }">
          <el-input
            v-if="tableRowEditId === row.id && tableColumnEditIndex === column.id"
            v-model="row[item.prop]"
          />
        </template>
      </el-table-column>
      <el-table-column label="Operate">
        <template #default="{ row }">
          <el-button type="danger" link @click="handleDelete(row)">Delete</el-button>
          <el-button type="normal" link @click="handleEdit(row)">Edit</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { ref } from 'vue'

let tableRowEditId = ref(null) // 控制可编辑的每一行
let tableColumnEditIndex = ref(null) //控制可编辑的每一列

const showUnitInput = (row, column) => {
  //赋值给定义的变量
  tableRowEditId.value = row.id //确定点击的单元格在哪行 如果数据中有ID可以用ID判断，没有可以使用其他值判断，只要能确定是哪一行即可
  tableColumnEditIndex.value = column.id //确定点击的单元格在哪列
}
const blurValueInput = (row, column) => {
  // tableRowEditId.value = null
  // tableColumnEditIndex.value = null
  //在此处调接口传数据
}
const tableHeader = ref([
  {
    prop: 'carType',
    label: '车型号'
  },
  {
    prop: 'carVersion',
    label: '版本号'
  },
  {
    prop: 'carNum',
    label: '车辆数'
  },
  {
    prop: 'parkInNum',
    label: '泊入次数'
  },
  {
    prop: 'parkInSuccess',
    label: '泊入成功'
  },
  {
    prop: 'parkOutNum',
    label: '泊出次数'
  },
  {
    prop: 'parkOutSuccess',
    label: '泊出成功'
  },
  {
    prop: 'eventNum',
    label: '问题数'
  },
  {
    prop: 'accNum',
    label: '事故数'
  },
  {
    prop: 'startTime',
    label: '开始时间'
  },
  {
    prop: 'endTime',
    label: '截至时间'
  }
])
const tableData = ref([{}])

const handleDelete = (row) => {
  const index = tableData.value.indexOf(row)
  if (index !== -1) {
    tableData.value.splice(index, 1)
  }
}

const handleEdit = (row) => {
  console.log(row)
}

const handleAdd = () => {
  tableData.value.unshift({})
}
</script>

<style></style>
