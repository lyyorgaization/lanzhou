<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 用户列表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <!--解决冲突-->
            <div class="handle-box">
                <el-select v-model="searchData.status" placeholder="状态" class="handle-select mr10">
                    <el-option key="1" label="已启用" value="1"></el-option>
                    <el-option key="0" label="已禁用" value="0"></el-option>
                </el-select>
                <el-input v-model="searchData.username" placeholder="用户名" class="handle-input mr10"></el-input>
                <el-input v-model="searchData.userNumber" placeholder="用户编号" class="handle-input mr10"></el-input>
                <el-input v-model="searchData.nickname" placeholder="姓名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="search" @click="search()">搜索</el-button>
                <el-button type="primary" icon="add" @click="handleAdd()">新增</el-button>
            </div>
            <el-table :data="data" border class="table" ref="multipleTable">
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="username" label="用户名" width="120"></el-table-column>
                <el-table-column prop="userNumber" label="用户编号" width="120"></el-table-column>
                <el-table-column prop="nickname" label="姓名" width="120"></el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="120"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index)">编辑</el-button>
                        <el-button type="text" icon="el-icon-edit" @click="handlePermission(scope.$index)">授权
                        </el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="deleteUser(scope.$index)">删除
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
                    <el-form-item></el-form-item>
                    <el-form-item label="用户名">
                        <el-input v-model="form.username"></el-input>
                    </el-form-item>
                     <el-form-item label="用户编号">
                        <el-input v-model="form.userNumber"></el-input>
                        </el-form-item>
                    <el-form-item label="姓名">
                        <el-input v-model="form.nickname"></el-input>
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select v-model="form.status" placeholder="状态" class="handle-select mr10">
                            <el-option key="1" label="已启用" value="1"></el-option>
                            <el-option key="0" label="已禁用" value="0"></el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveData()">确 定</el-button>
                </span>
            </el-dialog>
            <!--授权-->
            <el-dialog title="变更角色" :visible.sync="roleVisible" width="30%">
                <el-checkbox-group v-model="editRoleIds.roleIdList">
                    <el-checkbox v-for="item in roleList" :key="item.id" :label="item.id" name="role">{{item.roleName}}
                    </el-checkbox>
                </el-checkbox-group>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="roleVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveRole()">确 定</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            searchData: {
                status: "",
                username: "",
                nickname: ""
            },
            tableData: [],
            curpage: 1,
            totalCount: 0,
            editVisible: false,
            title: "新增",
            userId: "",
            form: {
                id: "",
                username: "",
                nickname: "",
                status: ""
            },
            roleVisible: false,
            roleList: [],
            editRoleIds: {
                userId: 0,
                roleIdList: []
            }
        };
    },
    created() {
        this.getData();
    },
    computed: {
        data() {
            return this.tableData;
        }
    },
    methods: {
        getData() {
            this.searchData.pno = this.curpage;
            this.searchData.psize = 10;
            this.$axios
                .post("/omp/user/queryUser", this.searchData)
                .then(res => {
                    this.tableData = res.data.module.list;
                    this.curpage = res.data.module.pno;
                    this.totalCount = res.data.module.totalCount;
                });
        },
        handleCurrentChange(val) {
            this.curpage = val;
            this.getData();
        },
        handleEdit(index) {
            this.editVisible = true;
            this.title = "编辑";
            this.idx = index;
            const item = this.tableData[index];
            this.form.username = item.username;
            this.form.userNumber = item.userNumber;
            this.form.nickname = item.nickname;
            this.form.status = item.status + "";
            this.form.id = item.id;
            this.editVisible = true;
        },
        handleAdd() {
            this.editVisible = true;
            this.title = "新增";
            this.form.username = "";
            this.form.nickname = "";
            this.form.userNumber = "";
            this.form.status = "";
            this.form.id = "";
        },
        saveData() {
            var url = "";
            // if ((this.title = "新增")) {
            //   url = "/omp/user/add";
            // } else {
            url = "/omp/user/edit";
            // }
            this.$axios.post(url, this.form).then(res => {
                this.getData();
                this.editVisible = false;
                this.$message({
                    message: "保存成功",
                    type: "success"
                });
            });
        },
        handlePermission(index) {
            this.roleVisible = true;
            this.$axios.post("/omp/role/getAllRole", {}).then(res => {
                this.roleList = res.data.module.list;
                console.log(this.roleList);
            });
            const item = this.tableData[index];
            this.editRoleIds.userId = item.id;
            this.$axios
                .post("/omp/user/getUserRoleList", {
                    id: item.id
                })
                .then(res => {
                    var userRole = res.data.module;
                    for (
                        let i = this.editRoleIds.roleIdList.length - 1;
                        i >= 0;
                        i--
                    ) {
                        this.$delete(this.editRoleIds.roleIdList, i);
                    }
                    for (let i = 0; i < userRole.length; i++) {
                        this.$set(
                            this.editRoleIds.roleIdList,
                            i,
                            userRole[i].id
                        );
                    }
                });
        },
        saveRole() {
            this.$axios
                .post("/omp/user/editUserRole", this.editRoleIds)
                .then(res => {
                    this.$message({
                        message: "保存成功",
                        type: "success"
                    });
                    this.roleVisible = false;
                });
        },
        search() {
            this.getData();
        },
        deleteUser(index) {
            this.$confirm("是否确定删除", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
                const item = this.tableData[index];
                this.$axios
                    .post("/omp/user/deleteUser", { id: item.id })
                    .then(res => {
                        this.$message({
                            message: "删除成功",
                            type: "success"
                        });
                        this.getData();
                    });
            });
        }
    }
};
</script>

<style scoped>
.handle-box {
    margin-bottom: 20px;
}

.handle-select {
    width: 120px;
}

.handle-input {
    width: 300px;
    display: inline-block;
}
.del-dialog-cnt {
    font-size: 16px;
    text-align: center;
}
.table {
    width: 100%;
    font-size: 14px;
}
.red {
    color: #ff0000;
}
.mr10 {
    margin-right: 10px;
}
</style>

