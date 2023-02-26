<template>
  <div class="frontBackground">
    <div class="clubList">
      <div style="width: 100%; height: 300px">
        <img :src="this.club.clubImg" alt="" style="width: 30%; height: 300px; background-color: azure"/>
        <div style="float: right; width: 700px; height: 300px; padding-right: 10px">
          <el-form class="form" label-width="100px" size="small"
                   :inline="true" model="formInline">
            <el-form-item label="社团名称：" prop="clubName">
              <el-input style="width: 100px" v-model="this.club.clubName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="社团类型" prop="clubType">
              <el-input style="width: 100px" readonly autocomplete="off"
                        v-for="item in options"
                        v-if="club.clubType === item.tid"
                        v-model="item.typeName"></el-input>
            </el-form-item>
            <el-form-item label="社长" prop="applicant">
              <el-input style="width: 100px" readonly autocomplete="off"
                        v-for="item in userList"
                        v-if="club.clubHead === item.uid"
                        v-model="item.name"></el-input>
            </el-form-item>
            <el-form-item label="社团人数" prop="clubNumber">
              <el-input style="width: 50px" v-model="this.club.clubNumber" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="建团时间" prop="clubCreate">
              <el-input style="width: 200px" v-model="this.club.clubCreate" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="社团评分" prop="clubGrade">
              <el-input style="width: 50px" v-model="this.club.clubGrade" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="社团介绍" prop="clubIntroduction">
              <el-input
                  type="textarea"
                  style="width: 500px"
                  :autosize="{ minRows: 3, maxRows: 5}"
                  v-model="this.club.clubIntroduction"
                  readonly autocomplete="off">
              </el-input>
            </el-form-item>
          </el-form>
          <el-button style="float: right" type="primary" @click="clubAdd">加 入</el-button>
        </div>

      </div>
      <div style="padding: 10px 0; text-align: right">
        <span style="float: left">活动列表</span><br/>
      </div>
      <el-table :data="tableData" border stripe header-cell-class-name="'headerBg'"
                @selection-change="handleSelectionChange">
        <el-table-column prop="activityName" label="活动名称" width="150" align="center"></el-table-column>
        <el-table-column prop="activityHost" label="主持人" width="80" align="center">
          <template #default="scope">
            <span v-for="item in userList" v-if="scope.row.activityHost === item.uid">{{ item.name }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="activityTime" label="活动时间" width="150" align="center"></el-table-column>
        <el-table-column prop="activityAddress" label="活动地址" width="200" align="center"></el-table-column>
        <el-table-column prop="activityTel" label="活动电话" width="100" align="center"></el-table-column>
        <el-table-column prop="activityIntroduction" label="活动介绍" width="200" align="center"></el-table-column>
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-button type="primary" @click="handleAdd(scope.row)">报 名</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "ClubInfo",
  data() {
    return {
      club: sessionStorage.getItem("club") ? JSON.parse(sessionStorage.getItem("club")) : {},
      tableData: [],
      multipleSelection: [],
      form: {},
      userList: {},
      options: {}
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
      })
    },
    getOptions() {
      this.request.get("/type").then(res => {
        this.options = res.data;
        console.log(res);
      })
    },
    load() {
      this.request.get("/activity/club/" + this.club.cid).then(res => {
        this.tableData = res.data;
        console.log(res);
      })
    },
    handleSelectionChange(val) {
      console.log(val);
      this.multipleSelection = val;
    },
    clubAdd() {

    },
    handleAdd() {

    }
  }
}
</script>

<style scoped>

.clubList {
  overflow: hidden;
  width: 100%;
  height: 100vh;
}

.form {
  float: right;
  width: 700px;
  height: 270px;
  background-color: white;
  padding-top: 40px;
}

</style>