package com.lucien.dap.framework.core.cache.zk;

import com.lucien.dap.framework.core.zk.ZookeeperClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
public class ZkCache {
    private ZookeeperClient zkClientUtil;
    private String cacheNode;
    private Map<String, Object> singleCache = new HashMap<>();
    private Map<String, Set<Object>> memberCache = new HashMap<>();
    private Map<String, Map<Object, Object>> mapCache = new HashMap<>();
    private Map<String, List<String>> listCache = new HashMap<>();


    @Autowired
    public void setZkClientUtil(ZookeeperClient zkClientUtil) {
        this.zkClientUtil = zkClientUtil;
    }

    @Value("${zk.cache.node}")
    public void setCacheNode(String cacheNode) {
        this.cacheNode = cacheNode;
    }


    //region protected

    <T> void setSingleCache(String key, T value) {
        singleCache.put(key, value);
    }

    void setMemberCache(String key, Set value) {
        memberCache.put(key, value);
    }

    void setListCache(String key, List value) {
        listCache.put(key, value);
    }

    void setMapCache(String key, Map value) {
        this.mapCache.put(key, value);
    }
    //endregion

    //region public
    //region set method
    public <T> void set(String key, T value) {
        try {
            zkClientUtil.writeData(cacheNode + "/" + ZkCacheConstants.ZK_CACHE_TYPE_SINGLE + "/" + key, value);
        } catch (Exception e) {
            log.error("<<<<设置zookeeper缓存出错", e);
        }
    }

    /**
     * 高并发下可能不安全,慎用
     *
     * @param key
     * @param value
     */
    public void mset(String key, Object value) {
        String path = cacheNode + "/" + ZkCacheConstants.ZK_CACHE_TYPE_MEMBERS + "/" + key;
        Set<Object> set;
        if (zkClientUtil.getZkClient().exists(path)) {
            set = zkClientUtil.getZkClient().readData(path);
        } else {
            set = new HashSet<>();
        }
        set.add(value);
        try {
            zkClientUtil.writeData(path, set);
        } catch (Exception e) {
            log.error("<<<<设置zookeeper缓存出错", e);
        }
    }

    public void msetAll(String key, Set value) {
        String path = cacheNode + "/" + ZkCacheConstants.ZK_CACHE_TYPE_MEMBERS + "/" + key;
        try {
            zkClientUtil.writeData(path, value);
        } catch (Exception e) {
            log.error("<<<<设置zookeeper缓存出错", e);
        }
    }

    /**
     * 高并发下可能不安全,慎用
     *
     * @param key
     * @param value
     */
    public void lset(String key, Object value) {
        String path = cacheNode + "/" + ZkCacheConstants.ZK_CACHE_TYPE_LIST + "/" + key;
        List<Object> list;
        if (zkClientUtil.getZkClient().exists(path)) {
            list = zkClientUtil.getZkClient().readData(path);
        } else {
            list = new LinkedList<>();
        }
        list.add(value);
        try {
            zkClientUtil.writeData(path, list);
        } catch (Exception e) {
            log.error("<<<<设置zookeeper缓存出错", e);
        }
    }

    public <T> void lsetAll(String key, List<T> value) {
        String path = cacheNode + "/" + ZkCacheConstants.ZK_CACHE_TYPE_LIST + "/" + key;
        try {
            zkClientUtil.writeData(path, value);
        } catch (Exception e) {
            log.error("<<<<设置zookeeper缓存出错", e);
        }
    }

    /**
     * 高并发下可能不安全,慎用
     *
     * @param key
     * @param field
     * @param value
     * @param <T>
     */
    public <T> void hset(String key, String field, T value) {
        String path = cacheNode + "/" + ZkCacheConstants.ZK_CACHE_TYPE_MAP + "/" + key;
        Map<String, Object> map;
        if (zkClientUtil.getZkClient().exists(path)) {
            map = zkClientUtil.getZkClient().readData(path);
        } else {
            map = new HashMap<>();
        }
        map.put(field, value);
        try {
            zkClientUtil.writeData(path, map);
        } catch (Exception e) {
            log.error("<<<<设置zookeeper缓存出错", e);
        }
    }

    public void hsetAll(String key, Map value) {
        String path = cacheNode + "/" + ZkCacheConstants.ZK_CACHE_TYPE_MAP + "/" + key;
        try {
            zkClientUtil.writeData(path, value);
        } catch (Exception e) {
            log.error("<<<<设置zookeeper缓存出错", e);
        }
    }

    //endregion
    //region get method
    public <T> T get(String key) {
        return (T) singleCache.get(key);
    }

    public <T> Set<T> members(String key) {
        return (Set<T>) memberCache.get(key);
    }

    public <T> List<T> lget(String key) {
        return (List<T>) listCache.get(key);
    }

    public <T> T hget(String key, String field) {
        if (mapCache.containsKey(key)) {
            Map<Object, Object> map = mapCache.get(key);
            return (T) map.get(field);
        }
        return null;
    }

    public Map hgetAll(String key) {
        if (mapCache.containsKey(key)) {
            return mapCache.get(key);
        }
        return null;
    }

    //endregion
    //region del method
    public void del(String key) {
        zkClientUtil.getZkClient().delete(cacheNode + "/" + ZkCacheConstants.ZK_CACHE_TYPE_SINGLE + "/" + key);
    }

    public void ldel(String key) {
        zkClientUtil.getZkClient().delete(cacheNode + "/" + ZkCacheConstants.ZK_CACHE_TYPE_LIST + "/" + key);
    }

    public void mdel(String key) {
        zkClientUtil.getZkClient().delete(cacheNode + "/" + ZkCacheConstants.ZK_CACHE_TYPE_MEMBERS + "/" + key);
    }

    public void mapdel(String key) {
        zkClientUtil.getZkClient().delete(cacheNode + "/" + ZkCacheConstants.ZK_CACHE_TYPE_MAP + "/" + key);
    }
    //endregion
    //endregion
}
