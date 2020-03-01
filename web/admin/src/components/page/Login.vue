<template>
  <div class="login-wrap">
    <div class="ms-title">
      <img :src="logoUrl" />数据监测中心大数据平台
    </div>
    <div class="ms-login">
      <el-tabs v-model="activeName" class="login-tab">
        <el-tab-pane label="用户名登陆" name="first">
          <el-form
            :model="ruleForm"
            :rules="rules"
            ref="ruleForm"
            label-width="0px"
            class="ms-content"
          >
            <el-form-item prop="username">
              <el-input v-model="ruleForm.username" placeholder="username">
                <el-button slot="prepend" icon="el-icon-lx-people"></el-button>
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                type="password"
                placeholder="password"
                v-model="ruleForm.password"
                @keyup.enter.native="submitForm('ruleForm')"
              >
                <el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
              </el-input>
            </el-form-item>
            <el-form-item prop="username">
              <div style="float:left">
                <el-checkbox v-model="remember">
                  <span style="color:white">记住用户名</span>
                </el-checkbox>
              </div>
              <div style="float:right">
                <el-link target="_blank">
                  <span style="color:white">忘记密码?</span>
                </el-link>
              </div>
            </el-form-item>
            <div class="login-btn">
              <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            </div>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      ruleForm: {
        username: "admin",
        password: ""
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
      userItem: {},
      logoUrl: require("../../assets/img/logo.png"),
      activeName: "first",
      remember: true
    };
  },
  methods: {
    submitForm(formName) {
      this.$axios
        .post("/omp/login", {
          username: this.ruleForm.username,
          password: this.ruleForm.password
        })
        .then(res => {
          if (res.data.code === 0) {
            localStorage.setItem("ms_username", this.ruleForm.username);
            localStorage.setItem("userInfo", JSON.stringify(res.data.module));
            this.$axios.post("/omp/user/getMenuTree", {}).then(res => {
              sessionStorage.setItem("menu", JSON.stringify(res.data.module));
            });
            this.$router.push("/map");
          }
        });
    }
  }
};
</script>

<style>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url(../../assets/img/login-bg.jpg);
  background-size: 100%;
}
.ms-title {
  font-size: 2.6vh;
  font-family: SourceHanSansCN;
  font-weight: 400;
  color: rgba(255, 255, 255, 1);
  display: flex;
  padding-left: 4vw;
  padding-top: 5vh;
}
.ms-title img {
  margin-right: 0.7vw;
}
.ms-login {
  width: 25vw;
  height: 46vh;
  background: rgba(0, 0, 0, 1);
  box-shadow: 0px 3px 250px 0px rgba(4, 0, 0, 0.29);
  opacity: 0.5;
  border-radius: 2px;
  margin-top: 17vh;
  margin-left: 62vw;
  padding-top: 1.8vh;
}
.el-tabs__header {
  padding-left: 3.2vw;
  padding-right: 3.2vw;
  position: relative;
  margin: 0 0 15px;
  font-size: 1.8vh;
  color: #fff;
}

.el-tabs__item.is-active {
  color: white;
}

.el-tabs__active-bar {
  background-color: #fff;
}

.ms-content {
  padding: 30px 30px;
}
.login-btn {
  text-align: center;
}
.login-btn button {
  width: 100%;
  height: 36px;
  margin-bottom: 10px;
}
.login-tips {
  font-size: 12px;
  line-height: 30px;
  color: #fff;
}
</style>
