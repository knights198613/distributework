package com.jiangwei.distributework.zookeeper;

import com.jiangwei.distributework.zookeeper.listenner.NodeListenner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by weijiang
 * Date: 2018/2/5
 * DESC:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring_cfg.xml"})
public class ZKclientTest {

    @Autowired
    private ZKclientUtils zKclientUtils;

    @Test
    public void testCreatePath() {
        zKclientUtils.startClient();
        String result = zKclientUtils.createPath("/test", null, 1);
        System.out.println(result);
        zKclientUtils.addWatcher(result);
        NodeListenner nodeListenner = new NodeListenner(result);
        zKclientUtils.addListenner(nodeListenner);
        zKclientUtils.deletePath(result);


    }
}
