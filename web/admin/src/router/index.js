import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    base: 'admin',
    mode: 'history',
    routes: [{
            path: '/',
            redirect: '/login'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: {
                title: '自述文件'
            },
            children: [
                {
                    path:'map',
                    component: resolve => require(['../components/page/jiance/Map.vue'], resolve),
                    meta: {
                        title: '地图'
                    }
                },
                {
                    path:'sensor',
                    component: resolve => require(['../components/page/jiance/Sensor.vue'], resolve),
                    meta: {
                        title: '皋兰山监测站'
                    }
                },
                {
                    path:'sensorList',
                    component: resolve => require(['../components/page/jiance/SensorList.vue'], resolve),
                    meta: {
                        title: '皋兰山监测站'
                    }
                },
                {
                    path: 'index',
                    component: resolve => require(['../components/common/Index.vue'], resolve),
                    meta: {
                        title: '系统首页'
                    }
                },
                {
                    path: '/userList',
                    component: resolve => require(['../components/page/systemManage/UserList.vue'], resolve),
                    meta: {
                        title: '用户管理'
                    }
                },
                {
                    path: '/roleList',
                    component: resolve => require(['../components/page/systemManage/RoleList.vue'], resolve),
                    meta: {
                        title: '角色管理'
                    }
                },
                {
                    path: '/menuList',
                    component: resolve => require(['../components/page/systemManage/MenuList.vue'], resolve),
                    meta: {
                        title: '菜单管理'
                    }
                },
                {
                    path: '/permissionList',
                    component: resolve => require(['../components/page/systemManage/PermissionList.vue'], resolve),
                    meta: {
                        title: '资源管理'
                    }
                },                
                {
                    path: '/404',
                    component: resolve => require(['../components/page/404.vue'], resolve),
                    meta: {
                        title: '404'
                    }
                },
                {
                    path: '/403',
                    component: resolve => require(['../components/page/403.vue'], resolve),
                    meta: {
                        title: '403'
                    }
                },
                {
                    path: '/editor',
                    component: resolve => require(['../components/page/VueEditor.vue'], resolve),
                    meta: {
                        title: 'editor'
                    }
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})