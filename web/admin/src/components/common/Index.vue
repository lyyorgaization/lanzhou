<template>
    <div class="container">
        <div class="map" v-show="showMap">
            <el-radio v-model="mapType" label="BMAP_NORMAL_MAP">普通视图</el-radio>
            <el-radio v-model="mapType" label="BMAP_SATELLITE_MAP">卫星视图</el-radio>
            <el-radio v-model="mapType" label="BMAP_HYBRID_MAP">卫星路网混合图</el-radio>
            <baidu-map class="bmView" :map-type="mapType" :scroll-wheel-zoom="true" :center="location" :zoom="zoom"
                ak="W3snqVzGLE554YKO8azARWwWpG5vKYqI">
                <bm-boundary name="甘肃省" :strokeWeight="2" strokeColor="blue" :fillOpacity="0"></bm-boundary>
                <bm-marker :position="{lat: 35.78786946, lng: 103.98119978 }" :dragging="true"
                    animation="BMAP_ANIMATION_BOUNCE" @click="markClick">
                </bm-marker>

            </baidu-map>
        </div>
        <div style="width:100%" v-show="!showMap">
            <el-row>
                <el-col :span="12" style="padding:10px">
                    <el-card header="简介">
                        <div>
                            <h1>皋兰山地质环境监测站</h1>
                            <el-carousel :interval="4000" type="card" height="160px">
                                <el-carousel-item v-for="item in 6" :key="item">
                                    <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
                                        class="i-image">
                                </el-carousel-item>
                            </el-carousel>
                            <span style="font-size:9px">
                                皋兰山地质环境监测站由甘肃工程地质研究院牵头组织、兰州资源环境职业技术学院（甘肃应急管理学院）、兰州大学土木工程与力学学院协助建设。甘肃工程地质研究院依托产业资源优势，主要负责提供示范区的基础设施和场地空间，全面负责监测设备的完全运营，并协助其他两个机构的科研教学工作，最终保证集科研、示范、培训和教学一体的地质灾害监测预警示范区能够长期稳定运行。兰州资源环境职业技术学院地质学系依托高职实习实训平台，提供能充分用于学生在地质灾害领域进行实地实践的教学设备和部分观测仪器，维护教学设备的正常，保证教学活动的长期执行；兰州大学土木工程与力学学院利用在地质灾害研究中的长期科研积累和平台化建设，主要负责滑坡与泥石流等地质灾害监测预警系统的仪器开发和整体设计，从科学研究角度布设和维护监测设备，并用于地质工程类等相关专业的专业实践；三家合作单位本着“优势互补、劣势互消、多方共赢”的原则，通过“科研驱动-项目支撑-典型示范-应用推广”的方式和途径，将监测站打造成为黄土高原区地质灾害监测预警领域科研、示范、实训基地，促进教育链、人才链与产业链、创新链的有机衔接，推动“产学研用”的实现。
                            </span>
                        </div>
                    </el-card>
                </el-col>
                <el-col :span="12" style="padding:10px">
                    <el-card header="数据总览">
                        <img src="../../assets/img/statistic.png" style="height：412px" />
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
            <el-dialog :title="sensorDialog.title" :visible.sync="sensorDialog.visible" width="80%">
                <el-form :inline="true" label-width="70px">
                    <el-form-item label="最大值">{{sensorDialog.max}}</el-form-item>
                    <el-form-item label="最小值">{{sensorDialog.min}}</el-form-item>
                    <el-form-item label="平均值">{{sensorDialog.avg}}</el-form-item>
                    <el-form-item label="当前值">{{sensorDialog.last}}</el-form-item>
                </el-form>
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
            ]
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
        markClick(){
            this.showMap = false;
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
    max-height: 150px;
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
</style>