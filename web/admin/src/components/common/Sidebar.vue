<template>
  <div class="sidebar">
    <el-menu class="sidebar-el-menu"
             :default-active="onRoutes"
             :collapse="collapse"
             text-color="#bfcbd9"
             active-text-color="#fff"
             unique-opened
             router>
      <template v-for="item in items">
        <template v-if="item.subs">
          <el-submenu :index="item.index"
                      :key="item.index">
            <template slot="title">
              <!-- <i :class="item.icon"></i> -->
              <span slot="title">{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-submenu v-if="subItem.subs"
                          :index="subItem.index"
                          :key="subItem.index">
                <template slot="title">{{ subItem.title }}</template>
                <el-menu-item v-for="(threeItem,i) in subItem.subs"
                              :key="i"
                              :index="threeItem.index">{{ threeItem.title }}</el-menu-item>
              </el-submenu>
              <el-menu-item v-else
                            :index="subItem.index"
                            :key="subItem.index">{{ subItem.title }}</el-menu-item>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.index"
                        :key="item.index">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import bus from "../common/bus";
export default {
  data() {
    return {
      collapse: false,
      items: []
    };
  },
  computed: {
    onRoutes() {
      return this.$route.path.replace("/", "");
    }
  },
  created() {
    // 通过 Event Bus 进行组件间通信，来折叠侧边栏
    bus.$on("collapse", msg => {
      this.collapse = msg;
    });
    this.getMenu();
  },
  methods: {
    getMenu() {
      if (sessionStorage.getItem("menu") != null) {
        this.items = JSON.parse(sessionStorage.getItem("menu"));
      } else {
        if (this.items == null || this.items.length == 0) {
          this.$axios.post("/omp/user/getMenuTree", {}).then(res => {
            this.items = res.data.module;
            sessionStorage.setItem("menu", JSON.stringify(res.data.module));
          });
        }
      }
    }
  }
};
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 6.2vh;
  bottom: 0;
  overflow-y: scroll;
  width: 15.6vw;
}
.sidebar::-webkit-scrollbar {
  width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
  width: 250px;
}
.sidebar > ul {
  height: 100%;
  background-color: #002b69;
}
.el-menu-item.is-active {
  background-color: #1055b9;
}

.el-menu-item {
  height: 50px;
  line-height: 50px;
  padding: 0 45px;
  min-width: 200px;
  background-color: #002253;
}

.el-menu-item:hover {
  background-color: #1055b9;
}


</style>
