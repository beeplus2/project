<template>
  <div style="font-size: 12px; line-height: 60px; display: flex">
    <div style="flex: 1; font-size: 20px;">
      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px;">
        <el-breadcrumb-item to="/front">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-dropdown style="width: 150px; cursor: pointer; text-align: right">
      <div style="display: inline-block">
        <img :src="user.avatar" alt="" class="mr-5" style="width: 40px; height: 40px;" align="center"/>
        <span>{{ user.name }}<i class="el-icon-setting" style="margin-left: 5px"></i></span>
      </div>
      <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="getUser">个人信息</span>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="passwordChange">修改密码</span>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="logout">退出登录</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

    <el-dialog title="个人信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form
          :model="form" :rules="rules" ref="userForm"
          label-width="80px" size="small" @submit.native.prevent>
        <el-form-item label="头像">
          <el-upload
              class="avatar-uploader"
              action="http://localhost:8888/file/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess">
            <img v-if="form.avatar" :src="form.avatar" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="账号" prop="username">
          <el-input style="width: 150px;" v-model="form.username" readonly autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="真实姓名" prop="name">
          <el-input style="width: 100px" v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select style="width: 70px" v-model="form.sex">
            <el-option
                v-for="item in options"
                :key="item.value"
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
        <el-button @click="dialogFormVisible = false">关 闭</el-button>
        <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="您确定修改吗？"
            @confirm="save"
        >
          <el-button type="primary" slot="reference">修 改</el-button>
        </el-popconfirm>
      </div>
    </el-dialog>

    <el-dialog title="修改密码" :visible.sync="dialogFormVisible2" width="40%"
               :close-on-click-modal="false">
      <el-form
          :model="list" :rules="rules" ref="form"
          label-width="200px" size="small" @submit.native.prevent>
        <el-form-item label="请输入当前密码" prop="oldPassword">
          <el-input style="width: 200px" show-password v-model="list.oldPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请输入新密码" prop="password">
          <el-input style="width: 200px" show-password v-model="list.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="请确认新密码" prop="confirmPassword">
          <el-input style="width: 200px" show-password v-model="list.confirmPassword" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">关 闭</el-button>
        <el-button type="primary" @click="passwordSave">修 改</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import rule from "@/utils/rule";

export default {
  name: "Header",
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      dialogFormVisible: false,
      dialogFormVisible2: false,
      form: {},
      list: {},
      options: [{
        value: 1,
        label: '男'
      }, {
        value: 2,
        label: '女'
      }],
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'change'},
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'change'}
        ],
        oldPassword: [
          {required: true, message: '请输入旧密码', trigger: 'change'},
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'change'}
        ],
        password: [
          {required: true, message: '请输入新密码', trigger: 'change'},
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'change'}
        ],
        confirmPassword: [
          {required: true, message: '请确认新密码', trigger: 'change'},
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'change'}
        ],
        name: [
          {required: true, message: '请输入姓名', trigger: 'change'},
          {validator: rule.chinese, message: '', trigger: 'change'}
        ],
        sex: [
          {required: true, message: '请选择性别', trigger: 'change'}
        ],
        tel: [
          {required: true, message: '请输入电话', trigger: 'change'},
          {validator: rule.telephone, message: '', trigger: 'change'}
        ],
        email: [
          {required: true, message: '请输入邮箱', trigger: 'change'},
          {validator: rule.email, message: '', trigger: 'change'}
        ],
      }
    }
  },
  computed: {
    currentPathName() {
      return this.$store.state.currentPathName;   // 需要监听的数据
    }
  },
  methods: {
    logout() {
      this.$router.push("/login");
      localStorage.removeItem("user");
    },
    async getUser() {
      this.request.get("/user/username/" + this.user.username).then(res => {
        this.form = res.data;
        this.dialogFormVisible = true;
      })
    },
    save() {
      this.$refs["userForm"].validate((valid) => {
        if (valid) {
          this.request.post("/user", this.form).then(res => {
            if (res.code === '200') {
              this.$message.success("保存成功");
              this.dialogFormVisible = false;
              // 更新浏览器存储的用户信息
              this.user = JSON.parse(localStorage.getItem("user"));
              this.user.avatar = this.form.avatar;
              localStorage.setItem("user", JSON.stringify(this.user));
            } else {
              this.$message.error("保存失败");
            }
          })
        }
      })
    },
    handleAvatarSuccess(res, file) {
      console.log(res);
      this.form.avatar = res;
    },
    passwordChange() {
      this.dialogFormVisible2 = true;
    },
    passwordSave() {
      this.$refs["form"].validate((valid) => {
        if (valid) {    // 表单校验合法
          if (this.list.oldPassword !== this.user.password) {
            this.$message.error("旧密码输入错误");
            return false;
          } else if (this.list.password !== this.list.confirmPassword) {
            this.$message.error("两次输入的密码不一致");
            return false;
          } else if (this.list.oldPassword === this.list.password) {
            this.$message.error("新旧密码不能一致");
          } else {
            this.request.get("/user/username/" + this.user.username).then(res => {
              this.form = res.data;
              this.form.password = this.list.password;
              this.request.post("/user", this.form).then(res => {
                if (res.code === '200') {
                  this.$message.success("修改成功,请重新登录");
                  this.dialogFormVisible2 = false;
                  this.logout();
                } else {
                  this.$message.error("修改失败");
                }
              })
            })
          }
        }
      })
    }
  }
}
</script>

<style>

</style>