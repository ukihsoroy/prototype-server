<template>
  <div>
    <el-form :inline="true" :model="formInline" label-width="70px" label-position="left" size="small">
      <el-row :gutter="10">
        <el-col :span="8">
          <el-form-item label="审批人">
            <el-input v-model="formInline.user" placeholder="审批人"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="活动区域">
            <el-select v-model="formInline.region" placeholder="活动区域">
              <el-option label="区域一" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item>
            <el-button type="primary" @click="onSubmit" icon="el-icon-search">查询</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-table :data="users" style="width: 100%" size="small">
      <el-table-column type="index"></el-table-column>
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="nickname" label="昵称"></el-table-column>
      <el-table-column prop="birthday" label="生日"></el-table-column>
      <el-table-column prop="gender" label="性别"></el-table-column>
      <el-table-column prop="mobile" label="手机号"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>
      <el-table-column prop="signature" label="签名"></el-table-column>
      <el-table-column label="操作" fixed="right" width="200">
        <template slot-scope="scope">
        <el-button size="mini" icon="el-icon-edit" type="primary">编辑</el-button>
        <el-button size="mini" icon="el-icon-delete" type="danger">删除</el-button>
      </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getUserList } from '@/api/user'
import { get } from 'https';
import { debuglog } from 'util';

export default {
  data() {
    return {
      formInline: {
        user: "",
        region: ""
      },
      users: null,
      userLoading: true
    };
  },
  methods: {
    onSubmit() {
      this.fetchUserList()
      console.log("submit!");
    },
    fetchUserList() {
      this.userLoading = true
      getUserList().then((response) => {
        if (response.success) {
          this.users = response.data.records
        }
        this.userLoading = false
      }).catch((e) => {
        this.userLoading = false
        console.log(e)
      })
    }
  },
  created() {
    debugger
    this.fetchUserList()
  },
};
</script>
<style>
.el-form {
  margin-top: 20px;
  margin-left: 30px
}
.el-form-item {
  width: 100%;
}
.el-table {
  margin-top: 20px;
  margin-left: 20px
}
</style>
