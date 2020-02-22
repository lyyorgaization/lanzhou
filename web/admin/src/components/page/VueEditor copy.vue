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
                <el-col :span="16">
                    <div class="plugins-tips">
                        Vue-Quill-Editor：基于Quill、适用于Vue2的富文本编辑器。
                        访问地址：<a href="https://github.com/surmon-china/vue-quill-editor"
                            target="_blank">vue-quill-editor</a>
                    </div>
                    <quill-editor ref="myTextEditor" v-model="content" :options="editorOption">
                        <div id="toolbar" slot="toolbar">
                            <select class="ql-header" title="段落格式">
                                <option selected>段落</option>
                                <option value="1">标题1</option>
                                <option value="2">标题2</option>
                                <option value="3">标题3</option>
                                <option value="4">标题4</option>
                                <option value="5">标题5</option>
                                <option value="6">标题6</option>
                            </select>
                        </div>

                    </quill-editor>
                    <el-button class="editor-btn" type="primary" @click="submit">提交</el-button>
                </el-col>
                <el-col :span=8
                    style="display:flex;flex-direction:column;justify-content:top;align-items:center;height:100%">
                    <el-card style="width:324px;height:576px">
                        <div v-html="content"></div>
                    </el-card>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
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
                placeholder: "请输入内容",
                modules: {
                    imageResize: {
                        displayStyles: {
                            backgroundColor: "black",
                            border: "none",
                            color: "white"
                        },
                        modules: ["Resize", "DisplaySize", "Toolbar"]
                    },
                    toolbar: {
                        container: [["image"], ["mainBody"]],
                        handlers: {
                            shadeBox: null,
                            mainBody: () => {
                                // const reg = /\<br\>/g,
                                // container = this.container,
                                // firstChild =
                                //     container.nextElementSibling.firstChild;
                                console.log(this.shadeBox);
                                // if (!this.shadeBox) {
                                //     let shadeBox = (this.shadeBox = document.createElement(
                                //         "div"
                                //     ));
                                //     shadeBox.style.cssText =
                                //         "position:absolute; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.5); cursor:pointer";
                                //     container.style.position = "relative";
                                //     shadeBox.addEventListener(
                                //         "click",
                                //         function() {
                                //             this.style.display = "none";
                                //             firstChild.innerHTML = firstChild.innerText.trim();
                                //         },
                                //         false
                                //     );
                                //     container.appendChild(shadeBox);
                                //     let innerHTML = firstChild.innerHTML;
                                //     innerHTML = innerHTML.replace(reg, "");
                                //     firstChild.innerText = innerHTML;
                                // } else {
                                //     let innerHTML = firstChild.innerHTML;
                                //     innerHTML = innerHTML.replace(reg, "");
                                //     firstChild.innerText = innerHTML;
                                //     this.shadeBox.style.display = "block";
                                // }
                            }
                        }
                    }
                }
            }
        };
    },
    mounted() {
        this.initButton();
    },
    components: {
        quillEditor
    },
    methods: {
        onEditorChange({ editor, html, text }) {
            this.content = html;
        },
        submit() {
            console.log(this.content);
            this.$message.success("提交成功！");
        },

        initButton: function() {
            //添加正文
            const sourceEditorButton = document.querySelector(".ql-mainBody");
            sourceEditorButton.style.cssText =
                "width:80px; border:1px solid #ccc; border-radius:5px;";
            sourceEditorButton.innerText = "正文";
        }
    }
};
</script>
<style scoped>
.editor-btn {
    margin-top: 20px;
}
</style>