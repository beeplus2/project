<template>
  <div>
    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="社团类型" clearable v-model="typeName"></el-input>
      <el-button class="ml-5" type="primary" @click="load"><i class="el-icon-search"/> 高级查询</el-button>
      <el-button type="primary" @click="handleAdd"><i class="el-icon-edit-outline"/> 新建社团类型</el-button>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="'headerBg'"
              @selection-change="handleSelectionChange">
      <el-table-column prop="tid" label="ID" width="150" align="center"></el-table-column>
      <el-table-column prop="typeName" label="类型名称" width="150" align="center"></el-table-column>
      <el-table-column prop="typeRemark" label="备注" width="400" align="center"></el-table-column>
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
              @confirm="del(scope.row.tid)"
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
          :page-sizes="[2, 3, 4]"
          :page-size="3"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="社团类型" :visible.sync="dialogFormVisible" width="40%">
      <el-form
          :model="form" :rules="rules" ref="form"
          label-width="80px" size="small" @submit.native.prevent>
        <el-form-item label="类型名称" prop="typeName">
          <el-input style="width: 100px" v-model="form.typeName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 6}"
              v-model="form.typeRemark"
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
  name: "Type",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 3,
      typeName: "",
      dialogFormVisible: false,
      multipleSelection: [],
      form: {},
      rules: {
        typeName: [
          {required: true, message: '请输入类型名称', trigger: 'blur'},
          {validator: rule.chinese, message: '', trigger: 'blur'},
        ]
      }
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      // 请求分页查询数据
      this.request.get("/type/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          typeName: this.typeName
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
          this.request.post("/type", this.form).then(res => {
            if (res) {
              this.$message.success("保存成功");
              this.dialogFormVisible = false;
              this.typeName = "";
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
      this.form = {};
    },
    handleEdit(row) {
      this.form = {...row};
      this.dialogFormVisible = true;
    },
    del(tid) {
      this.request.delete("/type/" + tid).then(res => {
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