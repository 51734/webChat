# webChat
## 1、项目为个人正在进行中项目，前端参考layui，该系统主要使用sprintboot+netty+mybatis+redis等技术。主要实现网页端点对点聊天、群聊、互加好友、发帖、回复、个人动态展示、个人主页等功能。系统主要使用ajax进行数据交互，数据库为mysql。项目完善中……已实现聊天、群聊、加好友以及登录注册等。
## 2、主要技术：
### 1）聊天功能主要属于netty整合websocket实现；
### 2）系统采用redis存储用户未收到的消息，方便登陆时接收；
### 3）ajax实现数据交互；
### 4）rocketMq存储好友申请的消息
### 5）前端展示借鉴了layui的社区网页。

<template>
  <div class="card content-box">
    <ECharts :option="option" />
  </div>
</template>

<script setup lang="ts" name="columnChart">
import { onMounted, ref } from "vue";
import { ECOption } from "@/components/ECharts/config";
import ECharts from "@/components/ECharts/index.vue";
import { computed } from "vue";

const optionSource = ref()
const test = (data: any, x) => {
  const tem = data.map(item => item[x]);
  const xAxis:any = [...new Set(tem)]
  xAxis.sort();
  const keys: any[] = [];
  const yAxis: any[][] = []
  xAxis.forEach((element:any) => {
    yAxis.push([element])
  });
  data.forEach(element => {
    Object.keys(element).forEach(key => {
      if (!keys.includes(key) && typeof(element[key]) == 'number') {
        keys.push(key)
      }
    })
  });
  for(let index = 0; index < xAxis.length; index++) {
    const xValue = xAxis[index];
    yAxis.forEach((element: any) => {
      const values = data.filter(item => item[x] == xValue)
      let sum = 0;
      const fieldName = element[0];
      values.forEach(value => {
        sum += value[fieldName];
      });
      element.push(sum)
    });
  }
  let result = [["product",...keys],...yAxis]
  console.log(result)
  optionSource.value = result
}
onMounted(() => {
  const data = [{
    time: "2023-02",
    carModel : "F1",
    a: 1,
    b: 2,
    c: 3 
  },{
    time: "2023-02",
    carModel : "F1",
    a: 1,
    b: 2,
    c: 3 
  }]
  test(data, "time")
})
const option: ECOption = computed(() =>({
  legend: {},
  tooltip: {},
  dataset: { 
    source: optionSource.value
    // source: [
    //   ['product', '2015', '2016', '2017'],
    //   ['Matcha Latte', 43.3, 85.8, 93.7],
    //   ['Milk Tea', 83.1, 73.4, 55.1],
    //   ['Cheese Cocoa', 86.4, 65.2, 82.5],
    //   ['Walnut Brownie', 72.4, 53.9, 39.1]
    // ]
  },
  xAxis: { type: 'category' },
  yAxis: {},
  // Declare several bar series, each will be mapped
  // to a column of dataset.source by default.
  series: [{ type: 'bar' }, { type: 'bar' }, { type: 'bar' }]
}));
</script>

<style scoped lang="scss">
@import "./index.scss";
</style>

