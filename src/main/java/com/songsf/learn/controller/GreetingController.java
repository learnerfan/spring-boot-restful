package com.songsf.learn.controller;

import com.alibaba.fastjson.JSONObject;
import com.songsf.learn.domain.p.User;
import com.songsf.learn.domain.p.UserRepository;
import com.songsf.learn.domain.s.Message;
import com.songsf.learn.domain.s.MessageRepository;
import com.songsf.learn.hystrix.HystrixTestService;
import com.songsf.learn.service.CacheService;
import com.songsf.learn.service.EcacheService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by songsf on 2016/12/31.
 */
@RequestMapping(value = "/api/v1.0/greeting")
@RestController
public class GreetingController {
    private final EcacheService ecacheService = new EcacheService();

    @Autowired
    private HystrixTestService hystrixTestService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ApiOperation(value = "问候接口",notes = "这个接口通过输入一个名字，返回hell+name信息，内容放在message对象中")
    public JSONObject greet(@ApiParam(value = "名字")@RequestParam()String name){
        JSONObject response = new JSONObject();
        response.put("message","hello"+"   "+name);
        return response;
    }
    @RequestMapping(value = "/cacheTest",method = RequestMethod.GET)
    @ApiOperation(value = "缓存测试接口",notes = "这个接口通过输入一个名字，返回hell+name信息，名字放在缓存中")
    public JSONObject getCacheTest(@ApiParam(value = "名字")@RequestParam()String name){
        JSONObject response = new JSONObject();
        CacheService cacheService = CacheService.getIns();
        String message = (String) cacheService.getExpire1Cache(name);
        if (message != null){
            response.put("message","从缓存中获取"+message);
            return response;
        }else {
            response.put("message","生成信息"+"hello"+name+",同时放到缓存中");
            cacheService.putExpire1Cache(name,"hello"+name);
            return response;
        }
    }
    @RequestMapping(value = "/ecacheTest",method = RequestMethod.GET)
    @ApiOperation(value = "spring boot 注解缓存接口",notes = "")
    public String ecacheTest(@RequestParam()String name){

        return ecacheService.getMessage(name);
    }
    @RequestMapping(value = "/hystrixTest",method = RequestMethod.GET)
    public JSONObject testHystrix(){
        return hystrixTestService.Hello();
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void cornTest(){
        System.out.println(new Date());
    }
    @RequestMapping(value = "/primaryDataSource",method = RequestMethod.GET)
    @ApiOperation(value = "主数据源mysql测试")
    public JSONObject getPrimaryData(){
        JSONObject response = new JSONObject();
        response.put("data",userRepository.findAll());
        return response;
    }
    @RequestMapping(value = "/secondDataSource",method = RequestMethod.GET)
    public JSONObject getSecondData(){
        JSONObject response = new JSONObject();
        response.put("data",messageRepository.findAll());

        return response;
    }
    @RequestMapping(value = "/secondDataSource",method = RequestMethod.POST)
    public JSONObject save(){
        JSONObject response = new JSONObject();
        messageRepository.save(new Message(1L,"猪八戒","悟空的师弟"));
        return response;
    }
}
