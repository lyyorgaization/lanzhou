package com.lucien.dap.framework.core.cache.zk;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class ZkCacheDataListner implements IZkDataListener {

    private ZkCache zkCache;

    @Autowired
    public void setZkCache(ZkCache zkCache) {
        this.zkCache = zkCache;
    }

    @Override
    public void handleDataChange(String dataPath, Object data) {
        String[] pathArray = dataPath.split("/");
        String cacheType = pathArray[pathArray.length - 2];
        switch (cacheType) {
            case ZkCacheConstants.ZK_CACHE_TYPE_SINGLE:
                zkCache.setSingleCache(pathArray[pathArray.length - 1], data);
                break;
            case ZkCacheConstants.ZK_CACHE_TYPE_MEMBERS:
                zkCache.setMemberCache(pathArray[pathArray.length - 1], (Set) data);
                break;
            case ZkCacheConstants.ZK_CACHE_TYPE_LIST:
                zkCache.setListCache(pathArray[pathArray.length - 1], (List) data);
                break;
            case ZkCacheConstants.ZK_CACHE_TYPE_MAP:
                zkCache.setMapCache(pathArray[pathArray.length - 1], (Map) data);
                break;
            default:
                break;
        }
    }

    @Override
    public void handleDataDeleted(String dataPath) throws Exception {
        String key = dataPath.substring(dataPath.lastIndexOf('/') + 1);
        zkCache.del(key);
    }
}
