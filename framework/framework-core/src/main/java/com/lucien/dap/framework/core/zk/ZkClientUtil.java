package com.lucien.dap.framework.core.zk;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.Set;

public class ZkClientUtil extends ZkClient {
    public ZkClientUtil(String zkServers, int connectionTimeout) {
        super(zkServers, connectionTimeout);
    }

    /**
     * 引出获取监听器的方法
     */
    public Set<IZkDataListener> getDataListener(String path) {
        return super.getDataListener(path);
    }
}
