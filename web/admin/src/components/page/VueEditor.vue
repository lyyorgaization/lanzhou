<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-lx-calendar"></i> 表单</el-breadcrumb-item>
                <el-breadcrumb-item>编辑器</el-breadcrumb-item>
            </el-breadcrumb>
        </div>

        <div class="container">
            <el-row>
                <div class="plugins-tips">
                    Vue-Quill-Editor：基于Quill、适用于Vue2的富文本编辑器。
                    访问地址：<a href="https://github.com/surmon-china/vue-quill-editor" target="_blank">vue-quill-editor</a>
                </div>
                <quill-editor ref="myTextEditor" v-model="content" :options="editorOption">
                    <div id="toolbar" slot="toolbar">
                        <button class="ql-clean" style="color:#000000" value="clean">清除</button>
                        <button class="ql-header el-button--primary" style="color:#000000" value="H1">主标题</button>
                        <button class="ql-header el-button--primary" style="color:#000000" value="H2">二级标题</button>
                        <button class="ql-header el-button--primary" style="color:#000000" value="H6">正文</button>
                        <button class="ql-align" style="color:#000000" value="justify">靠左</button>
                        <button class="ql-align" style="color:#000000" value="center">居中</button>
                        <button class="ql-align" style="color:#000000" value="right">靠右</button>
                        <select class="ql-size">
                            <option value="small"></option>
                            <option selected></option>
                            <option value="large"></option>
                            <option value="huge"></option>
                        </select>
                        <button class="ql-bold"></button>
                        <el-upload ref="upload" class="upload-demo" :http-request="handleUploadImage" action=""
                            :show-upload-list="false" list-type="picture">
                            <el-button style="color:#000000" type="primary">插入图片</el-button>
                        </el-upload>
                    </div>
                </quill-editor>
                <el-button class="editor-btn" type="primary" @click="submit">提交</el-button>
            </el-row>
        </div>
    </div>
</template>

<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import "../../assets/css/helper_article.css"
import Quill from "quill";
import { quillEditor } from "vue-quill-editor";
import imageResize from "quill-image-resize-module";
Quill.register("modules/imageResize", imageResize);

export default {
    name: "editor",
    data: function() {
        return {
            content: "",
            editorOption: {
                placeholder: "请输入文本...",
                modules: {
                    toolbar: "#toolbar",
                    imageResize: {
                        displayStyles: {
                            backgroundColor: "black",
                            border: "none",
                            color: "white"
                        },
                        modules: ["Resize", "DisplaySize", "Toolbar"]
                    }
                }
            }
        };
    },
    computed: {
        editor() {
            return this.$refs.myTextEditor.quill;
        }
    },
    components: {
        quillEditor
    },
    methods: {
        submit() {
            console.log(this.content);
            this.$message.success("提交成功！");
        },
        //初始化编辑内容
        setContent(innerHTML) {
            setTimeout(() => {
                const quill = this.editor;
                quill.container.querySelector(
                    ".ql-editor"
                ).innerHTML = innerHTML;
            });
        },
        handleUploadImage(file) {
            console.log("上传图片");
            console.log(file);
            const param = new FormData();
            param.append("file", file.file);
            param.append("type", 0);
            param.append("fileName", file.file.name);
            let config = {
                //添加请求头
                headers: { "Content-Type": "multipart/form-data" }
            };
            this.$axios
                .post("/terminal/admin/helper/image/upload", param, config)
                .then(res => {
                    // 获取富文本组件实例
                    let quill = this.editor;
                    // 如果上传成功
                    if (res) {
                        let index = 0;
                        let length = 0;
                        // 获取光标所在位置
                        console.log(quill.getSelection());
                        if (quill.getSelection() != undefined) {
                            index = quill.getSelection().index;
                            length = quill.getSelection().length;
                        }
                        console.log(index);
                        console.log(length);
                        // 插入图片，res为服务器返回的图片链接地址
                        quill.insertEmbed(
                            index + length,
                            "image",
                            "/terminal/admin/helper/image/get?id=" +
                                res.data.module
                        );
                        // 调整光标到最后
                        quill.setSelection(index + length + 1);
                    } else {
                        // 提示信息，需引入Message
                        Message.error("图片插入失败");
                    }
                });
            this.$refs.upload.clearFiles();
        }
    }
};
</script>