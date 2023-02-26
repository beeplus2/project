<template>
  <div class="wrapper">
    <div
        style="margin: 200px auto; background-color: #fff; width: 350px; height: 400px; padding: 20px; border-radius: 16px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>注 册</b></div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input placeholder="请输入账号" size="medium" style="margin: 10px 0" prefix-icon="el-icon-user"
                    v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password
                    v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword">
          <el-input placeholder="请确认密码" size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password
                    v-model="user.confirmPassword"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small" autocomplete="off" @click="register">确定</el-button>
          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/login')">返回</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%"
               :close-on-click-modal="false" :show-close="false">
      <el-form
          :model="form" :rules="rules" ref="form"
          label-width="80px" size="small" @submit.native.prevent>
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
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import rule from "@/utils/rule";

export default {
  name: "Login",
  data() {
    return {
      user: {},
      form: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur'}
        ],
        confirmPassword: [
          {required: true, message: '请确认密码', trigger: 'blur'},
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
      },
      options: [{
        label: "男",
        value: 1
      }, {
        label: "女",
        value: 2
      }],
      dialogFormVisible: false
    }
  },
  methods: {
    register() {
      this.$refs["userForm"].validate((valid) => {
        if (valid) {    // 表单校验合法
          if (this.user.password !== this.user.confirmPassword) {
            this.$message.error("两次输入的密码不一致");
            return false;
          }
          this.request.post("/user/register", this.user).then(res => {
            if (res.code === '200') {
              this.dialogFormVisible = true;
              this.$message.success("注册成功");
              this.request.get("/user/username/" + this.user.username).then(res => {
                this.form = res.data;
              })
            } else {
              this.$message.error(res.msg);
            }
          })
        }
      });
    },
    save() {
      this.$refs["form"].validate((valid) => {
        if (valid) {  // 表单校验合法
          this.request.post("/user", this.form).then(res => {
            if (res) {
              this.dialogFormVisible = false;
              this.$message.success("个人信息保存成功");
              this.$router.push("/login");
            } else {
              this.$message.error("个人信息保存失败");
            }
          })
        }
      })
    },
  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC4668, #3F5EF8);
  overflow: hidden;
}
</style>