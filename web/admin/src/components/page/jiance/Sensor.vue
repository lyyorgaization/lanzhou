<template>
  <div style="width:100%">
    <el-row>
      <el-col :span="12"
              style="padding-right:5px">
        <el-card body-style="height:25.67vw">
          <div slot="header"
               style="display:flex">
            <!-- <div class="card_header_pre">&nbsp;</div> -->
            <div class="card_header_title">简介</div>
          </div>
          <h1 style="font-size:2.68vh;font-family:SourceHanSansCN;font-weight:bold;color:rgba(24,24,24,1);line-height:53px;">皋兰山地质环境监测站</h1>
          <div style="display:flex;justify-content:center">
            <el-image class="i-image"
                      v-for="image in srcList"
                      :key="image"
                      :src="image"
                      :preview-src-list="srcList"
                      fit="contain">
            </el-image>
          </div>
          <div style="font-size:0.9vw">
            <p>
              一、皋兰山地质环境监测站简介</p>
            <p>
              甘肃工程地质研究院皋兰山地质环境监测站位于兰州市城区南侧，皋兰山以东，城关区皋兰山乡民族村以北，交通便利，沟口为南山陵园墓区，沟前为兰州火车站区域。站内老狼沟为典型的黄土沟壑区地貌，沟谷南北走向，西侧发育两条支沟，沟道切割严重。沟道内高程起伏大，陡坡发育，植被整体不发育，且沟道内分布大量松散堆积物，为降雨诱发滑坡和泥石流提供重要的基础。沟内发育滑坡91处，其中旋转式滑动49处，平移滑动37处，崩塌有5处，是研究地质灾害滑坡的天然实验室...
            </p>
          </div>
          <el-button @click="intro_visible=true" style="margin-top:3.6vh;background:linear-gradient(77deg,rgba(0,157,236,1) 0%,rgba(35,188,255,1) 100%);box-shadow:0px 2px 7px 0px rgba(35,188,255,0.39);border-radius:4px;">更多详情</el-button>
        </el-card>
        <el-dialog :visible.sync="intro_visible"
                   width="76vw"
                   top="5vw">
          <div slot="title"
               class="dialog_head">
            详情介绍
          </div>
          <div>
            <el-image fit="contain" :src="require('@/assets/img/gaolanshan_sensor.png')">

            </el-image>
            <p>甘肃工程地质研究院皋兰山地质环境监测站位于兰州市城区南侧，皋兰山以东，城关区皋兰山乡民族村以北，交通便利，沟口为南山陵园墓区，沟前为兰州火车站区域。站内老狼沟为典型的黄土沟壑区地貌，沟谷南北走向，西侧发育两条支沟，沟道切割严重。沟道内高程起伏大，陡坡发育，植被整体不发育，且沟道内分布大量松散堆积物，为降雨诱发滑坡和泥石流提供重要的基础。沟内发育滑坡91处，其中旋转式滑动49处，平移滑动37处，崩塌有5处，是研究地质灾害滑坡的天然实验室...</p>
          </div>
        </el-dialog>
      </el-col>
      <el-col :span="12"
              style="padding-left:5px">
        <el-card body-style="padding:0;height:25.67vw">
          <div slot="header"
               style="display:flex">
            <div class="card_header_title">数据总览</div>
          </div>
          <el-row style="widht:50%"
                  type="flex"
                  justify="space-between"
                  class="data_overview">
            <el-col :span="12">
              <el-row>
                <el-col :span="12"
                        class="item item-left">
                  <img src="@/assets/img/icon/自动监测站.png" />
                  <div style="display:inline-block">
                    <span class="count">1</span>
                    <br />
                    <span class="name">监测站</span>
                  </div>
                </el-col>
                <el-col :span="12"
                        class="item"
                        style="background:linear-gradient(46deg,rgba(255,216,0,1),rgba(255,108,0,1));">
                  <img src="@/assets/img/icon/shebei.png" />
                  <div style="display:inline-block">
                    <span class="count">17</span>
                    <br />
                    <span class="name">设备数</span>
                  </div>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12"
                        class="item  item-left"
                        style="background:linear-gradient(16deg,rgba(155,120,255,1),rgba(87,87,223,1));">
                  <img src="@/assets/img/icon/数据采集.png" />
                  <div style="display:inline-block">
                    <span class="count">2240</span>
                    <br />
                    <span class="name">已采集天数</span>
                  </div>
                </el-col>
                <el-col :span="12"
                        class="item"
                        style="background:linear-gradient(218deg,rgba(84,186,44,1),rgba(149,246,91,1));">
                  <img src="@/assets/img/icon/数据.png" />
                  <div style="display:inline-block">
                    <span class="count">1427</span>
                    <br />
                    <span class="name">已收集数据</span>
                  </div>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12"
                        class="item  item-left"
                        style="background:linear-gradient(-70deg,rgba(0,198,196,1),rgba(3,225,212,1))">
                  <img src="@/assets/img/icon/动态监测.png" />
                  <div style="display:inline-block">
                    <span class="count">127</span>
                    <br />
                    <span class="name">已正常监测</span>
                  </div>
                </el-col>
                <el-col :span="12"
                        class="item"
                        style="background:linear-gradient(16deg,rgba(255,114,0,1),rgba(237,36,69,1));">
                  <img src="@/assets/img/icon/预警.png" />
                  <div style="display:inline-block">
                    <span class="count"
                          style="color:#FFF">1</span>
                    <br />
                    <span class="name">已预警</span>
                  </div>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="12">
              <div>
                <ve-ring :data="ringData"
                         :settings="ringSetting"
                         legend-position="bottom"
                         :title="{text:'设备传感器占比'}"
                         height="10.46vw"></ve-ring>
              </div>
              <div>
                <ve-line :xAixs="{boundaryGap :false}"
                         height="20.75vw"
                         :data="lineData"
                         :settings="lineSetting"
                         :title="{text:'布置设备数'}"></ve-line>
              </div>
            </el-col>
          </el-row>
          <!-- <img src="@/assets/img/statistic.png" style="height：412px" /> -->
        </el-card>
      </el-col>
    </el-row>
    <el-card style="margin:10px"
             class="sensor-info">
      <div slot="header"
           style="display:flex">
        <!-- <div class="card_header_pre">&nbsp;</div> -->
        <div class="card_header_title">设备基本信息</div>
      </div>
      <el-row>
        <el-col :span="4"
                style="padding:10px">
          <a style="cursor:hand"
             @click="handleShowSensor('空气温度传感器','t1')">
            <el-card :body-style="{ padding: '0px' }">
              <img src="@/assets/img/sensor/air_temperature.png"
                   class="image" />
              <div style="padding: 14px;"
                   :class="[sensorActive=='t1'?'sensor_info_check':'sensor_info']">
                <span>空气温度传感器</span>
                <div class="bottom clearfix">
                  <el-form label-position="left"
                           label-width="80px">
                    <el-form-item label="当前状态">
                      <span>正常</span>
                    </el-form-item>
                    <el-form-item label="当前值">
                      <span>{{sensorData.t1.last}}</span>
                    </el-form-item>
                  </el-form>
                </div>
              </div>
            </el-card>
          </a>
        </el-col>
        <el-col :span="4"
                style="padding:10px">
          <a style="cursor:hand"
             @click="handleShowSensor('土壤温湿度传感器','humidity1_mean')">
            <el-card :body-style="{ padding: '0px' }">
              <img src="@/assets/img/sensor/soil_moisture.png"
                   class="image" />
              <div style="padding: 14px;"
                   :class="[sensorActive=='humidity1_mean'?'sensor_info_check':'sensor_info']">
                <span>土壤温湿度传感器</span>
                <div class="bottom clearfix">
                  <el-form label-position="left"
                           label-width="80px">
                    <el-form-item label="当前状态">
                      <span>正常</span>
                    </el-form-item>
                    <el-form-item label="当前值">
                      <span>{{sensorData.humidity1_mean.last}}</span>
                    </el-form-item>
                  </el-form>
                </div>
              </div>
            </el-card>
          </a>
        </el-col>
        <el-col :span="4"
                style="padding:10px">
          <a style="cursor:hand"
             @click="handleShowSensor('孔隙压传感器','pressure1_mean')">
            <el-card :body-style="{ padding: '0px' }">
              <img src="@/assets/img/sensor/pore_pressure.png"
                   class="image" />
              <div style="padding: 14px;"
                   :class="[sensorActive=='pressure1_mean'?'sensor_info_check':'sensor_info']">
                <span>孔隙压传感器</span>
                <div class="bottom clearfix">
                  <el-form label-position="left"
                           label-width="80px">
                    <el-form-item label="当前状态">
                      <span>正常</span>
                    </el-form-item>
                    <el-form-item label="当前值">
                      <span>{{sensorData.pressure1_mean.last}}</span>
                    </el-form-item>
                  </el-form>
                </div>
              </div>
            </el-card>
          </a>
        </el-col>
        <el-col :span="4"
                style="padding:10px">
          <a style="cursor:hand"
             @click="handleShowSensor('雨量计','rainfall_mean')">
            <el-card :body-style="{ padding: '0px' }">
              <img src="@/assets/img/sensor/rain.png"
                   class="image" />
              <div style="padding: 14px;"
                   :class="[sensorActive=='rainfall_mean'?'sensor_info_check':'sensor_info']">
                <span>雨量计</span>
                <div class="bottom clearfix">
                  <el-form label-position="left"
                           label-width="80px">
                    <el-form-item label="当前状态">
                      <span>正常</span>
                    </el-form-item>
                    <el-form-item label="当前值">
                      <span>{{sensorData.rainfall_mean.last}}</span>
                    </el-form-item>
                  </el-form>
                </div>
              </div>
            </el-card>
          </a>
        </el-col>
        <el-col :span="4"
                style="padding:10px">
          <a style="cursor:hand"
             @click="handleShowSensor('微震监测仪','illuminance_mean')">
            <el-card :body-style="{ padding: '0px' }">
              <img src="@/assets/img/sensor/microseism.png"
                   class="image" />
              <div style="padding: 14px;"
                   :class="[sensorActive=='illuminance_mean'?'sensor_info_check':'sensor_info']">
                <span>微震监测仪</span>
                <div class="bottom clearfix">
                  <el-form label-position="left"
                           label-width="80px">
                    <el-form-item label="当前状态">
                      <span>正常</span>
                    </el-form-item>
                    <el-form-item label="当前值">
                      <span>{{sensorData.illuminance_mean.last}}</span>
                    </el-form-item>
                  </el-form>
                </div>
              </div>
            </el-card>
          </a>
        </el-col>
        <el-col :span="4"
                style="padding:10px">
          <a style="cursor:hand"
             @click="handleShowSensor('不极化化电极','V1_mean')">
            <el-card :body-style="{ padding: '0px' }">
              <img src="@/assets/img/sensor/GNSS.png"
                   class="image" />
              <div style="padding: 14px;"
                   :class="[sensorActive=='V1_mean'?'sensor_info_check':'sensor_info']">
                <span>不极化化电极</span>
                <div class="bottom clearfix">
                  <el-form label-position="left"
                           label-width="80px">
                    <el-form-item label="当前状态">
                      <span>正常</span>
                    </el-form-item>
                    <el-form-item label="当前值">
                      <span>{{sensorData.V1_mean.last}}</span>
                    </el-form-item>
                  </el-form>
                </div>
              </div>
            </el-card>
          </a>
        </el-col>
      </el-row>
    </el-card>
    <el-dialog :visible.sync="sensorDialog.visible"
               width="76vw"
               top="5vw">
      <div slot="title"
           class="dialog_head">
        {{sensorDialog.title}}
      </div>
      <el-row class="summary">
        <el-col :span="6"
                style="display: flex;    align-items: center;">
          <img src="@/assets/img/icon/max.jpg" />
          <div style="padding-left:15px;font-size:0.08vw;font-family:Microsoft YaHei;font-weight:bold;color:rgba(140,140,140,1)">
            最小
            <br />
            <span style="font-size:0.9vw;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1)">{{sensorDialog.max}}</span>
          </div>
        </el-col>
        <el-col :span="6"
                style="display: flex;    align-items: center;">
          <img src="@/assets/img/icon/min.jpg" />
          <div style="padding-left:15px;font-size:0.08vw;font-family:Microsoft YaHei;font-weight:bold;color:rgba(140,140,140,1)">
            最小
            <br />
            <span style="font-size:0.9vw;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1)">{{sensorDialog.min}}</span>
          </div>
        </el-col>
        <el-col :span="6"
                style="display: flex;    align-items: center;">
          <img src="@/assets/img/icon/avg.jpg" />
          <div style="padding-left:15px;font-size:0.08vw;font-family:Microsoft YaHei;font-weight:bold;color:rgba(140,140,140,1)">
            最小
            <br />
            <span style="font-size:0.9vw;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1)">{{sensorDialog.avg}}</span>
          </div>
        </el-col>
        <el-col :span="6"
                style="display: flex;    align-items: center;">
          <img src="@/assets/img/icon/ct.jpg" />
          <div style="padding-left:15px;font-size:0.8vw;font-family:Microsoft YaHei;font-weight:bold;color:rgba(140,140,140,1)">
            最小
            <br />
            <span style="font-size:0.9vw;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1)">{{sensorDialog.last}}</span>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top:20px"
              class="info">
        <el-col :span="6">
          <el-image style="width: 16vw; height: 17vw"
                    :src="require('@/assets/img/sensor/pore_pressure.png')"></el-image>
        </el-col>
        <el-col :span="18"
                style="padding-left:37px">
          <div style="font-size:0.9vw;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1);">功能</div>
          <p style="margin-top:13px;margin-bottom:13px;font-size:0.7vw;font-family:Microsoft YaHei;font-weight:bold;color:rgba(140,140,140,1);">使用高精度、高灵敏度和高可靠性MEMS孔隙水压力传感器，监控孔隙压力压力的变化；支持各种接口的数据采集仪；先进的技术，更高的精度， 实现了孔隙压监测自动化、简单化。</p>
          <div style="font-size:0.9vw;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1);">指标</div>
          <el-row style="margin-top:13px"
                  class="quota">
            <el-col :span="12">
              <el-card body-style="padding:0">
                <div slot="header">
                  <div class="info_title">物理特性</div>
                </div>
                <el-form label-width="5.2vw"
                         label-position="left">
                  <el-form-item label="尺寸">
                    <span>自定义</span>
                  </el-form-item>
                  <el-form-item label="工作温度">
                    <span>-20℃ ~ +70℃</span>
                  </el-form-item>
                  <el-form-item label="防护等级">
                    <span>IP67</span>
                  </el-form-item>
                </el-form>
              </el-card>
            </el-col>
            <el-col :span="12">
              <el-card body-style="padding-top:0;padding-bottom:0">
                <div slot="header">
                  <div class="info_title">量程</div>
                </div>
                <el-form label-width="100px"
                         label-position="left">
                  <el-form-item label="精度">
                    <span>0.25%</span>
                  </el-form-item>
                  <el-form-item label="量程">
                    <span>0~15kPa~200kPa</span>
                  </el-form-item>
                  <el-form-item label="接口">
                    <span>模拟、RS485/232</span>
                  </el-form-item>
                </el-form>
              </el-card>
            </el-col>
          </el-row>
          <el-row class="quota">
            <el-col :span="12">
              <el-card body-style="padding:0">
                <div slot="header">
                  <div class="info_title">电气指示</div>
                </div>
                <el-form label-width="5.2vw"
                         label-position="left">
                  <el-form-item label="电压">
                    <span>8-24V</span>
                  </el-form-item>
                  <el-form-item label="功耗">
                    <span>0.75W</span>
                  </el-form-item>
                </el-form>
              </el-card>
            </el-col>
          </el-row>
        </el-col>
      </el-row>
      <ve-line :data="chartData"
               :settings="chartSettings"
               :dataZoom="dataZoom"
               :not-set-unchange="['dataZoom']"
               height="14.8vw"></ve-line>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      srcList: [
        require("@/assets/img/jiance1.jpg"),
        require("@/assets/img/jiance2.jpg"),
        require("@/assets/img/jiance3.jpg")
      ],
      intro_visible:false,
      location: { lat: 35.78786946, lng: 103.98119978 },
      zoom: 7,
      addressKeyword: "",
      mapType: "BMAP_SATELLITE_MAP",
      sensorData: {
        t1: {},
        humidity1_mean: {},
        pressure1_mean: {},
        rainfall_mean: {},
        illuminance_mean: {},
        V1_mean: {}
      },
      sensorDialog: {
        title: "空气温度传感器",
        visible: false,
        max: 0,
        min: 0,
        avg: 0,
        last: 0
      },
      sensorActive: "",
      chartData: {
        columns: ["date", "value"],
        rows: []
      },
      chartSettings: {
        min: ["dataMin", "dataMin"]
      },
      dataZoom: [
        {
          show: true,
          realtime: true,
          start: 65,
          end: 85
        },
        {
          type: "inside",
          realtime: true,
          start: 65,
          end: 85
        }
      ],
      ringData: {
        columns: ["设备传感器", "data"],
        rows: [
          { 设备传感器: "空气温度传感器", data: 1393 },
          { 设备传感器: "大气压传感器", data: 3530 },
          { 设备传感器: "雨量计", data: 2923 },
          { 设备传感器: "微震监测仪", data: 1723 },
          { 设备传感器: "孔隙压传感器", data: 3792 },
          { 设备传感器: "拉线式位移", data: 4593 }
        ]
      },
      ringSetting: {
        legendLimit: 0,
        radius: [24, 45],
        offsetY: 90
      },
      lineData: {
        columns: ["日期", "data"],
        rows: [
          {
            日期: "2020-01-01",
            data: 1393
          },
          {
            日期: "2020-02-01",
            data: 3530
          },
          {
            日期: "2020-03-01",
            data: 2923
          },
          {
            日期: "2020-04-01",
            data: 1723
          },
          {
            日期: "2020-05-01",
            data: 3792
          },
          {
            日期: "2020-06-01",
            data: 4593
          }
        ]
      },
      lineSetting: {
        legendLimit: 0,
        area: true,
        xAxisType: "time"
      }
    };
  },
  mounted() {
    // this.baiduMap();
  },
  created() {
    this.searchSensors();
  },
  methods: {
    searchSensors() {
      this.getSensorData("t1");
      this.getSensorData("humidity1_mean");
      this.getSensorData("pressure1_mean");
      this.getSensorData("rainfall_mean");
      this.getSensorData("illuminance_mean");
      this.getSensorData("V1_mean");
    },
    getSensorData(type) {
      let param = {};
      param.type = type;
      param.hours = 1;
      this.$axios.post("/data/admin/data/getStatistic", param).then(res => {
        this.$set(this.sensorData, type, res.data.module);
      });
    },
    handleShowSensor(title, type) {
      this.sensorActive = type;
      this.sensorDialog.title = title;
      this.sensorDialog.max = this.sensorData[type].max;
      this.sensorDialog.min = this.sensorData[type].min;
      this.sensorDialog.avg = this.sensorData[type].avg;
      this.sensorDialog.last = this.sensorData[type].last;
      this.sensorDialog.visible = true;
      let param = {};
      param.type = type;
      param.hours = 1;
      this.$axios.post("/data/admin/data/getSeries", param).then(res => {
        this.$set(this.chartData, "rows", res.data.module);
      });
    },
    markClick() {
      this.showMap = false;
      let newData = JSON.parse(JSON.stringify(this.ringData));
    },
    getImageUrl(index) {
      return require("@/assets/img/jiance" + (index + 1) + ".jpg");
    }
  }
};
</script>
<style>
.i-image {
  height: 5.1vw;
  max-width: 6.8vw;
  margin-right: 1.3vw;
}
.image {
  max-width: 100%;
  max-height: 100%;
}
.sensor-info .el-form-item {
  margin-bottom: 5px;
}

