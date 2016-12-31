package com.sonsf.learn.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songsf on 2016/12/31.
 */
@RequestMapping(value = "/api/v1.0/greeting")
@RestController
public class GreetingController {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ApiOperation(value = "问候接口",notes = "这个接口通过输入一个名字，返回hell+name信息，内容放在message对象中")
    public JSONObject greet(@ApiParam(value = "名字")@RequestParam()String name){
        JSONObject response = new JSONObject();
        response.put("message","hello"+"   "+name);
        return response;
    }
}
