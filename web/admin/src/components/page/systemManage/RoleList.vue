<template>
    <div class="table">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>
                    <i class="el-icon-lx-cascades"></i> 角色列表
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
            <div class="handle-box">
                <el-input v-model="search.roleName" placeholder="角色名" class="handle-input mr10"></el-input>
                <el-button type="primary" icon="el-icon-search" @click="handleSearch()">查询</el-button>
                <el-button type="primary" icon="el-icon-plus" @click="handleAdd()">新增</el-button>
            </div>
            <el-table :data="roleList" border class="table" ref="multipleTable">
                <el-table-column type="selection" width="55" align="center"></el-table-column>
                <el-table-column prop="roleName" label="角色名称" width="120"></el-table-column>
                <el-table-column prop="description" label="备注" width="240"></el-table-column>
                <el-table-column label="操作" width="240" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index)">编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="handleDelete(scope.$index)">删除
                        </el-button>
                        <el-button type="text" icon="el-icon-edit" @click="handelMenu(scope.$index)">关联菜单</el-button>
                        <el-button type="text" icon="el-icon-edit" @click="handelPermission(scope.$index)">关联接口
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
                    <el-form-item label="角色名">
                        <el-input v-model="form.roleName"></el-input>
                    </el-form-item>
                    <el-form-item label="备注">
                        <el-input v-model="form.description"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveData()">确 定</el-button>
                </span>
            </el-dialog>

            <el-dialog title="关联菜单" :visible.sync="menuVisible" width="30%">
                <el-tree :data="menuTree" show-checkbox node-key="id" :props="menuProps" ref="tree"
                    check-strictly="true">
                </el-tree>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="menuVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveMenu()">确 定</el-button>
                </span>
            </el-dialog>

            <el-dialog title="接口授权" :visible.sync="permissionVisible">
                <el-checkbox-group v-model="rolePermissons.permissionIds">
                    <el-checkbox v-for="item in allPermissions" :key="item.id" :value="item.id" :label="item.id"
                        name="permissions">
                        {{item.remark}}
                    </el-checkbox>
                </el-checkbox-group>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="menuVisible = false">取 消</el-button>
                    <el-button type="primary" @click="savePermission()">确 定</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            roleList: [],
            totalCount: 0,
            editVisible: false,
            form: {
                roleName: "",
                description: "",
                id: 0
            },
            title: "编辑",
            search: {
                roleName: "",
                pno: 1,
                psize: 10
            },
            menuVisible: false,
            menuTree: [],
            menuProps: {
                children: "subs",
                label: "title"
            },
            roleMenuId: {
                roleId: "",
                menuIds: []
            },
            rolePermissons: { roleId: 0, permissionIds: [] },
            allPermissions: [],
            permissionVisible: false
        };
    },
    created() {
        this.getData();
    },
    methods: {
        getData() {
            this.$axios.post("/omp/role/getAllRole", this.search).then(res => {
                this.roleList = res.data.module.list;
                this.search.pno = res.data.module.pno;
                this.totalCount = res.data.module.totalCount;
            });
        },
        handleEdit(index) {
            this.editVisible = true;
            this.title = "编辑";
            const item = this.roleList[index];
            this.form.roleName = item.roleName;
            this.form.description = item.description;
            this.form.id = item.id;
        },
        handleAdd() {
            this.editVisible = true;
            this.title = "新增";
            this.form.roleName = "";
            this.form.description = "";
            this.form.id = "";
        },
        saveData() {
            this.$axios.post("omp/role/edit", this.form).then(res => {
                this.editVisible = false;
                this.$message({
                    message: "保存成功",
                    type: "success"
                });
                this.getData();
            });
        },
        handleCurrentChange(val) {
            this.search.pno = val;
            this.getData();
        },
        handleSearch() {
            this.search.pno = 1;
            this.getData();
        },
        handleDelete(index) {
            this.$confirm("是否确定删除", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            }).then(() => {
                const item = this.roleList[index];
                this.$axios
                    .post("omp/role/delete", { id: item.id })
                    .then(res => {
                        this.$message({
                            message: "删除成功",
                            type: "success"
                        });
                        this.getData();
                    });
            });
        },
        handelMenu(index) {
            this.menuVisible = true;
            const item = this.roleList[index];
            this.roleMenuId.roleId = item.id;
            this.$axios.post("/omp/menu/menuTree", {}).then(res => {
                this.menuTree = res.data.module;
            });
            this.$axios
                .post("/omp/role/getRoleMenu", { id: item.id })
                .then(res => {
                    this.$refs.tree.setCheckedKeys([]);
                    var arry = res.data.module;
                    var mIds = [];
                    if (arry != null && arry.length > 0) {
                        for (let i = 0; i < arry.length; i++) {
                            mIds.push(arry[i].id);
                        }
                    }
                    this.$refs.tree.setCheckedKeys(mIds);
                });
        },
        saveMenu() {
            this.roleMenuId.menuIds = this.getCheckedKeys(
                this.menuTree,
                this.$refs.tree.getCheckedKeys(),
                "id"
            );
            this.$axios
                .post("omp/role/editRoleMenu", this.roleMenuId)
                .then(res => {
                    this.$message({
                        message: "更新成功",
                        type: "success"
                    });
                });
        },
        handelPermission(index) {
            this.rolePermissons.roleId = this.roleList[index].id;
            this.permissionVisible = true;
            this.$axios
                .post("omp/permission/list", { pno: 1, psize: 200 })
                .then(res => {
                    this.allPermissions = res.data.module.list;
                    this.$axios
                        .post("omp/role_permission/rolePermissonIds", {
                            id: this.roleList[index].id
                        })
                        .then(res => {
                            this.rolePermissons.permissionIds = res.data.module;
                        });
                });
        },
        savePermission() {
            this.$axios
                .post("omp/role_permission/editRolePermission", this.rolePermissons)
                .then(res => {
                    this.$message({
                        message: "更新成功",
                        type: "success"
                    });
                });
        },
        getCheckedKeys(data, keys, key) {
            var res = [];
            recursion(data, false);
            return res;

            // arr -> 树形总数据
            // keys -> getCheckedKeys获取到的选中key值
            // isChild -> 用来判断是否是子节点
            function recursion(arr, isChild) {
                var aCheck = [];
                for (var i = 0; i < arr.length; i++) {
                    var obj = arr[i];
                    aCheck[i] = false;

                    if (obj.subs) {
                        aCheck[i] = recursion(obj.subs, true)
                            ? true
                            : aCheck[i];
                        if (aCheck[i]) {
                            res.push(obj[key]);
                        }
                    }

                    for (var j = 0; j < keys.length; j++) {
                        if (obj[key] == keys[j]) {
                            aCheck[i] = true;
                            if (res.indexOf(obj[key]) == -1) {
                                res.push(obj[key]);
                            }
                            break;
                        }
                    }
                }
                if (isChild) {
                    return aCheck.indexOf(true) != -1;
                }
            }
        }
    }
};
</script>

              
          