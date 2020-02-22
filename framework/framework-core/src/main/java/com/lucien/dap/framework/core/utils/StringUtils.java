package com.lucien.dap.framework.core.utils;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * 使用正则表达式判断字符串是否为数字
     *
     * @param value
     * @return
     */
    public static boolean isNumricRex(String value) {
        return value.matches("-?[0-9]+.？[0-9]*");
    }
}
