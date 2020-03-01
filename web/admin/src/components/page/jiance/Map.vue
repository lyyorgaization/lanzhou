<template>
  <div class="container"
       style="padding:0">
    <div class="map">
      <baidu-map class="bmView"
                 :map-type="mapType"
                 :scroll-wheel-zoom="true"
                 :center="location"
                 :zoom="zoom"
                 ak="W3snqVzGLE554YKO8azARWwWpG5vKYqI">
        <bm-boundary name="甘肃省"
                     :strokeWeight="2"
                     strokeColor="blue"
                     :fillOpacity="0"></bm-boundary>
        <bm-marker :position="location"
                   animation="BMAP_ANIMATION_BOUNCE"
                   @click="markClick">
          <bm-info-window :show="show"
                          :height="220"
                          :width="338">
            <div style="text-align: center;">
              <div style="float:left;padding-left:24px;font-size:16px;font-weight:bold;color:rgba(51,51,51,1);">皋兰山监测站</div>
              <div style="float:right;padding-right:24px;font-size:12px;font-weight:bold;color:#666666;">地址:兰州市城关区</div>
              <img :src="glsUrl"
                   style="margin-left:24px;margin-right:24px;margin-top:10px;width:296px;height:135px;" />
              <el-button type="primary"
                         style="margin-top:14px" @click="markClick">进入监测中心</el-button>
            </div>
          </bm-info-window>
        </bm-marker>
      </baidu-map>
    </div>
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  data() {
    return {
      location: { lat: 35.78786946, lng: 103.98119978 },
      zoom: 15,
      addressKeyword: "",
      mapType: "BMAP_SATELLITE_MAP",
      glsUrl: require("../../../assets/img/gaolanshan.jpg"),
      show: true
    };
  },
  methods: {
    markClick() {
      this.$router.push('sensor')
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
  height: 100%;
}
</style>