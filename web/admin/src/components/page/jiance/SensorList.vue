<template>
  <div style="width:100%">
    <el-row>
      <el-col :span="12"
              style="padding-right:5px">
        <el-card body-style="height:45.64vh">
          <div slot="header"
               style="display:flex">
            <!-- <div class="card_header_pre">&nbsp;</div> -->
            <div class="card_header_title">监测站</div>
          </div>
          <h1>
            皋兰山滑坡泥石流5号监测站
          </h1>
          <div style="display:flex;  align-items: center;  justify-content: center;margin-top:5.9vh">
            <div class="work work_1" @click="goSensor">工作中</div>
            <div class="work work_2">工作中</div>
            <div class="work work_3">工作中</div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12"
              style="padding-left:5px">
        <el-card body-style="padding:0;height:45.64vh">
          <div slot="header"
               style="display:flex">
            <div class="card_header_title">气象站</div>
          </div>
          <h1>
            皋兰山环境气象站
          </h1>
          <div>
            <el-row type="flex"
                    justify="space-around"
                    style="padding-left:2.6vw;padding-right:2.6vw;margin-top:7.5vh;">
              <el-col class="data work_1"
                      :span="6">工作中</el-col>
              <el-col class="data work_2"
                      :span="6">工作中</el-col>
              <el-col class="data work_3"
                      :span="6">工作中</el-col>
            </el-row>
            <el-row type="flex"
                    justify="space-around"
                    style="padding-left:2.6vw;padding-right:2.6vw;margin-top:1.66vh">
              <el-col class="data work_1"
                      :span="6">工作中</el-col>
              <el-col class="data work_2"
                      :span="6">工作中</el-col>
              <el-col class="data work_3"
                      :span="6">工作中</el-col>
            </el-row>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
    methods:{
        goSensor(){
            this.$router.push("/sensor");
        }
    }
}
</script>
<style>
.el-card__header {
  padding: 0px;
}
.card_header_title {
  display: inline-block;
  font-size: 1.8vh;
  font-family: SourceHanSansCN;
  font-weight: bold;
  color: #fff;
  line-height: 5vh;
  background: linear-gradient(
    0deg,
    rgba(31, 119, 242, 1) 0%,
    rgba(47, 193, 229, 1) 100%
  );
  width: 6.93vw;
  height: 5vh;
  text-align: center;
}
.el-card h1 {
  margin-left: 5.36vw;
  margin-top: 5.4vh;
  font-family: SourceHanSansCN;
  font-weight: bold;
  color: rgba(24, 24, 24, 1);
  font-size: 2.77vh;
}

.work {
  display: flex;
  border-radius: 4px;
  width: 18.5vh;
  height: 20.18vh;
  align-items: center;
  justify-items: center;
  justify-content: center;
  font-size: 2.22vh;
  font-family: SorceHanSansCN;
  font-weight: bold;
  color: rgba(255, 255, 255, 1);
  cursor: pointer;
}

.work_1 {
  background: linear-gradient(
    16deg,
    rgba(1, 177, 251, 1),
    rgba(65, 132, 255, 1)
  );
  margin-right: 1.71vw;
}
.work_2 {
  background: linear-gradient(
    16deg,
    rgba(155, 120, 255, 1),
    rgba(87, 87, 223, 1)
  );
  margin-right: 1.71vw;
}

.work_3 {
  background: linear-gradient(
    -70deg,
    rgba(0, 198, 196, 1),
    rgba(3, 225, 212, 1)
  );
}

.data {
  width: 18.5vh;
  height: 9.25vh;
  border-radius: 4px;
  display: flex;
  align-items: center;
  justify-items: center;
  justify-content: center;
  font-size: 2.22vh;
  font-family: SorceHanSansCN;
  font-weight: bold;
  color: rgba(255, 255, 255, 1);
}
</style>