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
            <div class="work work_1"
                 @click="goQixaing(18052300079101)">
              <div class="block">
                <el-image :src="work_img_1"></el-image>
                <span class="demonstration">工作中</span>
              </div>
            </div>
            <div class="work work_2"
                 @click="goSensor">
              <div class="block">
                <el-image :src="work_img_2"></el-image>
                <span class="demonstration">工作中</span>
              </div>
            </div>
            <div class="work work_3"
                 @click="goSensor">
              <div class="block">
                <el-image :src="work_img_3"></el-image>
                <span class="demonstration">工作中</span>
              </div>
            </div>
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
          <div >
            <el-row type="flex"
                    justify="space-around"
                    style="padding-left:2.6vw;padding-right:2.6vw;margin-top:7.5vh;">
              <el-col class="data data_1"
                      :span="6" @click.native="goQixaing(18012200073687)">
                <el-image :src="data_img_1"></el-image>14.67℃
              </el-col>
              <el-col class="data data_2"
                      :span="6" @click.native="goQixaing(18012200073687)">
                <el-image :src="data_img_2"></el-image>16.14%
              </el-col>
              <el-col class="data data_3"
                      :span="6" @click.native="goQixaing(18012200073687)">
                <el-image :src="data_img_3"></el-image>0.73m/s
              </el-col>
            </el-row>
            <el-row type="flex"
                    justify="space-around"
                    style="padding-left:2.6vw;padding-right:2.6vw;margin-top:1.66vh">
              <el-col class="data data_4"
                      :span="6" @click.native="goQixaing(18012200073687)">
                <el-image :src="data_img_4"></el-image>东风
              </el-col>
              <el-col class="data data_5"
                      :span="6" @click.native="goQixaing(18012200073687)">
                <el-image :src="data_img_5"></el-image>0.0mm
              </el-col>
              <el-col class="data data_6"
                      :span="6" @click.native="goQixaing(18012200073687)">
                <el-image :src="data_img_6"></el-image>790.29hPa
              </el-col>
            </el-row>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      work_img_1: require("@/assets/img/icon/订单_03.png"),
      work_img_2: require("@/assets/img/icon/水滴.png"),
      work_img_3: require("@/assets/img/icon/安全-03.png"),
      data_img_1: require("@/assets/img/icon/温度.png"),
      data_img_2: require("@/assets/img/icon/形状.png"),
      data_img_3: require("@/assets/img/icon/风扇.png"),
      data_img_4: require("@/assets/img/icon/风向.png"),
      data_img_5: require("@/assets/img/icon/小水滴.png"),
      data_img_6: require("@/assets/img/icon/能源.png")
    };
  },
  methods: {
    goSensor() {
      this.$router.push("/sensor");
    },
    goQixaing(sn) {
      this.$router.push({ path: "/qixiang", query: { sn: sn } });
    }
  }
};
</script>
<style>
.el-card__header {
  padding: 0px;
}
.el-card__body {
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
  width: 10.4vw;
  height: 11.3vw;
  justify-content: center;
  font-size: 2.22vh;
  font-family: SorceHanSansCN;
  font-weight: bold;
  color: rgba(255, 255, 255, 1);
  cursor: pointer;
}

.work .block {
  width: 3.33vw;
  height: 3.95vw;
}

.work img {
  /* width: 3.33vw; */
  height: 3.95vw;
  margin-bottom: 0.125vw;
  margin-top: 2.6vw;
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
  width: 10.4vw;
  height: 5.2vw;
  border-radius: 4px;
  display: flex;
  align-items: center;
  /* justify-items: center; */
  /* justify-content: center; */
  font-size: 2.22vh;
  font-family: SorceHanSansCN;
  font-weight: bold;
  color: rgba(255, 255, 255, 1);
  cursor: pointer;
}

.data img {
  width: 2.29vw;
  height: 2.91vw;
  margin-left: 1.4vw;
}

.data_1 {
  background: linear-gradient(
    16deg,
    rgba(1, 177, 251, 1),
    rgba(65, 132, 255, 1)
  );
}

.data_2 {
  background: linear-gradient(
    -70deg,
    rgba(0, 198, 196, 1),
    rgba(3, 225, 212, 1)
  );
}

.data_3 {
  background: linear-gradient(
    16deg,
    rgba(255, 114, 0, 1),
    rgba(237, 36, 69, 1)
  );
}

.data_4 {
  background: linear-gradient(
    16deg,
    rgba(155, 120, 255, 1),
    rgba(87, 87, 223, 1)
  );
}

.data_5 {
  background: linear-gradient(
    46deg,
    rgba(255, 216, 0, 1),
    rgba(255, 108, 0, 1)
  );
}

.data_6 {
  background: linear-gradient(
    218deg,
    rgba(84, 186, 44, 1),
    rgba(149, 246, 91, 1)
  );
}
</style>