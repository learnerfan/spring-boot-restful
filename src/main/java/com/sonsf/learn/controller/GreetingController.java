package com.sonsf.learn.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songsf on 2016/12/31.
 */
@RequestMapping("/api/v1.0/greeting")
@RestController
public class GreetingController {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ApiOperation(value = "问候接口",notes = "hello")
    public JSONObject greet(@RequestParam()String name){
        JSONObject response = new JSONObject();
        response.put("message","hello"+"   "+name);
        return response;
    }
}
