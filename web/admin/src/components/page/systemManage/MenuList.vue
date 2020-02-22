<template>
    <div class="table">
        <!-- <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 菜单列表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div> -->
        <!--解决冲突-->
        <div class="container">
            <div class="handle-box">
                <el-input v-model="search.menuName" placeholder="菜单名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch()">查询</el-button>
                <el-button type="primary" icon="el-icon-plus" @click="handleAdd()">新增</el-button>
            </div>
            <el-table :data="menuList" border class="table" ref="multipleTable" :cell-style="cellStyle">
                <el-table-column prop="icon" label="图标" width="60" sytle="text-align:center">
                    <template slot-scope="scope">
                        <i :class="scope.row.icon" sytle="text-align:center"/>
                    </template>
                </el-table-column>
                <el-table-column prop="menuName" label="菜单名称" width="120"></el-table-column>
                <el-table-column prop="code" label="编码" width="240"></el-table-column>
                <el-table-column prop="url" label="地址" width="240"></el-table-column>
                <el-table-column prop="sortNumber" label="排序" width="240"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index)">编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <el-pagination background layout="prev, pager, next" :total="totalCount"
                               @current-change="handleCurrentChange"></el-pagination>
            </div>
            <!--编辑-->
            <el-dialog :title="title" :visible.sync="editVisible" width="30%">
                <el-form ref="form" :model="form" label-width="70px">
                    <el-form-item label="菜单名称">
                        <el-input v-model="form.menuName"></el-input>
                    </el-form-item>
                    <el-form-item label="编码">
                        <el-input v-model="form.code"></el-input>
                    </el-form-item>
                    <el-form-item label="父菜单">
                        <el-select v-model="form.parentId" placeholder="请选择">
                            <el-option v-for="item in topMenuList" :key="item.id" :label="item.menuName"
                                       :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="地址">
                        <el-input v-model="form.url"></el-input>
                    </el-form-item>
                    <el-form-item label="排序">
                        <el-input v-model="form.sortNumber"></el-input>
                    </el-form-item>
                    <el-form-item label="图标">
                        <el-select v-model="form.icon" placeholder="请选择">
                            <el-option v-for="item in options" :key="item" :label="item" :value="item" :class="item">
                            </el-option>
                        </el-select>
                        <!-- <el-input v-model="form.icon"></el-input> -->
                        <i :class="form.icon"/>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveData()">确 定</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                menuList: [],
                search: {
                    menuName: "",
                    pno: "1",
                    psize: "10"
                },
                totalCount: 0,
                form: {},
                editVisible: false,
                title: "编辑",
                topMenuList: [],
                options: [
                    "el-icon-lx-attentionforbid",
                    "el-icon-lx-attentionforbidfill",
                    "el-icon-lx-attention",
                    "el-icon-lx-attentionfill",
                    "el-icon-lx-tag",
                    "el-icon-lx-tagfill",
                    "el-icon-lx-people",
                    "el-icon-lx-peoplefill",
                    "el-icon-lx-notice",
                    "el-icon-lx-noticefill",
                    "el-icon-lx-mobile",
                    "el-icon-lx-mobilefill",
                    "el-icon-lx-voice",
                    "el-icon-lx-voicefill",
                    "el-icon-lx-unlock",
                    "el-icon-lx-lock",
                    "el-icon-lx-home",
                    "el-icon-lx-homefill",
                    "el-icon-lx-delete",
                    "el-icon-lx-deletefill",
                    "el-icon-lx-notification",
                    "el-icon-lx-notificationfill",
                    "el-icon-lx-notificationforbidfill",
                    "el-icon-lx-like",
                    "el-icon-lx-likefill",
                    "el-icon-lx-comment",
                    "el-icon-lx-commentfill",
                    "el-icon-lx-camera",
                    "el-icon-lx-camerafill",
                    "el-icon-lx-warn",
                    "el-icon-lx-warnfill",
                    "el-icon-lx-time",
                    "el-icon-lx-timefill",
                    "el-icon-lx-location",
                    "el-icon-lx-locationfill",
                    "el-icon-lx-favor",
                    "el-icon-lx-favorfill",
                    "el-icon-lx-skin",
                    "el-icon-lx-skinfill",
                    "el-icon-lx-news",
                    "el-icon-lx-newsfill",
                    "el-icon-lx-record",
                    "el-icon-lx-recordfill",
                    "el-icon-lx-emoji",
                    "el-icon-lx-emojifill",
                    "el-icon-lx-message",
                    "el-icon-lx-messagefill",
                    "el-icon-lx-goods",
                    "el-icon-lx-goodsfill",
                    "el-icon-lx-crown",
                    "el-icon-lx-crownfill",
                    "el-icon-lx-move",
                    "el-icon-lx-add",
                    "el-icon-lx-hot",
                    "el-icon-lx-hotfill",
                    "el-icon-lx-com.lucien.dap.data.server.service",
                    "el-icon-lx-servicefill",
                    "el-icon-lx-present",
                    "el-icon-lx-presentfill",
                    "el-icon-lx-pic",
                    "el-icon-lx-picfill",
                    "el-icon-lx-rank",
                    "el-icon-lx-rankfill",
                    "el-icon-lx-male",
                    "el-icon-lx-female",
                    "el-icon-lx-down",
                    "el-icon-lx-top",
                    "el-icon-lx-recharge",
                    "el-icon-lx-rechargefill",
                    "el-icon-lx-forward",
                    "el-icon-lx-forwardfill",
                    "el-icon-lx-info",
                    "el-icon-lx-infofill",
                    "el-icon-lx-redpacket",
                    "el-icon-lx-redpacket_fill",
                    "el-icon-lx-roundadd",
                    "el-icon-lx-roundaddfill",
                    "el-icon-lx-friendadd",
                    "el-icon-lx-friendaddfill",
                    "el-icon-lx-cart",
                    "el-icon-lx-cartfill",
                    "el-icon-lx-more",
                    "el-icon-lx-moreandroid",
                    "el-icon-lx-back",
                    "el-icon-lx-right",
                    "el-icon-lx-shop",
                    "el-icon-lx-shopfill",
                    "el-icon-lx-question",
                    "el-icon-lx-questionfill",
                    "el-icon-lx-roundclose",
                    "el-icon-lx-roundclosefill",
                    "el-icon-lx-roundcheck",
                    "el-icon-lx-roundcheckfill",
                    "el-icon-lx-global",
                    "el-icon-lx-mail",
                    "el-icon-lx-punch",
                    "el-icon-lx-exit",
                    "el-icon-lx-upload",
                    "el-icon-lx-read",
                    "el-icon-lx-file",
                    "el-icon-lx-link",
                    "el-icon-lx-full",
                    "el-icon-lx-group",
                    "el-icon-lx-friend",
                    "el-icon-lx-profile",
                    "el-icon-lx-addressbook",
                    "el-icon-lx-calendar",
                    "el-icon-lx-text",
                    "el-icon-lx-copy",
                    "el-icon-lx-share",
                    "el-icon-lx-wifi",
                    "el-icon-lx-vipcard",
                    "el-icon-lx-weibo",
                    "el-icon-lx-remind",
                    "el-icon-lx-refresh",
                    "el-icon-lx-filter",
                    "el-icon-lx-settings",
                    "el-icon-lx-scan",
                    "el-icon-lx-qrcode",
                    "el-icon-lx-cascades",
                    "el-icon-lx-apps",
                    "el-icon-lx-sort",
                    "el-icon-lx-searchlist",
                    "el-icon-lx-search",
                    "el-icon-lx-edit"
                ],
                value: ""
            };
        },
        created() {
            this.getData();
        },
        methods: {
            getData() {
                this.$axios.post("/omp/menu/menuList", this.search).then(res => {
                    this.menuList = res.data.module.list;
                    this.search.pno = res.data.module.pno;
                    this.totalCount = res.data.module.totalCount;
                });
            },
            cellStyle(row) {
                if (row.columnIndex == 0) {
                    return "text-align:center";
                }
            },
            handleCurrentChange(curpage) {
                this.search.pno = curpage;
                this.getData();
            },
            handleEdit(index) {
                this.editVisible = true;
                this.$axios
                    .post("/omp/menu/menuList", {parentId: 0, psize: 100, pno: 1})
                    .then(res => {
                        this.topMenuList = [];
                        this.topMenuList = res.data.module.list;
                        this.topMenuList.unshift({id: 0, menuName: "顶级菜单"});
                    });
                this.form = this.menuList[index];
            },
            handleAdd() {
                this.$axios
                    .post("/omp/menu/menuList", { parentId: 0, psize: 100, pno: 1 })
                    .then(res => {
                        this.topMenuList = [];
                        this.topMenuList = res.data.module.list;
                        this.topMenuList.unshift({ id: 0, menuName: "顶级菜单" });
                    });
                this.editVisible = true;
                this.form = {};
            },
            saveData() {
                this.$axios.post("/omp/menu/edit", this.form).then(res => {
                    this.editVisible = false;
                    this.$message({
                        message: "保存成功",
                        type: "success"
                    });
                    this.getData();
                });
            },
            handleDelete(index) {
                this.$confirm("是否确定删除", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {
                    this.$axios
                        .post("/omp/menu/delete", {id: this.menuList[index].id})
                        .then(res => {
                            this.$message({
                                message: "保存成功",
                                type: "success"
                            });
                            this.getData();
                        });
                });
            }
        }
    };
</script>

