<template>
  <div class="header">
    <!-- 折叠按钮 -->
    <!-- <div class="collapse-btn" @click="collapseChage">
      <i class="el-icon-menu"></i>
    </div>-->
    <div class="logo">
      <img :src="logoUrl"
           style="margin-right:0.6vw" />
      <span style="  line-height: 6.2vh;">数据监测中心大数据平台</span>
    </div>
    <div class="header-right">
      <div class="header-user-con">
        <div>
          <el-input placeholder="请输入搜索内容"
                    v-model="searchData"
                    class="input-with-select">
            <el-button slot="append"
                       icon="el-icon-search"></el-button>
          </el-input>
          <!-- 全屏显示
        <div class="btn-fullscreen" @click="handleFullScreen">
          <el-tooltip effect="dark" :content="fullscreen?`取消全屏`:`全屏`" placement="bottom">
            <i class="el-icon-rank"></i>
          </el-tooltip>-->
        </div>
        <!-- 消息中心 -->
        <!-- <div class="btn-bell">
                    <el-tooltip effect="dark" :content="message?`有${message}条未读消息`:`消息中心`" placement="bottom">
                        <router-link to="/tabs">
                            <i class="el-icon-bell"></i>
                        </router-link>
                    </el-tooltip>
                    <span class="btn-bell-badge" v-if="message"></span>
        </div>-->
        <!-- 用户头像 -->
        <!-- <div class="user-avator"><img src="../../assets/img/img.jpg"></div> -->
        <!-- 用户名下拉菜单 -->
        <el-dropdown class="user-name"
                     trigger="click"
                     @command="handleCommand">
          <span class="el-dropdown-link">
            {{username}}
            <i class="el-icon-caret-bottom"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <el-button type="text"
                         @click="changePasswordVisible = true">修改密码</el-button>
            </el-dropdown-item>
            <el-dropdown-item divided
                              command="loginout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <el-dialog title="修改密码"
                   :visible.sync="changePasswordVisible"
                   width="40%"
                   label-width="80px">
          <el-form :model="form"
                   :rules="rules">
            <el-form-item ref="form"
                          label-width="80px"
                          label="旧密码">
              <el-input placeholder="请输入旧密码"
                        type="password"
                        v-model="form.oldPassword" />
            </el-form-item>
            <el-form-item ref="form"
                          label-width="80px"
                          prop="newPassword"
                          label="新密码">
              <el-input placeholder="请输入新密码"
                        type="password"
                        v-model="form.newPassword" />
            </el-form-item>
            <el-form-item ref="form"
                          label-width="80px"
                          label="确认密码"
                          prop="confirmPassword">
              <el-input placeholder="请再次输入新密码"
                        type="password"
                        v-model="form.confirmPassword" />
            </el-form-item>
          </el-form>
          <span slot="footer"
                class="dialog-footer">
            <el-button type="primary"
                       @click="savePassword()">保存</el-button>
            <el-button @click="changePasswordVisible=false">取消</el-button>
          </span>
        </el-dialog>
      </div>
    </div>
  </div>
</template>
<script>
import bus from "../common/bus";

