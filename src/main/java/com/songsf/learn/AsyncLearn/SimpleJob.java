package com.songsf.learn.AsyncLearn;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by songsf on 2017/1/21.
 */
@Component
public class SimpleJob {
    public static Random random = new Random();

    public void doJobOne() throws InterruptedException {
        System.out.println("开始做任务1");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        System.out.println("完成job1，耗时 ： "+(end - start) +" 毫秒");
    }

    public void doJobTwo() throws InterruptedException {
        System.out.println("开始做任务2");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        System.out.println("完成job2，耗时 ： "+(end - start) +" 毫秒");
    }

    public void doJobThree() throws InterruptedException {
        System.out.println("开始做任务3");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        System.out.println("完成job3，耗时 ： "+(end - start) +" 毫秒");
    }
}
