package com.songsf.learn.AsyncLearn;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by songsf on 2017/1/21.
 */
@Component
public class AsyncJob {
    public static Random random = new Random();

    @Async
    public Future<String> doJobOne() throws InterruptedException {
        System.out.println("开始做Async任务1");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        System.out.println("完成Asyncjob1，耗时 ： "+(end - start) +" 毫秒");
        return new AsyncResult<>("完成AsyncJob1");
    }
    @Async
    public Future<String> doJobTwo() throws InterruptedException {
        System.out.println("开始做Async任务2");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        System.out.println("完成Asyncjob2，耗时 ： "+(end - start) +" 毫秒");
        return new AsyncResult<>("完成AsyncJob2");
    }
    @Async
    public Future<String> doJobThree() throws InterruptedException {
        System.out.println("开始做Async任务3");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        System.out.println("完成Asyncjob3，耗时 ： "+(end - start) +" 毫秒");
        return new AsyncResult<>("完成AsyncJob3");
    }
}
