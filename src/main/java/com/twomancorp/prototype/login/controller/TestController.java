package com.twomancorp.prototype.login.controller;

import com.twomancorp.prototype.login.model.TestModel;
import com.twomancorp.prototype.login.repository.TestRepository;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by chuck on 9/18/15.
 */
@RestController
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public HttpEntity<String> test(){
        return new HttpEntity<String>("test success");
    }

    @RequestMapping(value="/db", method = RequestMethod.GET)
    public ResponseEntity<Iterable<TestModel>> db(){
        Iterable<TestModel> iterable =  testRepository.findAll();
        return new ResponseEntity<>(iterable, HttpStatus.OK);
    }

    @RequestMapping(value="/testPost", method = RequestMethod.POST)
    public ResponseEntity<String> testPost(@RequestBody String name){
        System.out.println(name);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, null);
        Stat stat = zooKeeper.exists("/test", false);
        System.out.println(stat);
        Stat stat1 = null;
        byte[] bytes = zooKeeper.getData("/test",false, stat1);
        System.out.println(new String(bytes, Charset.forName("utf-8")));
    }
}
