package com.twomancorp.prototype.login.extension;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
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
        try {
            ZooKeeper zookeeper = new ZooKeeper("127.0.0.1:2181",5000,this);
            Stat stat = null;
            zookeeper.getData("/test", this, stat);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
        super.processProperties(beanFactoryToProcess, props);

    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.getPath());

    }
}
