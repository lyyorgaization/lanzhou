<template>
  <iframe :src="wigetUrl"
          style="width:100%;height:100%"
          frameborder="0"></iframe>
</template>
<script>
export default {
  data() {
    return {
      wigetUrl: ""
    };
  },
  created() {
    this.getqixiang();
  },
  methods: {
    goSensor() {
      this.$router.push("/sensor");
    },
    getqixiang() {
      this.$axios
        .get(
          "http://openapi.ecois.info/v3/token?appid=o2oBPCyjJZ$_d55u&secret=P4q9eTfmaLGmDuIW_o*am1SOv8a6n6Np"
        )
        .then(res => {
          let token = res.data.token;
          this.$axios
            .get("http://openapi.ecois.info/v3/devices?page=1&limit=100", {
              headers: {
                Authorization: token
              }
            })
            .then(res1 => {
              console.log(res1);
              res1.data.list.forEach(item => {
                if (item.sn == this.$route.query.sn) {
                  this.wigetUrl = item.widget.externalAccessUrl;
                }
              });
            });
        });
    }
  }
};
</script>