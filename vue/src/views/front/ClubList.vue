<template>
  <div class="frontBackground">
    <div style="padding: 10px 0; text-align: right">
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
    <div class="clubList">
      <div class="club" v-for="item in form" @click="ToDetail(item)">
        <img class="img" :src="item.clubImg"/><br/>
        <div style="background-color: #2086b2">
          <span class="clubName">{{ item.clubName }}</span><br/>
          <span class="clubInfo" v-for="type in options" v-if="item.clubType === type.tid">类型：{{ type.typeName }}</span><br/>
          <span class="clubInfo" v-for="head in userList" v-if="item.clubHead === head.uid">社长：{{ head.name }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ClubList",
  data() {
    return {
      clubName: "",
      clubType: "",
      form: {},
      userList: {},
      options: {}
    }
  },
  created() {
    this.getUserList();
    this.getOptions();
    this.load();
    localStorage.removeItem("club");
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
      })
    },
    load() {
      this.request.get("/club/clubExist", {
        params: {
          clubName: this.clubName,
          clubType: this.clubType,
        }
      }).then(res => {
        this.form = res.data;
      })
    },
    ToDetail(item) {
      this.request.get("/club/" + item.cid).then(res => {
        sessionStorage.setItem("club", JSON.stringify(res.data));
        this.$router.push("/front/clubInfo");
      })
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

.club {
  width: 25%;
  height: auto;
  margin-left: 5%;
  margin-top: 10px;
  float: left;
  background-color: azure;
  text-align: center;
  cursor: pointer;
}

.img {
  width: 100%;
  height: 150px;
}

.clubName {
  font-weight: bold;
  font-size: xx-large;
  color: burlywood;
}

.clubInfo {
  font-size: large;
  color: burlywood;
}

</style>