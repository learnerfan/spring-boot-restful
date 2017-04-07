package com.songsf.learn.controller;

import com.alibaba.fastjson.JSONObject;
import com.songsf.learn.service.FileTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songsf on 2017/3/15.
 */
@RequestMapping(value = "/api/v1.0/file")
@RestController
public class FileTestController {
    @Autowired
    private FileTest fileTest;

    @RequestMapping(value = "/currentPath",method = RequestMethod.GET)
    public JSONObject getCurrentPath(){
        JSONObject response = new JSONObject();
        String path = fileTest.getCurrentPath();
        response.put("path",path);
        return response;
    }
}
