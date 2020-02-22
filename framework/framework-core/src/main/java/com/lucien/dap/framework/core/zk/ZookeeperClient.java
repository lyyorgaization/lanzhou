package com.lucien.dap.framework.core.zk;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.I0Itec.zkclient.exception.ZkTimeoutException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class ZookeeperClient {
    private volatile ZkClient zkClient = null;
    @Value("${zk.server}")
    private String servers;


    /**
     * zkClient默认获取连接超时限制
     */
    private static final int DEFAULT_CONNECTION_TIMEOUT = 20 * 1000;

    @PostConstruct
    protected void init() {
        try {
            zkClient = new ZkClientUtil(servers, DEFAULT_CONNECTION_TIMEOUT);
            log.info("Create zookeeper's connection successfully!");
        } catch (ZkTimeoutException e) {
            log.error("Connect zookeeper error", e);
        }
    }

    public ZkClient getZkClient() {
        if (zkClient == null) {
            init();
        }
        return zkClient;
    }

    /**
     * 创建永久性结点
     *
     * @param path
     */
    public void createPersistent(String path) {
        try {
            zkClient.createPersistent(path, true);
        } catch (ZkNodeExistsException e) {
            log.error("ZkNode exists", e);
        }
    }

    /**
     * 创建临时结点
     *
     * @param path
     */
    public void createEphemeral(String path) {
        try {
            zkClient.createEphemeral(path);
        } catch (ZkNodeExistsException e) {
        }
    }

    /**
     * 结点中写入数据
     *
     * @param keyPath
     * @param value
     * @return
     * @throws Exception
     */
    public boolean writeData(String keyPath, Object value) throws Exception {
        boolean flag = false;
        try {
            if (!zkClient.exists(keyPath)) {
                zkClient.createPersistent(keyPath, true);
            }
            zkClient.writeData(keyPath, value);
            flag = true;
        } catch (Exception e) {
            throw e;
        }
        return flag;
    }
}
