package com.songsf.learn.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by songsf on 2017/3/15.
 */
@Component
@Service
public class FileTest {

    public String getCurrentPath(){
        String realPath = FileTest.class.getClassLoader().getResource("")
                .getFile();
        java.io.File file = new java.io.File(realPath);
        realPath = file.getAbsolutePath();
        try {
            realPath = java.net.URLDecoder.decode(realPath, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return realPath;
    }
}
