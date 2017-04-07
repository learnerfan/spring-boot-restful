package com.songsf.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by songsf on 2016/12/31.
 */
@SpringBootApplication
@EnableCaching
@RestController
@EnableAsync
@EnableScheduling
@EnableAutoConfiguration
public class LearnApp {
    public static void main(String[] args){
        SpringApplication.run(LearnApp.class,args);
    }
}
