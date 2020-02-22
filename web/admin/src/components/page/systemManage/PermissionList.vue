<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 接口权限列表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="search.url" placeholder="接口地址" class="handle-input mr10"></el-input>
                <el-input v-model="search.remark" placeholder="权限描述" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch()">查询</el-button>
                <el-button type="primary" icon="el-icon-plus" @click="handleAdd()">新增</el-button>
            </div>
            <el-table :data="permissionList" border class="table" ref="multipleTable">
                <el-table-column prop="url" label="接口地址" width="240"></el-table-column>
                <el-table-column prop="remark" label="权限描述" width="120"></el-table-column>
                <el-table-column label="操作" width="240" align="center">
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
                    <el-form-item label="接口地址">
                        <el-input v-model="form.url"></el-input>
                    </el-form-item>
                    <el-form-item label="权限描述">
                        <el-input v-model="form.remark"></el-input>
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
            search: {},
            permissionList: [],
            totalCount: 0,
            title: "编辑",
            editVisible: false,
            form: {}
        };
    },
    created() {
        this.getData();
    },
    methods: {
        getData() {
            this.$axios.post("/omp/permission/list", this.search).then(res => {
                this.permissionList = res.data.module.list;
                this.totalCount = res.data.totalCount;
                this.search.pno = res.data.module.pno;
            });
        },
        handleCurrentChange() {
            this.search.pno = val;
            this.getData();
        },
        handleAdd() {
            this.editVisible = true;
            this.title="新增";
        },
        handleEdit(index){
            this.form = this.permissionList[index];
            this.editVisible = true;
            this.title="编辑";
        },
        handleDelete(index){
            const permissionId = this.permissionList[index].id;
            this.$confirm("是否确定删除", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
                this.$axios
                    .post("omp/permission/delete", { id: permissionId })
                    .then(res => {
                        this.$message({
                            message: "删除成功",
                            type: "success"
                        });
                        this.getData();
                    });
            });
        },
        saveData() {
            this.$axios.post("/omp/permission/edit", this.form).then(res => {
                this.$message({
                    message: "编辑成功",
                    type: "success"
                });
                this.editVisible = false;
                this.getData();
            });
        }
    }
};
</script>

              
          