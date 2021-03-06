package com.jiangwei.distributework.zookeeper;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.RetryUntilElapsed;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by weijiang
 * Date: 2018/2/2
 * DESC:
 */
public class ZKclientUtils {
    public static final Logger LOGGER = LoggerFactory.getLogger(ZKclientUtils.class);

    /**客户端失联最大超时等待时间*/
    private int maxElapsedTimeMs;
    /**下次重试间隔时间*/
    private int sleepMsBetweenRetries;
    /**集群ip列表*/
    private String hostList;
    private int default_MaxElapsedTimeMs = 5000;
    private int default_SleepMsBetweenRetries = 500;
    private int default_SessionTimeOutMs = 60 * 1000;
    private int default_connectionTimeOutMs = 60 * 1000;
    private CuratorFramework curatorFramework;

    public ZKclientUtils(String hostList) {
        this.hostList = hostList;
        this.curatorFramework = createClient(hostList, 0, 0,0, 0);
    }

    public ZKclientUtils( String hostList, int maxElapsedTimeMs, int sleepMsBetweenRetries) {
        this.maxElapsedTimeMs = maxElapsedTimeMs;
        this.sleepMsBetweenRetries = sleepMsBetweenRetries;
        this.hostList = hostList;
        this.curatorFramework = createClient(hostList,
                default_SessionTimeOutMs,
                default_connectionTimeOutMs,
                maxElapsedTimeMs,
                sleepMsBetweenRetries);
    }

    /**
     * 创建zk连接客户端
     * @param hostList
     * @return
     */
    private CuratorFramework createClient(String hostList, int sessionTimeOutMs, int connectionTimeOutMs,
                                          int maxElapsedTimeMs, int sleepMsBetweenRetries) {
        CuratorFramework curatorFramework = null;
        if(StringUtils.isNotBlank(hostList)) {
            curatorFramework = CuratorFrameworkFactory
                    .newClient(hostList,
                            sessionTimeOutMs == default_SessionTimeOutMs ? default_SessionTimeOutMs:sessionTimeOutMs,
                            connectionTimeOutMs == default_connectionTimeOutMs ? default_connectionTimeOutMs:connectionTimeOutMs,
                            new RetryUntilElapsed(maxElapsedTimeMs == 0 ? default_MaxElapsedTimeMs:maxElapsedTimeMs,
                                    sleepMsBetweenRetries == 0 ? default_SleepMsBetweenRetries:sleepMsBetweenRetries));
        }
        return curatorFramework;
    }

    /**
     * 创建znode节点
     * @param path
     * @param data
     * @param createMode  0:持久znode 1:临时znode 2:持久有序znode 3:临时有序znode  >3:持久znode
     * @return
     */
    public String createPath(String path, byte[] data, int createMode) {
        String result = null;
        CreateMode cm;
        switch (createMode) {
            case 0: cm = CreateMode.PERSISTENT; break;
            case 1: cm = CreateMode.EPHEMERAL; break;
            case 2: cm = CreateMode.PERSISTENT_SEQUENTIAL; break;
            case 3: cm = CreateMode.EPHEMERAL_SEQUENTIAL; break;
            default: cm = CreateMode.PERSISTENT;
        }
        try {
            if(StringUtils.isNotBlank(path) && ArrayUtils.isNotEmpty(data)) {
                result = curatorFramework
                        .create()
                        .withMode(cm)
                        .forPath(path, data);
            }else if(StringUtils.isNotBlank(path) && ArrayUtils.isEmpty(data)) {
                result = curatorFramework
                        .create()
                        .withMode(cm)
                        .forPath(path);
            }
        } catch (Exception e) {
            LOGGER.error("创建znode节点失败. path={}, byte[]={}, createMode={}.", path, data, createMode, e);
        }
        return result;
    }

    public void deletePath(String path) {
        try {
            curatorFramework.delete().forPath(path);
        } catch (Exception e) {
            LOGGER.error("删除znode节点失败. path={}", path, e);
        }
    }

    /**
     * 对执行节点创建监控点
     * @param path
     */
    public void addWatcher(String path) {
        try {
            this.curatorFramework.getData().watched().forPath(path);
        }catch (Exception e) {
            LOGGER.error("创建监控点失败. path={}", path, e);
        }
    }

    /**
     * 添加事件监听器
     * @param listener
     */
    public void addListenner(CuratorListener listener) {
        curatorFramework.getCuratorListenable().addListener(listener);
    }

    /**
     * 开启客户端
     */
    public void startClient() {
        if(null != curatorFramework) {
            curatorFramework.start();
        }
    }

    /**
     * 关闭客户端
     */
    public void closeClient() {
        if(null != curatorFramework) {
            curatorFramework.close();
        }
    }

    public int getMaxElapsedTimeMs() {
        return maxElapsedTimeMs;
    }

    public void setMaxElapsedTimeMs(int maxElapsedTimeMs) {
        this.maxElapsedTimeMs = maxElapsedTimeMs;
    }

    public int getSleepMsBetweenRetries() {
        return sleepMsBetweenRetries;
    }

    public void setSleepMsBetweenRetries(int sleepMsBetweenRetries) {
        this.sleepMsBetweenRetries = sleepMsBetweenRetries;
    }

    public String getHostList() {
        return hostList;
    }

    public void setHostList(String hostList) {
        this.hostList = hostList;
    }

    public CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }

    public void setCuratorFramework(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }
}
