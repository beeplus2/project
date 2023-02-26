<template>
  <div>
    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="活动名称" clearable v-model="activityName"></el-input>
      <el-input class="ml-5" style="width: 200px" placeholder="选择社团" clearable v-model="activityClub"></el-input>
      <el-button class="ml-5" type="primary" @click="load"><i class="el-icon-search"/> 高级查询</el-button>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="'headerBg'"
              @selection-change="handleSelectionChange">
      <el-table-column prop="acid" label="ID" width="80" align="center"></el-table-column>
      <el-table-column prop="activityName" label="活动名称" width="100" align="center"></el-table-column>
      <el-table-column prop="activityClub" label="所属社团" width="100" align="center"></el-table-column>
      <el-table-column prop="host" label="主持人" width="80" align="center"></el-table-column>
      <el-table-column prop="activityTime" label="活动时间" width="150" align="center"></el-table-column>
      <el-table-column prop="activityAddress" label="活动地址" width="150" align="center"></el-table-column>
      <el-table-column prop="activityTel" label="联系电话" width="100" align="center"></el-table-column>
      <el-table-column prop="activityState" label="状态" width="80" align="center">
        <template #default="scope">
          <el-tag v-if="scope.row.activityState === null">未审核</el-tag>
          <el-tag v-if="scope.row.activityState === 1">审核通过</el-tag>
          <el-tag v-if="scope.row.activityState === 2">申请驳回</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" @click="handleEdit(scope.row)">详情</el-button>
          <el-button
              v-if="scope.row.activityState === null"
              type="success" @click="handleEdit(scope.row)">审核</el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.acid)"
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

    <el-dialog title="社团信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="活动名称">
          <el-input style="width: 200px" v-model="form.activityName" readonly autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="所属社团">
          <el-input style="width: 100px" v-model="form.activityClub" readonly autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="主持人">
          <el-input style="width: 100px" v-model="form.host" readonly autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动时间">
          <el-input style="width: 200px" v-model="form.activityTime" readonly autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动地址">
          <el-input style="width: 200px" v-model="form.activityAddress" readonly autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系电话">
          <el-input style="width: 100px" v-model="form.activityTel" readonly autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select style="width: 100px" v-model="form.applyState">
            <el-option placehold="请选择状态"
                       v-for="item in options"
                       :key="form.applyState"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
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

export default {
  name: "Activity",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      activityName: "",
      activityClub: "",
      dialogFormVisible: false,
      multipleSelection: [],
      form: {},
      options: [{
        label: "同意",
        value: 1
      }, {
        label: "不同意",
        value: 2
      }]
    }
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      // 请求分页查询数据
      this.request.get("/activity/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          activityName: this.activityName,
          activityClub: this.activityClub
        }
      }).then(res => {
        console.log(res);
        this.tableData = res.records;
        this.total = res.total;
      })
    },
    save() {
      if (this.form.activityState === 1) {
        return false;
      }
      this.request.post("/activity", this.form).then(res => {
        if (res) {
          this.$message.success("保存成功");
          this.dialogFormVisible = false;
          this.load();
        } else {
          this.$message.error("保存失败");
        }
      })
    },
    handleEdit(row) {
      this.form = row;
      this.dialogFormVisible = true;
    },
    del(acid) {
      this.request.delete("/activity/" + acid).then(res => {
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