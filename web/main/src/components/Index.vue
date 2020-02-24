<template>
    <el-container>
        <el-header class="header" height="69px">
            <span class="title">数据监测中心大数据平台</span>
            <el-button type="text">地图</el-button>
            <el-button type="text">数据</el-button>
            <el-button type="text">大屏</el-button>
            <span class="split">|</span>
            <el-button type="text">登录</el-button>
            <el-button type="text">注册</el-button>
        </el-header>
        <el-main class="main">
            <el-carousel indicator-position="outside" class="banner" height="425px">
                <el-carousel-item v-for="item in imageList" :key="item">
                    <!-- <img :src="item"> -->
                    <div :style="{backgroundImage: 'url(' + item + ')', backgroundSize:'contain',height:'575px'}">
                        <div style="padding-top:130px">
                            <h1 style="margin:0 15% 0">地理数据大屏可视化</h1>
                            <p style="margin:32px 15% 0">更直观更智能查看实时数据，现场资料，助力智能高效的预警系统</p>
                        </div>
                    </div>
                    <div></div>
                </el-carousel-item>
            </el-carousel>
            <div style="text-align: center;" class="content">
                <h1>功能与服务</h1>
                <el-row type="flex" class="row-bg" justify="space-around">
                    <el-col :span="4">
                        <div class="grid-content bg-purple">
                            <h2>数据存储能力</h2>
                            <p>非关系型数据库，以动态列、稀疏列非结构化的方式存储PB级以上数据</p>
                            <el-button type="primary">查看详情</el-button>
                        </div>
                    </el-col>
                    <el-col :span="4">
                        <div class="grid-content bg-purple-light">
                            <h2>数据查询能力</h2>
                            <p>千万级高并发，能够构建二级索引，具备较强的查询与管理能力，优于HDFS文件系统</p>
                            <el-button type="primary">查看详情</el-button>
                        </div>
                    </el-col>
                    <el-col :span="4">
                        <div class="grid-content bg-purple">
                            <h2>数据计算能力</h2>
                            <p>具备亿级规模数据的计算能力，支持动态横向扩展，有效解决海量空间数据分析时的IO瓶颈</p>
                            <el-button type="primary">查看详情</el-button>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </el-main>
    </el-container>
</template>
<script>
export default {
    data() {
        return {
            imageList: [
                require("../assets/01_01.jpg"),
                require("../assets/02_01.png"),
                require("../assets/03_01.png")
            ]
        };
    }
};
</script>
<style scoped>
.header {
    position: fixed;
    z-index: 999;
    color: white;
    width: 100%;
}
.main {
    padding: 0;
}
.header .title {
    margin-left: 15%;
    font-size: 28px;
    font-family: SourceHanSansCN;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
    line-height: 69px;
}

.header button {
    margin-left: 5%;
    font-size: 18px;
    font-family: SourceHanSansCN;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
    line-height: 69px;
}

.header .split {
    margin-left: 5%;
    font-size: 18px;
    font-family: SourceHanSansCN;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
    line-height: 69px;
}

.banner h1 {
    font-size: 40px;
    font-family: SourceHanSansCN;
    font-weight: 500;
    color: rgba(255, 255, 255, 1);
}

.banner p {
    font-size: 18px;
    font-family: SourceHanSansCN;
    font-weight: 400;
    color: rgba(255, 255, 255, 1);
    line-height: 30px;
}

.content h2 {
    font-size: 18px;
    font-family: SourceHanSansCN;
    font-weight: 300;
    color: rgba(0, 0, 0, 1);
}

.content p {
    font-size: 14px;
    font-family: SourceHanSansCN;
    font-weight: 300;
    color: rgba(0, 0, 0, 1);
    line-height: 22px;
    height: 59px;
}
</style>