<template>
  <div>
    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="用户名称" clearable v-model="username"></el-input>
      <el-button class="ml-5" type="primary" @click="load"><i class="el-icon-search"/> 高级查询</el-button>
      <el-button type="primary" @click="handleAdd"><i class="el-icon-edit-outline"/> 新增管理员</el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定要批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference"><i class="el-icon-remove-outline"/> 批量删除</el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="'headerBg'"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="30" align="center"></el-table-column>
      <el-table-column prop="uid" label="ID" width="50" align="center"></el-table-column>
      <el-table-column prop="username" label="账号" width="120" align="center"></el-table-column>
      <el-table-column prop="password" label="密码" width="120" align="center"></el-table-column>
      <el-table-column prop="name" label="真实姓名" width="100" align="center"></el-table-column>
      <el-table-column prop="sex" label="性别" width="50" align="center">
        <template #default="scope">
          <el-tag v-if="scope.row.sex === 1">男</el-tag>
          <el-tag v-if="scope.row.sex === 2">女</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="tel" label="电话" width="100" align="center"></el-table-column>
      <el-table-column prop="email" label="邮箱" width="200" align="center"></el-table-column>
      <el-table-column prop="type" label="类型" width="100" align="center">
        <template #default="scope">
          <el-tag v-if="scope.row.type === 1">系统管理员</el-tag>
          <el-tag v-if="scope.row.type === 2">社长</el-tag>
          <el-tag v-if="scope.row.type === 3">学生</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定重置吗？"
              @confirm="reset(scope.row)"
          >
            <el-button type="success" slot="reference">重置密码</el-button>
          </el-popconfirm>
          <el-button class="ml-5" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.uid)"
          >
            <el-button type="danger" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10]"
          :page-size="5"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form
          :model="form" :rules="rules" ref="form"
          label-width="80px" size="small" @submit.native.prevent>
        <el-form-item label="账号" prop="username">
          <el-input style="width: 150px;" v-model="form.username" :disabled="edit1" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input style="width: 200px" v-model="form.password" :disabled="edit2" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名" prop="name">
          <el-input style="width: 100px" v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select style="width: 100px" v-model="form.sex">
            <el-option placehold="请选择性别"
                       v-for="item in options"
                       :key="form.sex"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input style="width: 200px" v-model="form.tel" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input style="width: 200px" v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import rule from '@/utils/rule.js'

export default {
  name: "User",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      username: "",
      dialogFormVisible: false,
      edit1: false,
      edit2: false,
      multipleSelection: [],
      form: {},
      options: [{
        label: "男",
        value: 1
      }, {
        label: "女",
        value: 2
      }],
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
          {validator: rule.chinese, message: '', trigger: 'blur'}
        ],
        sex: [
          {required: true, message: '请选择性别', trigger: 'blur'}
        ],
        tel: [
          {required: true, message: '请输入电话', trigger: 'blur'},
          {validator: rule.telephone, message: '', trigger: 'blur'}
        ],
        email: [
          {required: true, message: '请输入邮箱', trigger: 'blur'},
          {validator: rule.email, message: '', trigger: 'blur'}
        ],
      }
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      // 请求分页查询数据
      this.request.get("/user/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username
        }
      }).then(res => {
        console.log(res);
        this.tableData = res.records;
        this.total = res.total;
      })
    },
    save() {
      this.$refs["form"].validate((valid) => {
        if (valid) {  // 表单校验合法
          this.request.post("/user", this.form).then(res => {
            if (res) {
              this.$message.success("保存成功");
              this.dialogFormVisible = false;
              this.username = "";
              this.load();
            } else {
              this.$message.error("保存失败");
            }
          })
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true;
      this.form = {type: 1, avatar: "http://localhost:8888/file/70cb27739c724102a53bfb3e8c839c9a.jpg"};
      this.edit1 = false;
      this.edit2 = false;
    },
    handleEdit(row) {
      this.form = {...row};
      this.edit1 = true;
      this.edit2 = true;
      this.dialogFormVisible = true;
    },
    del(uid) {
      this.request.delete("/user/" + uid).then(res => {
        if (res) {
          this.$message.success("删除成功");
          this.load();
        } else {
          this.$message.error("删除失败");
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val);
      this.multipleSelection = val;
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.uid);
      this.request.post("/user/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功");
          this.load();
        } else {
          this.$message.error("批量删除失败");
        }
      })
    },
    reset(row) {
      this.form = row;
      this.form.password = "123456";
      this.save();
    },
    handleSizeChange(pageSize) {
      console.log(pageSize);
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum);
      this.pageNum = pageNum;
      this.load();
    }
  }
}
</script>

<style scoped>

</style>