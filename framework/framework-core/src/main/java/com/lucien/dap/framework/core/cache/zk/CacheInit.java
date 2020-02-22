package com.lucien.dap.framework.core.cache.zk;

import com.lucien.dap.framework.core.zk.ZookeeperClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class CacheInit {
    private ZookeeperClient zookeeperClient;
    private ZkCacheKeyListner zkCacheKeyListner;
    private String cacheNode;
    private ZkCacheDataListner zkCacheDataListner;

    @Autowired
    public void setZookeeperClient(ZookeeperClient zookeeperClient) {
        this.zookeeperClient = zookeeperClient;
    }

    @Autowired
    public void setZkCacheDataListner(ZkCacheDataListner zkCacheDataListner) {
        this.zkCacheDataListner = zkCacheDataListner;
    }

    @Value("${zk.cache.node}")
    public void setCacheNode(String cacheNode) {
        this.cacheNode = cacheNode;
    }

    @Autowired
    public void setZkCacheKeyListner(ZkCacheKeyListner zkCacheKeyListner) {
        this.zkCacheKeyListner = zkCacheKeyListner;
    }

    @PostConstruct
    public void init() {
        List<String> children = zookeeperClient.getZkClient().getChildren(cacheNode);
        initNode(children, ZkCacheConstants.ZK_CACHE_TYPE_SINGLE);
        initNode(children, ZkCacheConstants.ZK_CACHE_TYPE_MEMBERS);
        initNode(children, ZkCacheConstants.ZK_CACHE_TYPE_MAP);
        initNode(children, ZkCacheConstants.ZK_CACHE_TYPE_LIST);
    }

    private void initNode(List<String> children, String node) {
        String path = cacheNode + "/" + node;
        if (!children.contains(node)) {
            zookeeperClient.createPersistent(path);
        } else {
            List<String> keys = zookeeperClient.getZkClient().getChildren(path);
            for (String key : keys) {
                zookeeperClient.getZkClient().subscribeDataChanges(path + "/" + key, zkCacheDataListner);
            }
        }
        zookeeperClient.getZkClient().subscribeChildChanges(path, zkCacheKeyListner);
    }
}
