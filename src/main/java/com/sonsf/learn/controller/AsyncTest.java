package com.sonsf.learn.controller;

import com.sonsf.learn.AsyncLearn.CombJobService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songsf on 2017/1/21.
 */
@RequestMapping(value = "/api/v1.0/asyncTest")
@RestController
public class AsyncTest {
    @Autowired
    private CombJobService combJobService;

    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    @ApiOperation(value = "测试异步调用",notes = "")
    public void asyncTest(){
        try {
            combJobService.noAsync();
            combJobService.asynocTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