.sensor-info .bottom {
  margin-top: 15px;
}
.el-card__header {
  padding: 0px;
}
.card_header_title {
  display: inline-block;
  font-size: 1.01vw;
  font-family: SourceHanSansCN;
  font-weight: bold;
  color: #fff;
  line-height: 2.81vw;
  background: linear-gradient(
    0deg,
    rgba(31, 119, 242, 1) 0%,
    rgba(47, 193, 229, 1) 100%
  );
  width: 6.93vw;
  height: 2.81vw;
  text-align:center;
}
.data_overview {
  margin-left: 1.19vw;
}

.data_overview .item {
  display: flex;
  align-items: center;
  width: 7.9vw;
  height: 5.4vw;
  background: linear-gradient(
    16deg,
    rgba(1, 177, 251, 1),
    rgba(65, 132, 255, 1)
  );
  border-radius: 4px;
  margin-top: 2.7vw;
}
.data_overview .item-left {
  margin-right: 3.28vw;
}

.data_overview .item img {
  max-width: 1.6vw;
  max-height: 1.7vw;
  margin-left: 1.3vw;
}
.data_overview .item .count {
  font-size: 3.14vh;
  font-family: UniDream;
  font-weight: normal;
  color: rgba(255, 255, 255, 1);
  margin-left: 0.36vw;
}

