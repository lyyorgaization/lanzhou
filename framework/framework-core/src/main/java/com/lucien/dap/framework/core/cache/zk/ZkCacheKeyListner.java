package com.lucien.dap.framework.core.cache.zk;

import com.lucien.dap.framework.core.zk.ZookeeperClient;
import org.I0Itec.zkclient.IZkChildListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZkCacheKeyListner implements IZkChildListener {
    private ZkCacheDataListner zkCacheDataListner;
    private ZookeeperClient zookeeperClient;

    @Autowired
    public void setZkCacheDataListner(ZkCacheDataListner zkCacheDataListner) {
        this.zkCacheDataListner = zkCacheDataListner;
    }

    @Autowired
    public void setZookeeperClient(ZookeeperClient zookeeperClient) {
        this.zookeeperClient = zookeeperClient;
    }

    @Override
    public void handleChildChange(String path, List<String> list) {
        for (String str : list) {
            zookeeperClient.getZkClient().subscribeDataChanges(path + "/" + str, zkCacheDataListner);
        }
    }
}
