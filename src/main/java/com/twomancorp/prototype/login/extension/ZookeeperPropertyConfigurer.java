package com.twomancorp.prototype.login.extension;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.BytesPushThroughSerializer;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by ssge on 1/27/16.
 */
public class ZookeeperPropertyConfigurer extends PropertyPlaceholderConfigurer implements Watcher{
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) {
        Enumeration<String> enumeration = (Enumeration<String>) props.propertyNames();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
//        try {
//            ZooKeeper zookeeper = new ZooKeeper("127.0.0.1:2181",5000,this);
//            Stat stat = null;
//            zookeeper.getData("/test", this, stat);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (KeeperException e) {
//            e.printStackTrace();
//        }
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 2000, 30000, new BytesPushThroughSerializer());
        System.out.println(new String(zkClient.<byte[]>readData("/test"), Charset.forName("utf-8")));
        zkClient.subscribeDataChanges("/test", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println(new String((byte[]) data));
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {

            }
        });
        props.setProperty("mongo.dbname", new String(zkClient.<byte[]>readData("/omnilogin/db/name")));
        super.processProperties(beanFactoryToProcess, props);

    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.getPath());
    }
}
