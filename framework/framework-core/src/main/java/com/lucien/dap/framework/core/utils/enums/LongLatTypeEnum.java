package com.lucien.dap.framework.core.utils.enums;

/**
 * @author qinbin.wan
 * @version v1.0.0
 * @date 2017/4/23
 */
public enum LongLatTypeEnum {
    BAIDU(1, "baidu"),
    GOOGLE(2, "google"),
    GAODE(3, "gaode");

    int type;
    String name;

    LongLatTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static LongLatTypeEnum valueOf(int type) {
        for (LongLatTypeEnum longLatTypeEnum : LongLatTypeEnum.values()) {
            if (longLatTypeEnum.getType() == type) {
                return longLatTypeEnum;
            }
        }

        return null;
    }
}
