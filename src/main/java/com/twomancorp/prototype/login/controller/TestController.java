package com.twomancorp.prototype.login.controller;

import com.twomancorp.prototype.login.model.TestModel;
import com.twomancorp.prototype.login.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
