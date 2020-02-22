package com.lucien.dap.framework.core.utils;

/**
 * Created by guwm on 2019/12/14.
 * 版本比较工具类
 */
public class VersionCompareUtil {

    /**
     * 比较版本大小
     * <p>
     * 说明：支n位基础版本号+1位子版本号
     * 示例：1.0.2>1.0.1 , 1.0.1.1>1.0.1
     *
     * @param version1 版本1
     * @param version2 版本2
     * @return 0:相同 1:version1大于version2 -1:version1小于version2
     */
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0; //版本相同
        }
        String[] v1Array = version1.split("\\.");
        String[] v2Array = version2.split("\\.");
        int v1Len = v1Array.length;
        int v2Len = v2Array.length;
        int baseLen = 0; //基础版本号位数（取长度小的）
        if (v1Len > v2Len) {
            baseLen = v2Len;
        } else {
            baseLen = v1Len;
        }

        for (int i = 0; i < baseLen; i++) { //基础版本号比较
            if (v1Array[i].equals(v2Array[i])) { //同位版本号相同
                continue; //比较下一位
            } else {
                return Integer.parseInt(v1Array[i]) > Integer.parseInt(v2Array[i]) ? 1 : -1;
            }
        }
        //基础版本相同，再比较子版本号
        if (v1Len != v2Len) {
            return v1Len > v2Len ? 1 : -1;
        } else {
            //基础版本相同，无子版本号
            return 0;
        }
    }

    /**
     * 比较时间戳大小
     * <p>
     * 示例：1882828282828>1382828282828
     *
     * @param timeStamp1 时间戳1
     * @param timeStamp2 时间戳2
     * @return 0:相同 1:timeStamp1大于timeStamp2 -1:timeStamp1小于timeStamp2
     */
    public static int compareTimeStamp(String timeStamp1, String timeStamp2) {
        if (timeStamp1.equals(timeStamp2)) {
            return 0;
        }
        Long version1 = Long.valueOf(timeStamp1);
        Long version2 = Long.valueOf(timeStamp2);
        return version1 > version2 ? 1 : -1;
    }
}
