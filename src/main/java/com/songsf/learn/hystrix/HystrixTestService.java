package com.songsf.learn.hystrix;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by songsf on 2017/2/28.
 */
@Service
public class HystrixTestService {
    public JSONObject Hello(){
        JSONObject response = new JSONObject();
        try {
            response = (new HystrixCommand<JSONObject>(HystrixCommandGroupKey.Factory.asKey("HelloCommand")){
                @Override
                protected JSONObject getFallback() {
                    JSONObject response = new JSONObject();
                    response.put("message","执行出错");
                    return response;
                }

                @Override
                protected JSONObject run() {
                    JSONObject response = new JSONObject();
                    System.out.println("hello world 服务");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //response = RestClient.get(null,"http://localhost:8080/api/v1.0/greeting/hello?name=qqq",null);
                    return response;
                }
            }).queue().get(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return response;
    }
}
