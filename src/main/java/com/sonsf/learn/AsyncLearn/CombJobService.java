package com.sonsf.learn.AsyncLearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * Created by songsf on 2017/1/21.
 */
@Component
public class CombJobService {
    @Autowired
    private SimpleJob simpleJob;
    @Autowired
    private AsyncJob asyncJob;
    public void noAsync() throws InterruptedException {
        long start = System.currentTimeMillis();
        simpleJob.doJobOne();
        simpleJob.doJobTwo();
        simpleJob.doJobThree();
        long end = System.currentTimeMillis();
        System.out.println("共消耗 ： "+(end-start) +" 毫秒");
    }

    public void asynocTest() throws InterruptedException {
        long start = System.currentTimeMillis();

        Future<String> job1 = asyncJob.doJobOne();
        Future<String> job2 = asyncJob.doJobTwo();
        Future<String> job3 = asyncJob.doJobThree();
        while (true){
            if (job1.isDone() && job2.isDone() && job3.isDone()){
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("async共消耗 ： "+(end-start) +" 毫秒");
    }

}
