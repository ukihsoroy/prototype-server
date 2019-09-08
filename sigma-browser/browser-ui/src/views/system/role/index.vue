<template>
  <div class="app-container">
    <header class="header-container">
      <el-input size="mini" placeholder="搜索" style="width: 200px;" />
      <el-button size="mini" type="success" icon="el-icon-search">搜索</el-button>
      <el-button size="mini" type="primary" icon="el-icon-plus">新增</el-button>
    </header>
    <div class="middle-container">
      <el-row>
        <el-col>
          <el-card class="box-card" shadow="never">
            <div slot="header" class="clearfix">
              <span class="role-span">角色列表</span>
              <div id="opt" style="float: right">
                <el-radio-group v-model="opt" size="mini">
                  <el-radio-button label="菜单权限" />
                  <el-radio-button label="操作权限" />
                </el-radio-group>
              </div>
            </div>
            <el-table :data="data" highlight-current-row size="small" style="width: 100%;">
              <el-table-column prop="name" label="角色名称" />
              <el-table-column prop="code" label="角色编码" />
              <el-table-column :show-overflow-tooltip="true" prop="gmtCreate" label="创建日期">
                <template slot-scope="{row}">
                  <span>{{ parseTime(row.gmtCreate) }}</span>
                </template>
              </el-table-column>
              <!-- <el-table-column v-if="checkPermission(['ADMIN','ROLES_ALL','ROLES_EDIT','ROLES_DELETE'])" label="操作" width="130px" align="center">
                <template slot-scope="scope">
                  <el-button v-permission="['ADMIN','ROLES_ALL','ROLES_EDIT']" size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.row)"/>
                  <el-popover
                    v-permission="['ADMIN','ROLES_ALL','ROLES_DELETE']"
                    :ref="scope.row.id"
                    placement="top"
                    width="180">
                    <p>确定删除本条数据吗？</p>
                    <div style="text-align: right; margin: 0">
                      <el-button size="mini" type="text" @click="$refs[scope.row.id].doClose()">取消</el-button>
                      <el-button :loading="delLoading" type="primary" size="mini" @click="subDelete(scope.row.id)">确定</el-button>
                    </div>
                    <el-button slot="reference" type="danger" icon="el-icon-delete" size="mini" />
                  </el-popover>
                </template>
              </el-table-column> -->
            </el-table>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { getAllRole } from '@/api/role'
import { parseTime } from '@/utils/index'

export default {
  data() {
    return {
      data: [],
      opt: '菜单分配'
    }
  },
  created() {
    this.initDate()
  },
  methods: {
    parseTime,
    initDate() { // 初始化
      getAllRole().then(res => {
        this.data = res.data
      })
    }
  }
}
</script>
