package com.sonsf.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by songsf on 2016/12/31.
 */
@SpringBootApplication
public class LearnApp extends WebMvcConfigurerAdapter {
    public static void main(String[] args){
        SpringApplication.run(LearnApp.class,args);
    }
}
