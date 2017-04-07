package com.songsf.learn.FastJsonLearn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

/**
 * Created by songsf on 2017/1/19.
 */
public class TestMain {
    public static void main(String... args){
        VO vo = new VO();

        vo.setId(123);
        vo.setName("flym");

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(VO.class, "name","id");
        /*Assert.assertEquals("{\"name\":\"flym\"}", JSON.toJSONString(vo, filter));*/
        System.out.println(JSON.toJSONString(vo,filter));
    }
}
