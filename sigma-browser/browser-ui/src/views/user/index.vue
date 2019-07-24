<template>
  <div>
    <el-form
      :inline="true"
      :model="condition"
      label-width="70px"
      label-position="left"
      size="small">
      <el-row :gutter="10">
        <el-col :span="6">
          <el-form-item label="用户名">
            <el-input v-model="condition.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="昵称">
            <el-input v-model="condition.nickname" placeholder="请输入昵称"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="性别">
            <el-select v-model="condition.gender" placeholder="请选择性别">
              <el-option label="男" value="1"></el-option>
              <el-option label="女" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="角色">
            <el-select v-model="condition.roleCode" placeholder="请选择角色">
              <el-option label="管理员" value="1"></el-option>
              <el-option label="普通用户" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="手机">
            <el-input v-model="condition.mobile" placeholder="请输入手机"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="邮箱">
            <el-input v-model="condition.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="6">
          <el-form-item>
            <el-button type="primary" @click="fetchUserList" icon="el-icon-search">查询</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-table :data="users" style="width: 100%" size="small">
      <el-table-column type="index"></el-table-column>
      <el-table-column prop="username" label="用户名" width="100px"></el-table-column>
      <el-table-column prop="nickname" label="昵称" width="150px"></el-table-column>
      <el-table-column prop="birthday" label="生日" width="140px"></el-table-column>
      <el-table-column prop="gender" label="性别" width="80px"></el-table-column>
      <el-table-column prop="balance" label="余额" width="120px"></el-table-column>
      <el-table-column prop="mobile" label="手机号" width="150px"></el-table-column>
      <el-table-column prop="email" label="邮箱" width="150px"></el-table-column>
      <el-table-column prop="roleName" label="角色" width="200px"></el-table-column>
      <el-table-column prop="signature" label="签名" width="300px"></el-table-column>
      <el-table-column label="操作" fixed="right" width="200">
        <template slot-scope="scope">
          <el-button size="mini" icon="el-icon-edit" type="primary">编辑</el-button>
          <el-button size="mini" icon="el-icon-delete" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :page-sizes="[100, 200, 300, 400]"
      :page-size="100"
      layout="total, sizes, prev, pager, next, jumper"
      :total="condition.total">
    </el-pagination>
  </div>
</template>

<script>
import { getUserList } from "@/api/user";
import { get } from "https";
import { debuglog } from "util";
import { sign } from 'crypto';

export default {
  data() {
    return {
      condition: {
        nickname: "",
        username: "",
        gender: "",
        roleCode: "",
        mobile: "",
        email: "",
        total: 0,
        current: 1,
        size: 10
      },
      users: null,
      userLoading: true
    };
  },
  methods: {
    fetchUserList() {
      this.userLoading = true;
      getUserList(this.condition)
        .then(response => {
          if (response.success) {
            console.log(response)
            const {records, current, total, size} = {...response.data}
            this.users = records
            this.condition.current = current
            this.condition.total = total
            this.condition.size = size
          }
          this.userLoading = false
        })
        .catch(e => {
          this.userLoading = false;
          console.log(e);
        });
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  },
  created() {
    this.fetchUserList();
  }
};
</script>
<style>
.el-form {
  margin-top: 20px;
  margin-left: 30px;
}
.el-form-item {
  width: 100%;
}
.el-table {
  margin-top: 20px;
  margin-left: 20px;
}
.el-pagination {
  text-align: center;
  margin-top: 10px;
}
</style>
