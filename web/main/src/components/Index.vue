<template>
  <el-container>
    <el-header height="9.26vh">
      <img :src="logoUrl"
           style="margin-left:18.7vw" />
      <span class="title">数据监测中心大数据平台</span>
      <el-button class="menu"
                 type="text"
                 style="margin-left:13.75vw" @click="openURL('/admin/map')">地图</el-button>
      <el-button class="menu"
                 type="text"
                 style="margin-left:2.81vw" @click="openURL('/admin/sensor')">数据</el-button>
      <el-button class="menu"
                 type="text"
                 style="margin-left:2.81vw" @click="openURL('/screen/index')">大屏</el-button>
      <el-button class="login-button"
                 type="text">登录</el-button>
      <el-button class="register-button"
                 type="text">注册</el-button>
    </el-header>
    <el-main>
      <div class="swiper-container">
        <div class="swiper-wrapper">
          <div class="swiper-slide">
            <el-carousel indicator-position="outside"
                         class="banner"
                         height="83.9vh" :autoplay="true">
              <el-carousel-item v-for="item in imageList"
                                :key="item">
                <div class="banner"
                     :style="{backgroundImage: 'url(' + item + ')'}">
                  <div>
                    <h1>地理数据大屏可视化</h1>
                    <p>更直观更智能查看实时数据，现场资料，助力智能高效的预警系统</p>
                  </div>
                </div>
                <div></div>
              </el-carousel-item>
            </el-carousel>
          </div>
          <div class="swiper-slide introduction"
               :style="{backgroundImage: 'url(' + introdctionUrl + ')'}">
            <p>
              地质数据检测中心及大数据平台是由甘肃省兰州市地质研究院组建和设计，通过专业地质灾害监测设备和物联网及通信技术收集，
              存储，和处理数据，结合大数据技术，实现对技术灾害监测，分析，处理，
              ，预警和应急化处理服务等一体的信息化，可视化的综合服务平台。

            </p>
          </div>
          <div class="swiper-slide systemFrame"
               :style="{backgroundImage: 'url(' + systemFrameUrl + ')'}">
            <p>
              地质数据检测中心及大数据平台采用全新的分布式架构进行数据处理，分布式存储，缓存，计算，拥有卓越的系统性能，多套系统之间具备良好的信息交互、共享和操控能力，为地质信息预警提供完美的解决方案。
            </p>
          </div>
          <div class="swiper-slide application"
               :style="{backgroundImage: 'url(' + applicationUrl + ')'}">
            <img v-for="image in applicationImageList"
                 :key="image"
                 :src="image"
                 style="height:46vh" />
          </div>
        </div>
        <div class="swiper-pagination"></div>
      </div>
    </el-main>
  </el-container>
</template>
<script>
import Swiper from "swiper";
import "swiper/css/swiper.css";
export default {
  name: "Index",
  data() {
    return {
      imageList: [
        require("../assets/01_01.jpg"),
        require("../assets/02_01.png"),
        require("../assets/03_01.png")
      ],
      logoUrl: require("../assets/logo.jpg"),
      introdctionUrl: require("../assets/introdction.png"),
      systemFrameUrl: require("../assets/system_frame.png"),
      applicationUrl: require("../assets/application.png"),
      applicationImageList: [
        require("../assets/application_1.png"),
        require("../assets/application_2.png"),
        require("../assets/application_3.png"),
        require("../assets/application_4.png")
      ]
    };
  },
  mounted() {
    new Swiper(".swiper-container", {
      //   loop: true,
      // 如果需要分页器
      pagination: {
        el: ".swiper-pagination"
      },
      // 如果需要前进后退按钮
      // nextButton: '.swiper-button-next',
      // prevButton: '.swiper-button-prev',
      // 如果需要滚动条
      //   scrollbar: ".swiper-scrollbar",
      direction: "vertical",
      mousewheel: true
    });
  },
  methods:{
      openURL(url){
          window.open(url,'_blank') 
      }
  }
};
</script>
<style>
.el-header {
  padding: 0;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  -ms-flex-negative: 0;
  flex-shrink: 0;
  display: flex;
  align-items: center;
}
.el-header .title {
  margin-left: 0.3125vw;
  font-size: 2.68vh;
  font-weight: 400;
  line-height: 9.26vh;
  font-family: Microsoft YaHei;
  font-weight: bold;
}
.el-header .menu {
  font-size: 1.85vh;
  font-family: Microsoft YaHei;
  font-weight: bold;
  color: #333333;
  line-height: 9.26vh;
  padding: 0;
  width: 5.79vw;
  border-radius: 0;
}
.el-header .menu:hover {
  border-bottom: 0.37vh solid #0045ad;
}

.el-header .login-button {
  font-size: 1.66vh;
  font-family: Microsoft YaHei;
  font-weight: bold;
  color: #003c96;
  margin-left: 2.86vw;
}

.el-header .register-button {
  font-size: 1.66vh;
  font-family: Microsoft YaHei;
  font-weight: bold;
  color: #0045ad;
  margin-left: 2.86vw;
  width: 3.38vw;
  height: 3.61vh;
  border: 1px solid #0045ad;
  border-radius: 6px;
  line-height: 0;
}

.el-container .el-main {
  display: block;
  -webkit-box-flex: 1;
  -ms-flex: 1;
  flex: 1;
  -ms-flex-preferred-size: auto;
  flex-basis: auto;
  overflow: auto;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  padding: 0px;
}

.swiper-container {
  width: 100%;
  height: 88vh;
  margin: 0;
}

.banner {
  background-repeat: no-repeat;
  background-position-x: center;
  height: 100%;
}

.banner h1 {
  font-family: SourceHanSansCN;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  margin-left: 19.63vw;
  padding-top: 15.27vh;
  margin-top: 0;
}
.banner p {
  font-size: 18px;
  font-family: SourceHanSansCN;
  font-weight: 400;
  color: rgba(255, 255, 255, 1);
  line-height: 30px;
  opacity: 0.8;
  padding-left: 19.63vw;
}

.introduction {
  background-repeat: no-repeat;
  background-position-x: center;
  height: 83vh;
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
}

.introduction p {
  font-size: 2.2vh;
  font-family: Microsoft YaHei;
  font-weight: bold;
  color: rgba(51, 51, 51, 1);
  margin-top: 29.44vh;
  margin-left: 19vw;
  width: 16.5vw;
  line-height: 4.44vh;
}

.systemFrame {
  background-repeat: no-repeat;
  background-position-x: left;
  height: 83vh;
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
}

.systemFrame p {
  font-size: 2.2vh;
  font-family: Microsoft YaHei;
  font-weight: bold;
  color: rgba(51, 51, 51, 1);
  margin-top: 29.44vh;
  margin-left: 64.4vw;
  width: 16.5vw;
  line-height: 4.44vh;
  text-indent: 2em;
}

.application {
  background-repeat: no-repeat;
  height: 83vh;
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>