export default {
  data() {
    var checkNewPassword = (rule, value, callback) => {
      if (value === "" || value === undefined) {
        callback(new Error("请输入密码"));
      } else {
        let regx = /[-\da-zA-Z`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]*((\d+[a-zA-Z]+[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+)|(\d+[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+[a-zA-Z]+)|([a-zA-Z]+\d+[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+)|([a-zA-Z]+[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+\d+)|([-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+\d+[a-zA-Z]+)|([-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]+[a-zA-Z]+\d+))[-\da-zA-Z`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]*/;
        if (!regx.test(value)) {
          callback(new Error("密码必须包含字母数字及特殊字符"));
        } else {
          callback();
        }
      }
    };
    var checkConfirmPassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.form.newPassword) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      collapse: false,
      fullscreen: false,
      name: "",
      message: 2,
      changePasswordVisible: false,
      form: {},
      rules: {
        newPassword: [{ validator: checkNewPassword, trigger: "blur" }],
        confirmPassword: [{ validator: checkConfirmPassword, trigger: "blur" }]
      },
      logoUrl: require("../../assets/img/logo.png"),
      searchData: ""
    };
  },
  computed: {
    username() {
      let username = localStorage.getItem("ms_username");
      return username ? username : this.name;
    }
  },
  methods: {
    // 用户名下拉菜单选择事件
    handleCommand(command) {
      if (command == "loginout") {
        this.$axios.post("/omp/loginout", {});
        localStorage.removeItem("ms_username");
        sessionStorage.removeItem("menu");
        this.$router.push("/login");
      }
    },
    // 侧边栏折叠
    collapseChage() {
      this.collapse = !this.collapse;
      bus.$emit("collapse", this.collapse);
    },
    // 全屏事件
    handleFullScreen() {
      let element = document.documentElement;
      if (this.fullscreen) {
        if (document.exitFullscreen) {
          document.exitFullscreen();
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen();
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen();
        } else if (document.msExitFullscreen) {
          document.msExitFullscreen();
        }
      } else {
        if (element.requestFullscreen) {
          element.requestFullscreen();
        } else if (element.webkitRequestFullScreen) {
          element.webkitRequestFullScreen();
        } else if (element.mozRequestFullScreen) {
          element.mozRequestFullScreen();
        } else if (element.msRequestFullscreen) {
          // IE11
          element.msRequestFullscreen();
        }
      }
      this.fullscreen = !this.fullscreen;
    },
    savePassword() {
      this.$axios
        .post("/omp/changePassword", {
          password: this.form.newPassword
        })
        .then(res => {
          this.$message({
            type: "success",
            message: "修改成功!"
          });
        });
    }
  },
  mounted() {
    if (document.body.clientWidth < 1500) {
      this.collapseChage();
    }
    let userInfo = JSON.parse(localStorage.getItem("userInfo"));
    if (userInfo != undefined && userInfo.defaultPassword === 1) {
      this.$message({
        type: "error",
        message: "请修改默认密码!"
      });
      this.changePasswordVisible = true;
    }
  }
};
</script>
<style>
.header {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 6.2vh;
  font-size: 2.2vh;
  color: #fff;
  background: linear-gradient(
    0deg,
    rgba(31, 118, 242, 1) 0%,
    rgba(37, 158, 213, 1) 100%
  );
}
.collapse-btn {
  float: left;
  padding: 0 21px;
  cursor: pointer;
  line-height: 70px;
}
.header .logo {
  float: left;
  width: 250px;
  display: flex;
  align-items: center;
  margin-left: 1.5vw;
}
.header-right {
  float: right;
  padding-right: 50px;
}
.header-user-con {
  display: flex;
  height: 6.2vh;
  align-items: center;
}
.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
}
.btn-bell,
.btn-fullscreen {
  position: relative;
  width: 30px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
}
.btn-bell-badge {
  position: absolute;
  right: 0;
  top: -2px;
  width: 8px;
  height: 8px;
  border-radius: 4px;
  background: #f56c6c;
  color: #fff;
}
.btn-bell .el-icon-bell {
  color: #fff;
}
.user-name {
  margin-left: 10px;
}
.user-avator {
  margin-left: 20px;
}
.user-avator img {
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.el-dropdown-link {
  color: #fff;
  cursor: pointer;
}
.el-dropdown-menu__item {
  text-align: center;
}

.header .el-input__inner {
  -webkit-appearance: none;
  background-color: #156fcd;
  background-image: none;
  border-radius: 4px;
  border: 0 solid #dcdfe6;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  color: #606266;
  display: inline-block;
  font-size: inherit;
  height: 40px;
  line-height: 40px;
  outline: 0;
  padding: 0 15px;
  -webkit-transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
  transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
  width: 100%;
}

.el-input-group__append {
  background-color: #156fcd;
  color: #909399;
  vertical-align: middle;
  display: table-cell;
  position: relative;
  border: 0 solid #156fcd;
  padding: 0 20px;
  width: 1px;
  white-space: nowrap;
}
</style>
