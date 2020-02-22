<template>
    <el-container>
        <el-header class="header" height="69px">head</el-header>
        <el-main class="main">
            <el-carousel indicator-position="outside">
                <el-carousel-item v-for="item in imageList" :key="item">
                    <img :src="item">
                </el-carousel-item>
            </el-carousel>

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
    .header{
        position: fixed;
        z-index: 999;
        color:white
    }
    .main{
        padding-top:0;
    }
</style>