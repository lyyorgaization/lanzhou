package com.lucien.dap.framework.core.utils;

/**
 * @author royzhang.zxx
 * @version 1.0.0.0
 * @date 2017/06/19 17:17
 */
public class NumberUtil {

    public static boolean longEquals(Long var1, Long var2) {
        if (var1 == null && var2 == null) {
            return true;
        } else if (var1 == null || var2 == null) {
            return false;
        }

        return var1.equals(var2);
    }

    public static boolean intEquals(Integer var1, Integer var2) {
        if (var1 == null && var2 == null) {
            return true;
        } else if (var1 == null || var2 == null) {
            return false;
        }

        return var1.equals(var2);
    }

}
