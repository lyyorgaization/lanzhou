import Vue from 'vue'
Vue.prototype.$getHelperImageUrl =
    imageId => {
        if (imageId != null) {
            return "/terminal/admin/helper/image/get?id=" + imageId;
        } else {
            return null;
        }

    }