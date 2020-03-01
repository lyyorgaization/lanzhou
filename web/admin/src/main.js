import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios';
import ElementUI from 'element-ui';
import VueI18n from 'vue-i18n';
import {
    messages
} from './components/common/i18n';
import BaiduMap from 'vue-baidu-map'
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题
import './assets/css/icon.css';
import './components/common/directives';
import "babel-polyfill";
import "./assets/css/main.css";

Vue.config.productionTip = false;
Vue.use(VueI18n);
Vue.use(ElementUI, {
    size: 'small'
});

Vue.use(BaiduMap, {
    // ak 是在百度地图开发者平台申请的密钥 详见 http://lbsyun.baidu.com/apiconsole/key */
    ak: 'W3snqVzGLE554YKO8azARWwWpG5vKYqI'
})

import VueDND from "awe-dnd";
Vue.use(VueDND);
import VCharts from 'v-charts';
Vue.use(VCharts)
axios.defaults.withCredentials = true;
Vue.prototype.$axios = axios;

import echarts from 'echarts'
Vue.prototype.$echarts = echarts;

const i18n = new VueI18n({
    locale: 'zh',
    messages
});

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    const role = localStorage.getItem('ms_username');
    if (!role && to.path !== '/login') {
        next('/login');
    }
    // else if (to.meta.permission) {
    //     // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
    //     role === 'admin' ? next() : next('/403');
    // } 
    else {
        // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
        if (navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {
            Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
                confirmButtonText: '确定'
            });
        } else {
            next();
        }
    }
});
axios.defaults.withCredentials = true;
axios.defaults.baseURL = '/'
axios.interceptors.response.use(response => {
    if (response.request.responseURL.indexOf("openapi.ecois.info") == -1) {
        if (response.data.code != 0) {
            ElementUI.Message({
                message: response.data.msg,
                type: "error"
            });
            if (response.data.code === 105) {
                localStorage.setItem("ms_username", "");
                this.$router.push('/login');
            }
            return Promise.reject(response.data.msg);
        } else {
            return response;
        }
    }
    return response;
}, err => {
    if (err && err.response) {
        switch (err.response.status) {
            case 400:
                err.message = '请求错误(400)';
                break;
            case 401:
                this.$router.push('/login');
                break;
            case 403:
                err.message = '拒绝访问(403)';
                break;
            case 404:
                err.message = '请求出错(404)';
                break;
            case 408:
                err.message = '请求超时(408)';
                break;
            case 500:
                err.message = '服务器错误(500)';
                break;
            case 501:
                err.message = '服务未实现(501)';
                break;
            case 502:
                err.message = '网络错误(502)';
                break;
            case 503:
                err.message = '服务不可用(503)';
                break;
            case 504:
                err.message = '网络超时(504)';
                break;
            case 505:
                err.message = 'HTTP版本不受支持(505)';
                break;
            default:
                err.message = `连接出错(${err.response.status})!`;
        }
    } else {
        err.message = '连接服务器失败!'
    }
    ElementUI.Message({
        message: err.message,
        type: "error"
    });
    return Promise.reject(err);
});



new Vue({
    router,
    i18n,
    render: h => h(App)
}).$mount('#app');