.data_overview .item .name {
  font-size: 1.11vh;
  font-family: Microsoft YaHei;
  font-weight: 400;
  color: rgba(243, 243, 243, 1);
  line-height: 24px;
  margin-left: 0.36vw;
}

.sensor_info {
  background: linear-gradient(
    0deg,
    rgba(213, 213, 213, 1) 0%,
    rgba(234, 234, 234, 1) 100%
  );
}

.sensor_info_check {
  background: linear-gradient(
    0deg,
    rgba(31, 118, 242, 1) 0%,
    rgba(48, 195, 229, 1) 100%
  );
}

/* .data_overview .item img {
  padding-right: 13px;
} */
.quota .el-form-item {
  margin-bottom: 0;
}

.dialog_head {
  height: 2.5vw;
  font-size: 1.09vw;
  font-weight: bold;
  color: rgba(51, 51, 51, 1);
}

.el-dialog__body {
  padding-top: 0;
  padding-left: 6.7vw;
  color: #606266;
  font-size: 14px;
  word-break: break-all;
}

.summary {
  margin-top: 1.3vw;
  padding-left: 62px;
  padding-right: 62px;
  display: flex;
}

.summary img {
  width: 2.7vw;
  height: 2.7vw;
}
.info .info_title {
  height: 0.8vw;
  line-height: 27px;
  font-size: 0.8vw;
  font-family: Microsoft YaHei;
  font-weight: bold;
  color: rgba(51, 51, 51, 1);
  display: flex;
  align-items: center;
}

.info span {
  font-size: 0.8vw;
}

.info label {
  font-size: 0.8vw;
}
</style>