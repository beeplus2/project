<template>
  <div>
    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="社团名称" clearable v-model="clubName"></el-input>
      <el-select class="ml-5" v-model="clubType" clearable placeholder="选择类型">
        <el-option
            v-for="item in options"
            :key="clubType"
            :label="item.typeName"
            :value="item.tid">
        </el-option>
      </el-select>
      <el-button class="ml-5" type="primary" @click="load"><i class="el-icon-search"/> 高级查询</el-button>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="'headerBg'"
              @selection-change="handleSelectionChange">
      <el-table-column prop="cid" label="ID" width="80" align="center"></el-table-column>
      <el-table-column prop="clubName" label="社团名称" width="100" align="center"></el-table-column>
      <el-table-column prop="clubType" label="社团类型" width="100" align="center">
        <template #default="scope">
          <span v-for="item in options" v-if="scope.row.clubType === item.tid">{{ item.typeName }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="clubHead" label="现任社长" width="80" align="center">
        <template #default="scope">
          <span v-for="item in userList" v-if="scope.row.clubHead === item.uid">{{ item.name }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="clubIntroduction" label="社团简介" width="300" align="center"></el-table-column>
      <el-table-column prop="clubCreate" label="创建时间" width="150" align="center"></el-table-column>
      <el-table-column prop="clubState" label="状态" width="80" align="center">
        <template #default="scope">
          <el-tag v-if="scope.row.clubState === 1">正常</el-tag>
          <el-tag v-if="scope.row.clubState === 2">已解散</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.cid)"
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
          :page-sizes="[3, 4, 5]"
          :page-size="3"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="社团信息" :visible.sync="dialogFormVisible" width="40%">
      <el-form
          :model="form" :rules="rules" ref="form"
          label-width="100px" size="small" @submit.native.prevent>
        <el-form-item label="社团名称：" prop="clubName">
          <el-input style="width: 150px" v-model="form.clubName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="社团类型：" prop="clubType">
          <el-select style="width: 150px" v-model="form.clubType" placeholder="选择类型">
            <el-option
                v-for="item in options"
                :key="form.clubType"
                :label="item.typeName"
                :value="item.tid">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="现任社长：" prop="clubHead">
          <span style="width: 100px" v-for="item in userList" v-if="form.clubHead === item.uid">{{ item.name }}</span>
        </el-form-item>
        <el-form-item style="width: 400px" label="社团简介：" prop="clubIntroduction">
          <el-input
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 4}"
              v-model="form.clubIntroduction"
              autocomplete="off">
          </el-input>
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

import rule from "@/utils/rule";

export default {
  name: "Club",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 3,
      clubName: "",
      clubType: "",
      dialogFormVisible: false,
      multipleSelection: [],
      form: {},
      userList: {},
      options: {},
      rules: {
        clubName: [
          {required: true, message: '请输入社团名称', trigger: 'blur'},
        ],
        clubType: [
          {required: true, message: '请选择社团类型', trigger: 'blur'},
        ],
        head: [
          {required: true, message: '请输入社长名称', trigger: 'blur'},
          {validator: rule.chinese, message: '', trigger: 'blur'}
        ],
        introduction: [
          {required: true, message: '请输入社团简介', trigger: 'blur'},
        ]
      }
    }
  },
  created() {
    this.getUserList();
    this.getOptions();
    this.load();
  },
  methods: {
    getUserList() {
      this.request.get("/user").then(res => {
        this.userList = res.data;
        console.log(res);
      })
    },
    getOptions() {
      this.request.get("/type").then(res => {
        this.options = res.data;
        console.log(res);
      })
    },
    load() {
      // 请求分页查询数据
      this.request.get("/club/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          clubName: this.clubName,
          clubType: this.clubType
        }
      }).then(res => {
        console.log(res);
        this.tableData = res.records;
        this.total = res.total;
      })
    },
    save() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.request.post("/club", this.form).then(res => {
            if (res) {
              this.$message.success("保存成功");
              this.dialogFormVisible = false;
              this.clubName = "";
              this.clubType = "";
              this.load();
            } else {
              this.$message.error("保存失败");
            }
          })
        }
      })
    },
    handleEdit(row) {
      this.form = {...row};
      this.dialogFormVisible = true;
    },
    del(cid) {
      this.request.delete("/club/" + cid).then(res => {
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