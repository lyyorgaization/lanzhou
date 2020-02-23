<template>
    <div class="container">
        <div class="map" v-if="showMap">
            <el-radio v-model="mapType" label="BMAP_NORMAL_MAP">普通视图</el-radio>
            <el-radio v-model="mapType" label="BMAP_SATELLITE_MAP">卫星视图</el-radio>
            <el-radio v-model="mapType" label="BMAP_HYBRID_MAP">卫星路网混合图</el-radio>
            <baidu-map class="bmView" :map-type="mapType" :scroll-wheel-zoom="true" :center="location" :zoom="zoom"
                ak="W3snqVzGLE554YKO8azARWwWpG5vKYqI">
                <bm-boundary name="甘肃省" :strokeWeight="2" strokeColor="blue" :fillOpacity="0"></bm-boundary>
                <bm-marker :position="{lat: 35.78786946, lng: 103.98119978 }" animation="BMAP_ANIMATION_BOUNCE"
                    @click="markClick">
                </bm-marker>
            </baidu-map>
        </div>
        <div style="width:100%" v-if="!showMap">
            <el-row>
                <el-col :span="12" style="padding-right:5px">
                    <el-card body-style="height:493px">
                        <div slot="header" style="display:flex">
                            <div class="card_header_pre">&nbsp;
                            </div>
                            <div class="card_header_title">
                                简介</div>
                        </div>
                        <h1
                            style="font-size:30px;font-family:SourceHanSansCN;font-weight:bold;color:rgba(24,24,24,1);line-height:53px;">
                            皋兰山地质环境监测站</h1>
                        <el-carousel :interval="4000" type="card" height="160px">
                            <el-carousel-item v-for="(item,index) in 3" :key="item">
                                <img :src="getImageUrl(index)" class="i-image">
                            </el-carousel-item>
                        </el-carousel>
                        <p style="font-size:9px;">
                            皋兰山地质环境监测站由甘肃工程地质研究院牵头组织、兰州资源环境职业技术学院（甘肃应急管理学院）、兰州大学土木工程与力学学院协助建设。甘肃工程地质研究院依托产业资源优势，主要负责提供示范区的基础设施和场地空间，全面负责监测设备的完全运营，并协助其他两个机构的科研教学工作，最终保证集科研、示范、培训和教学一体的地质灾害监测预警示范区能够长期稳定运行。兰州资源环境职业技术学院地质学系依托高职实习实训平台，提供能充分用于学生在地质灾害领域进行实地实践的教学设备和部分观测仪器，维护教学设备的正常，保证教学活动的长期执行；兰州大学土木工程与力学学院利用在地质灾害研究中的长期科研积累和平台化建设，主要负责滑坡与泥石流等地质灾害监测预警系统的仪器开发和整体设计，从科学研究角度布设和维护监测设备，并用于地质工程类等相关专业的专业实践；三家合作单位本着“优势互补、劣势互消、多方共赢”的原则，通过“科研驱动-项目支撑-典型示范-应用推广”的方式和途径，将监测站打造成为黄土高原区地质灾害监测预警领域科研、示范、实训基地，促进教育链、人才链与产业链、创新链的有机衔接，推动“产学研用”的实现。
                        </p>
                        <el-button style="background-color:#009DEC;color:white;margin-top:36px">查看详情</el-button>
                    </el-card>
                </el-col>
                <el-col :span="12" style="padding-left:5px">
                    <el-card body-style="height:493px">
                        <div slot="header" style="display:flex">
                            <div class="card_header_pre">&nbsp;
                            </div>
                            <div class="card_header_title">
                                数据总览</div>
                        </div>
                        <el-row style="padding:20px;widht:50%" type="flex" justify="space-between"
                            class="data_overview">
                            <el-col :span="12">
                                <el-row>
                                    <el-col :span="12" class="item">
                                        <img style="width: 53px; height: 53px"
                                            src="../../assets/img/icon/jiancezhan.jpg" />
                                        <div style="display:inline-block">
                                            <span class="count">1</span>
                                            <br />
                                            <span class="name">监测站</span>
                                        </div>
                                    </el-col>
                                    <el-col :span="12" class="item">
                                        <img style="width: 53px; height: 53px" src="../../assets/img/icon/shebei.jpg" />
                                        <div style="display:inline-block">
                                            <span class="count">17</span>
                                            <br />
                                            <span class="name">设备数</span>
                                        </div>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12" class="item">
                                        <img style="width: 53px; height: 53px" src="../../assets/img/icon/days.jpg" />
                                        <div style="display:inline-block">
                                            <span class="count">2240</span>
                                            <br />
                                            <span class="name">已采集天数</span>
                                        </div>
                                    </el-col>
                                    <el-col :span="12" class="item">
                                        <img style="width: 53px; height: 53px"
                                            src="../../assets/img/icon/data_count.jpg" />
                                        <div style="display:inline-block">
                                            <span class="count">1427</span>
                                            <br />
                                            <span class="name">已收集数据</span>
                                        </div>
                                    </el-col>
                                </el-row>
                                <el-row>
                                    <el-col :span="12" class="item">
                                        <img style="width: 53px; height: 53px"
                                            src="../../assets/img/icon/zhengchang.jpg" />
                                        <div style="display:inline-block">
                                            <span class="count">127</span>
                                            <br />
                                            <span class="name">已正常监测</span>
                                        </div>
                                    </el-col>
                                    <el-col :span="12" class="item">
                                        <img style="width: 53px; height: 53px"
                                            src="../../assets/img/icon/jiancezhan.jpg" />
                                        <div style="display:inline-block">
                                            <span class="count" style="color:#FE3600">1</span>
                                            <br />
                                            <span class="name">已预警</span>
                                        </div>
                                    </el-col>
                                </el-row>
                            </el-col>
                            <el-col :span="12">
                                <div style="height:50%">
                                    <ve-ring :data="ringData" :settings="ringSetting" legend-position="bottom"
                                        :title="{text:'设备传感器占比'}" height="201px"></ve-ring>
                                </div>
                                <div style="height:50%">
                                    <ve-line :xAixs="{boundaryGap :false}" height="291px" :data="lineData"
                                        :settings="lineSetting" :title="{text:'布置设备数'}"></ve-line>
                                </div>
                            </el-col>
                        </el-row>
                        <!-- <img src="../../assets/img/statistic.png" style="height：412px" /> -->
                    </el-card>
                </el-col>
            </el-row>
            <el-card header="设备基本信息" style="margin:10px" class="sensor-info">
                <el-row>
                    <el-col :span="4" style="padding:10px">
                        <a style="cursor:hand" @click="handleShowSensor('空气温度传感器','t1')">
                            <el-card :body-style="{ padding: '0px' }">
                                <img src="../../assets/img/sensor/air_temperature.png" class="image">
                                <div style="padding: 14px;">
                                    <span>空气温度传感器</span>
                                    <div class="bottom clearfix">
                                        <el-form label-position="left" label-width="80px">
                                            <el-form-item label="当前状态"><span>正常</span></el-form-item>
                                            <el-form-item label="当前值"><span>{{sensorData.t1.last}}</span></el-form-item>

                                        </el-form>
                                    </div>
                                </div>
                            </el-card>
                        </a>
                    </el-col>
                    <el-col :span="4" style="padding:10px">
                        <a style="cursor:hand" @click="handleShowSensor('土壤温湿度传感器','humidity1_mean')">
                            <el-card :body-style="{ padding: '0px' }">
                                <img src="../../assets/img/sensor/soil_moisture.png" class="image">
                                <div style="padding: 14px;">
                                    <span>土壤温湿度传感器</span>
                                    <div class="bottom clearfix">
                                        <el-form label-position="left" label-width="80px">
                                            <el-form-item label="当前状态"><span>正常</span></el-form-item>
                                            <el-form-item label="当前值"><span>{{sensorData.humidity1_mean.last}}</span>
                                            </el-form-item>
                                        </el-form>
                                    </div>
                                </div>
                            </el-card>
                        </a>
                    </el-col>
                    <el-col :span="4" style="padding:10px">
                        <a style="cursor:hand" @click="handleShowSensor('孔隙压传感器','pressure1_mean')">
                            <el-card :body-style="{ padding: '0px' }">
                                <img src="../../assets/img/sensor/pore_pressure.png" class="image">
                                <div style="padding: 14px;">
                                    <span>孔隙压传感器</span>
                                    <div class="bottom clearfix">
                                        <el-form label-position="left" label-width="80px">
                                            <el-form-item label="当前状态"><span>正常</span></el-form-item>
                                            <el-form-item label="当前值"><span>{{sensorData.pressure1_mean.last}}</span>
                                            </el-form-item>
                                        </el-form>
                                    </div>
                                </div>
                            </el-card>
                        </a>
                    </el-col>
                    <el-col :span="4" style="padding:10px">
                        <a style="cursor:hand" @click="handleShowSensor('雨量计','rainfall_mean')">
                            <el-card :body-style="{ padding: '0px' }">
                                <img src="../../assets/img/sensor/rain.png" class="image">
                                <div style="padding: 14px;">
                                    <span>雨量计</span>
                                    <div class="bottom clearfix">
                                        <el-form label-position="left" label-width="80px">
                                            <el-form-item label="当前状态"><span>正常</span></el-form-item>
                                            <el-form-item label="当前值"><span>{{sensorData.rainfall_mean.last}}</span>
                                            </el-form-item>
                                        </el-form>
                                    </div>
                                </div>
                            </el-card>
                        </a>
                    </el-col>
                    <el-col :span="4" style="padding:10px">
                        <a style="cursor:hand" @click="handleShowSensor('微震监测仪','illuminance_mean')">
                            <el-card :body-style="{ padding: '0px' }">
                                <img src="../../assets/img/sensor/microseism.png" class="image">
                                <div style="padding: 14px;">
                                    <span>微震监测仪</span>
                                    <div class="bottom clearfix">
                                        <el-form label-position="left" label-width="80px">
                                            <el-form-item label="当前状态"><span>正常</span></el-form-item>
                                            <el-form-item label="当前值"><span>{{sensorData.illuminance_mean.last}}</span>
                                            </el-form-item>
                                        </el-form>
                                    </div>
                                </div>
                            </el-card>
                        </a>
                    </el-col>
                    <el-col :span="4" style="padding:10px">
                        <a style="cursor:hand" @click="handleShowSensor('不极化化电极','V1_mean')">
                            <el-card :body-style="{ padding: '0px' }">
                                <div style="padding: 14px;">
                                    <span>不极化化电极</span>
                                    <div class="bottom clearfix">
                                        <el-form label-position="left" label-width="80px">
                                            <el-form-item label="当前状态"><span>正常</span></el-form-item>
                                            <el-form-item label="当前值"><span>{{sensorData.V1_mean.last}}</span>
                                            </el-form-item>
                                        </el-form>
                                    </div>
                                </div>
                            </el-card>
                        </a>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="4" style="padding:10px">
                        <el-card :body-style="{ padding: '0px' }">
                            <img src="../../assets/img/sensor/GNSS.png" class="image">
                            <div style="padding: 14px;">
                                <span>GNSS位移监测仪</span>
                                <div class="bottom clearfix">
                                    <el-form label-position="left" label-width="80px">
                                        <el-form-item label="当前状态"><span>正常</span></el-form-item>
                                        <el-form-item label="当前值"><span>6</span></el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="4" style="padding:10px">
                        <el-card :body-style="{ padding: '0px' }">
                            <img src="../../assets/img/sensor/line.png" class="image">
                            <div style="padding: 14px;">
                                <span>拉线式位移计</span>
                                <div class="bottom clearfix">
                                    <el-form label-position="left" label-width="80px">
                                        <el-form-item label="当前状态"><span>正常</span></el-form-item>
                                        <el-form-item label="当前值"><span>6</span></el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="4" style="padding:10px">
                        <el-card :body-style="{ padding: '0px' }">
                            <img src="../../assets/img/sensor/air_pressure.png" class="image">
                            <div style="padding: 14px;">
                                <span>大气压传感器</span>
                                <div class="bottom clearfix">
                                    <el-form label-position="left" label-width="80px">
                                        <el-form-item label="当前状态"><span>正常</span></el-form-item>
                                        <el-form-item label="当前值"><span>6</span></el-form-item>
                                    </el-form>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </el-card>
            <el-dialog :title="sensorDialog.title" :visible.sync="sensorDialog.visible" width="90%">
                <el-row style="padding-left:62px;padding-right:62px;display:flex">
                    <el-col :span="6" style="display: flex;    align-items: center;">
                        <img style="width: 53px; height: 53px" src="../../assets/img/icon/max.jpg" />
                        <div
                            style="padding-left:15px;font-size:16px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(140,140,140,1)">
                            最小<br />
                            <span
                                style="font-size:18px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1)">{{sensorDialog.max}}</span>
                        </div>
                    </el-col>
                    <el-col :span="6" style="display: flex;    align-items: center;"><img
                            style="width: 53px; height: 53px" src="../../assets/img/icon/min.jpg" />
                        <div
                            style="padding-left:15px;font-size:16px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(140,140,140,1)">
                            最小<br />
                            <span
                                style="font-size:18px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1)">{{sensorDialog.min}}</span>
                        </div>
                    </el-col>
                    <el-col :span="6" style="display: flex;    align-items: center;"><img
                            style="width: 53px; height: 53px" src="../../assets/img/icon/avg.jpg" />
                        <div
                            style="padding-left:15px;font-size:16px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(140,140,140,1)">
                            最小<br />
                            <span
                                style="font-size:18px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1)">{{sensorDialog.avg}}</span>
                        </div>
                    </el-col>
                    <el-col :span="6" style="display: flex;    align-items: center;"><img
                            style="width: 53px; height: 53px" src="../../assets/img/icon/ct.jpg" /><div
                            style="padding-left:15px;font-size:16px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(140,140,140,1)">
                            最小<br />
                            <span
                                style="font-size:18px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1)">{{sensorDialog.last}}</span>
                        </div>
                    </el-col>
                </el-row>
                <el-row style="margin-top:20px">
                    <el-col :span="6">
                        <el-image style="width: 307px; height: 331px"
                            :src="require('../../assets/img/sensor/pore_pressure.png')"></el-image>
                    </el-col>
                    <el-col :span="18" style="padding-left:37px">
                        <div
                            style="font-size:18px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1);">
                            功能</div>
                        <p
                            style="margin-top:13px;margin-bottom:13px;font-size:14px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(140,140,140,1);">
                            使用高精度、高灵敏度和高可靠性MEMS孔隙水压力传感器，监控孔隙压力压力的变化；支持各种接口的数据采集仪；先进的技术，更高的精度， 实现了孔隙压监测自动化、简单化。
                        </p>
                        <div
                            style="font-size:18px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1);">
                            指标</div>
                        <el-row style="margin-top:13px" class="quota">
                            <el-col :span="12">
                                <el-card body-style="padding-top:0;padding-bottom:0">
                                    <div slot="header">
                                        <div
                                            style="padding-left:13px;height:27px;line-height:27px;background-color:#C0C2C8;font-size:16px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1);">
                                            物理特性</div>
                                    </div>
                                    <el-form label-width="100px" label-position="left">
                                        <el-form-item label="尺寸"><span>自定义</span></el-form-item>
                                        <el-form-item label="工作温度"><span>-20℃ ~ +70℃</span></el-form-item>
                                        <el-form-item label="防护等级"><span>IP67</span></el-form-item>
                                    </el-form>
                                </el-card>
                            </el-col>
                            <el-col :span="12">
                                <el-card body-style="padding-top:0;padding-bottom:0">
                                    <div slot="header">
                                        <div
                                            style="padding-left:13px;height:27px;line-height:27px;background-color:#C0C2C8;font-size:16px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1);">
                                            量程</div>
                                    </div>
                                    <el-form label-width="100px" label-position="left">
                                        <el-form-item label="精度"><span>0.25%</span></el-form-item>
                                        <el-form-item label="量程"><span>0~15kPa~200kPa</span></el-form-item>
                                        <el-form-item label="接口"><span>模拟、RS485/232</span></el-form-item>
                                    </el-form>
                                </el-card>
                            </el-col>
                        </el-row>
                        <el-row class="quota">
                            <el-col :span="12">
                                <el-card body-style="padding-top:0;padding-bottom:0">
                                    <div slot="header">
                                        <div
                                            style="padding-left:13px;height:27px;line-height:27px;background-color:#C0C2C8;font-size:16px;font-family:Microsoft YaHei;font-weight:bold;color:rgba(51,51,51,1);">
                                            电气指示</div>
                                    </div>
                                    <el-form label-width="100px" label-position="left">
                                        <el-form-item label="电压"><span>8-24V</span></el-form-item>
                                        <el-form-item label="功耗"><span>0.75W</span></el-form-item>
                                    </el-form>
                                </el-card>
                            </el-col>
                        </el-row>
                    </el-col>
                </el-row>
                <ve-line :data="chartData" :settings="chartSettings" :dataZoom="dataZoom"
                    :not-set-unchange="['dataZoom']"></ve-line>
            </el-dialog>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            showMap: true,
            location: { lat: 35.78786946, lng: 103.98119978 },
            zoom: 7,
            addressKeyword: "",
            mapType: "BMAP_NORMAL_MAP",
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
                radius: [30, 60],
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
            this.$axios
                .post("/data/admin/data/getStatistic", param)
                .then(res => {
                    this.$set(this.sensorData, type, res.data.module);
                });
        },
        handleShowSensor(title, type) {
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
.map {
    width: 100%;
    height: 750px;
}
.bmView {
    width: 100%;
    height: 750px;
}
.i-image {
    max-height: 136px;
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
.card_header_pre {
    width: 4px;
    height: 54px;
    background-color: #009ee8;
    display: inline-block;
}
.card_header_title {
    margin-left: 27px;
    display: inline-block;
    font-size: 20px;
    font-family: SourceHanSansCN;
    font-weight: bold;
    color: rgba(51, 51, 51, 1);
    line-height: 54px;
}
.data_overview .item {
    display: flex;
    align-items: center;
    margin-bottom: 103px;
}
.data_overview .item .count {
    font-size: 28px;
    font-family: SourceHanSansCN;
    font-weight: bold;
    color: rgba(51, 51, 51, 1);
}

.data_overview .item .name {
    font-size: 14px;
    font-family: SourceHanSansCN;
    font-weight: bold;
    color: rgba(102, 102, 102, 1);
}

.data_overview .item img {
    padding-right: 13px;
}
.quota .el-form-item {
    margin-bottom: 0;
}
</style>