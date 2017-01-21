package com.sonsf.learn.FastJsonLearn;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by songsf on 2017/1/19.
 */
public class VO {

    private int    id;
    private String name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
