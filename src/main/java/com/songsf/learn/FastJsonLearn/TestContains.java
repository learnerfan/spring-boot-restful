package com.songsf.learn.FastJsonLearn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by songsf on 2017/3/7.
 */
public class TestContains {
    public static void main(String[] args){
        for (int i = 0 ; i < 10 ; i++){
            for (int j = 10 ; j < 20 ; j ++){
                if (j % 2 == 0){
                    System.out.println(i+"---"+j);
                    break;
                }
            }
        }
    }

    public static void test1(){
        String str = "{\n" +
                "\t\"isOnlySave\" : true,\n" +
                "\t\"customerId\" : 402,\n" +
                "\t\"customerName\" : \"徐海洋\",\n" +
                "\t\"customerType\" : \"医院代理商\",\n" +
                "\t\"drugId\" : \"D03\",\n" +
                "\t\"drugName\" : \"赛倍畅\",\n" +
                "\t\"startTime\" : 201703,\n" +
                "\t\"endTime\" : 201708,\n" +
                "\t\"timeDesc\" : \"2017年3月~2017年8月\",\n" +
                "\t\"hosps\" : [{\n" +
                "\t\t\t\"targetTotal\" : 20,\n" +
                "\t\t\t\"hospitalId\" : \"1480278102643\",\n" +
                "\t\t\t\"hospitalName\" : \"啊啊啊啊啊\",\n" +
                "\t\t\t\"endAppointMon\" : \"2017年3月\",\n" +
                "\t\t\t\"sales\" : [{\n" +
                "\t\t\t\t\t\"month\" : \"3月\",\n" +
                "\t\t\t\t\t\"yearMon\" : 201703,\n" +
                "\t\t\t\t\t\"plan\" : 0,\n" +
                "\t\t\t\t\t\"target\" : 0\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"month\" : \"4月\",\n" +
                "\t\t\t\t\t\"yearMon\" : 201704,\n" +
                "\t\t\t\t\t\"plan\" : 0,\n" +
                "\t\t\t\t\t\"target\" : \"10\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"month\" : \"5月\",\n" +
                "\t\t\t\t\t\"yearMon\" : 201705,\n" +
                "\t\t\t\t\t\"plan\" : 0,\n" +
                "\t\t\t\t\t\"target\" : 0\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"month\" : \"6月\",\n" +
                "\t\t\t\t\t\"yearMon\" : 201706,\n" +
                "\t\t\t\t\t\"plan\" : 0,\n" +
                "\t\t\t\t\t\"target\" : \"10\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"month\" : \"7月\",\n" +
                "\t\t\t\t\t\"yearMon\" : 201707,\n" +
                "\t\t\t\t\t\"plan\" : 0,\n" +
                "\t\t\t\t\t\"target\" : 0\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"month\" : \"8月\",\n" +
                "\t\t\t\t\t\"yearMon\" : 201708,\n" +
                "\t\t\t\t\t\"plan\" : 0,\n" +
                "\t\t\t\t\t\"target\" : 0\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"planTotal\" : 0\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}\n";
        JSONObject data = JSON.parseObject(str);
        JSONArray hops = data.getJSONArray("hosps");
        for (int i = 0 ; i < hops.size() ; i++){
            JSONObject hosObj = hops.getJSONObject(i);
            System.out.println(hosObj.containsValue("1480278102643"));
        }
    }
}
