<template>
  <div>
    <div style="padding: 10px 0">
      <el-input style="width: 200px" placeholder="社团名称" clearable v-model="applyName"></el-input>
      <el-select class="ml-5" v-model="applyType" clearable placeholder="选择类型">
        <el-option
            v-for="item in options"
            :key="applyType"
            :label="item.typeName"
            :value="item.tid">
        </el-option>
      </el-select>
      <el-button class="ml-5" type="primary" @click="load"><i class="el-icon-search"/> 高级查询</el-button>
    </div>

    <el-table :data="tableData" border stripe header-cell-class-name="'headerBg'"
              @selection-change="handleSelectionChange">
      <el-table-column prop="aid" label="ID" width="80" align="center"></el-table-column>
      <el-table-column prop="applyName" label="建团申请" width="100" align="center"></el-table-column>
      <el-table-column prop="applyType" label="社团类型" width="100" align="center">
        <template #default="scope">
          <span v-for="item in options" v-if="scope.row.applyType === item.tid">{{ item.typeName }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="applicant" label="申请人" width="80" align="center">
        <template #default="scope">
          <span v-for="item in userList" v-if="scope.row.applicant === item.uid">{{ item.name }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="applyTime" label="申请时间" width="150" align="center"></el-table-column>
      <el-table-column prop="applyFound" label="建团时间" width="150" align="center"></el-table-column>
      <el-table-column prop="applyState" label="状态" width="80" align="center">
        <template #default="scope">
          <el-tag v-if="scope.row.applyState === null">未审核</el-tag>
          <el-tag v-if="scope.row.applyState === 1">审核通过</el-tag>
          <el-tag v-if="scope.row.applyState === 2">申请驳回</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" @click="handleInfo(scope.row)">详情</el-button>
          <el-button
              v-if="scope.row.applyState === null"
              type="success" @click="handleCheck(scope.row)">审核</el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.aid)"
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

    <el-dialog title="申请详情" :visible.sync="dialogFormVisible1" width="50%">
      <el-form :inline="true" label-width="80px" size="small" @submit.native.prevent>
        <el-form-item label="申请人">
          <el-input style="width: 80px" readonly autocomplete="off"
                    v-for="item in userList"
                    v-if="form.applicant === item.uid"
                    v-model="item.name"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input style="width: 50px" readonly autocomplete="off"
                    v-for="item in userList"
                    v-if="form.applicant === item.uid && item.sex === 1"
                    value="男"></el-input>
          <el-input style="width: 50px" readonly autocomplete="off"
                    v-for="item in userList"
                    v-if="form.applicant === item.uid && item.sex === 2"
                    value="女">
          </el-input>
        </el-form-item>
        <el-form-item label="社团名称">
          <el-input style="width: 150px" readonly autocomplete="off" v-model="form.applyName"></el-input>
        </el-form-item>
        <el-form-item label="社团类型">
          <el-input style="width: 100px" readonly autocomplete="off"
                    v-for="item in options"
                    v-if="form.applyType === item.tid"
                    v-model="item.typeName"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input style="width: 150px" readonly autocomplete="off"
                    v-for="item in userList"
                    v-if="form.applicant === item.uid"
                    v-model="item.tel"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input style="width: 200px" readonly autocomplete="off"
                    v-for="item in userList"
                    v-if="form.applicant === item.uid"
                    v-model="item.email"></el-input>
        </el-form-item>
        <el-form-item label="申请时间">
          <el-input style="width: 180px" readonly autocomplete="off" v-model="form.applyTime"></el-input>
        </el-form-item>
        <el-form-item label="建团理由">
          <el-input
              type="textarea"
              style="width: 400px"
              v-model="form.applyReason"
              readonly autocomplete="off">
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">关 闭</el-button>
      </div>
    </el-dialog>

    <el-dialog title="审核" :visible.sync="dialogFormVisible2" width="50%">
      <el-form :inline="true" label-width="80px" size="small" @submit.native.prevent>
        <el-form-item label="申请人">
          <el-input style="width: 80px" readonly autocomplete="off"
                    v-for="item in userList"
                    v-if="form.applicant === item.uid"
                    v-model="item.name"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input style="width: 50px" readonly autocomplete="off"
                    v-for="item in userList"
                    v-if="form.applicant === item.uid && item.sex === 1"
                    value="男"></el-input>
          <el-input style="width: 50px" readonly autocomplete="off"
                    v-for="item in userList"
                    v-if="form.applicant === item.uid && item.sex === 2"
                    value="女">
          </el-input>
        </el-form-item>
        <el-form-item label="社团名称">
          <el-input style="width: 150px" readonly autocomplete="off" v-model="form.applyName"></el-input>
        </el-form-item>
        <el-form-item label="社团类型">
          <el-input style="width: 100px" readonly autocomplete="off"
                    v-for="item in options"
                    v-if="form.applyType === item.tid"
                    v-model="item.typeName"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input style="width: 150px" readonly autocomplete="off"
                    v-for="item in userList"
                    v-if="form.applicant === item.uid"
                    v-model="item.tel"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input style="width: 200px" readonly autocomplete="off"
                    v-for="item in userList"
                    v-if="form.applicant === item.uid"
                    v-model="item.email"></el-input>
        </el-form-item>
        <el-form-item label="申请时间">
          <el-input style="width: 180px" readonly autocomplete="off" v-model="form.applyTime"></el-input>
        </el-form-item>
        <el-form-item label="建团理由">
          <el-input
              type="textarea"
              style="width: 400px"
              v-model="form.applyReason"
              readonly autocomplete="off">
          </el-input>
        </el-form-item>
        <el-form-item label="审核回复">
          <el-input
              type="textarea"
              style="width: 500px"
              v-model="form.applyTime"
              autocomplete="off">
          </el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select style="width: 100px" v-model="form.applyState">
            <el-option placehold="请选择状态"
                       v-for="item in options2"
                       :key="form.applyState"
                       :label="item.label"
                       :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">关 闭</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>

export default {
  name: "Apply",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      applyName: "",
      applyType: "",
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      multipleSelection: [],
      form: {},
      userList: {},
      options: [],
      options2: [{
        label: "同意",
        value: 1
      }, {
        label: "不同意",
        value: 2
      }]
    }
  },
  created() {
    this.getOptions();
    this.getUserList();
    this.load();
  },
  methods: {
    getOptions() {
      this.request.get("/type").then(res => {
        this.options = res.data;
      })
    },
    getUserList() {
      this.request.get("/user").then(res => {
        this.userList = res.data;
        console.log(res);
      })
    },
    load() {
      // 请求分页查询数据
      this.request.get("/apply/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          applyName: this.applyName,
          applyType: this.applyType
        }
      }).then(res => {
        console.log(res);
        this.tableData = res.records;
        this.total = res.total;
      })
    },
    save() {

      this.request.post("/apply", this.form).then(res => {
        if (res) {
          this.$message.success("保存成功");
          this.dialogFormVisible2 = false;
          this.load();
        } else {
          this.$message.error("保存失败");
        }
      })
    },
    handleInfo(row) {
      this.form = {...row};
      this.dialogFormVisible1 = true;
    },
    handleCheck(row) {
      this.form = {applyState: 1};
      this.form = {...row};
      this.dialogFormVisible2 = true;
    },
    del(aid) {
      this.request.delete("/apply/" + aid).then(res => {
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