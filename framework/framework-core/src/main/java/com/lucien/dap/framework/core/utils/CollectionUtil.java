package com.lucien.dap.framework.core.utils;

import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * Created by wanqinbin
 * Date： 2017/8/15
 * Version：1.0.0
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 经常少写了非，所以重新包装一遍
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }
}
