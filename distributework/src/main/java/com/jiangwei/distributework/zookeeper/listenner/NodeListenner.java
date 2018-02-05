package com.jiangwei.distributework.zookeeper.listenner;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;

/**
 * Created by weijiang
 * Date: 2018/2/5
 * DESC:
 */
public class NodeListenner implements CuratorListener {

    private String path;

    public NodeListenner(String path) {
        this.path = path;
    }

    public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
        if(null != event && event.getPath().equals(path)) {
            System.out.println("listenner EventType="+event.getType().name());
        }
    }
}
