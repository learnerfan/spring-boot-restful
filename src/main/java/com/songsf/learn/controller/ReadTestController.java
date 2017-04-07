package com.songsf.learn.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;

/**
 * Created by songsf on 2017/2/3.
 */
@Controller
public class ReadTestController {
    @RequestMapping(value = "/api/v1.0/read",method = RequestMethod.GET)
    @ApiOperation(value = "读文件接口",notes = "")
    public void readTest(){
        File file = new File(this.getClass().getResource("test.txt").getFile());
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String readline = null;
            if ((readline = bf.readLine()) != null){
                System.out.println(readline);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
