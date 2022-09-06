/*
 * @(#) AccountRiskAPIDemo.java 2022-09-06
 *
 * Copyright 2022 NetEase.com, Inc. All rights reserved.
 */

package com.netease.is.risk.demo.upload;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netease.is.risk.demo.utils.HttpClient4Utils;
import com.netease.is.risk.demo.utils.SignatureUtils;
import org.apache.http.client.HttpClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 数据上报 接口调用示例,该示例依赖以下jar包：
 * 1. httpclient，用于发送http请求,详细见HttpClient4Utils.java
 * 2. commons-codec，使用md5算法生成签名信息，详细见SignatureUtils.java
 * 3. fastjson，用于做json解析
 *
 * @author yidun
 */
public class DataUploadAPIDemo {
    /**
     * 产品密钥ID，产品标识
     */
    private final static String SECRET_ID = "your_secret_id";
    /**
     * 产品私有密钥，服务端生成签名信息使用，请严格保管，避免泄露
     */
    private final static String SECRET_KEY = "your_secret_key";
    /**
     * 业务ID，易盾根据产品业务特点分配
     */
    private final static String BUSINESS_ID = "your_business_id";
    /**
     * 接口地址
     */
    private final static String API_URL = "https://rp.dun.163.com/v1/profile/user/async/check";
    /**
     * 实例化HttpClient，发送http请求使用，可根据需要自行调参
     */
    private static HttpClient httpClient = HttpClient4Utils.createHttpClient(100, 20, 5000, 2000, 2000);


    public static void main(String[] args) throws Exception {
        Map<String, String> params = new HashMap<>(10);
        // 1.设置公共参数
        params.put("secretId", SECRET_ID);
        params.put("businessId", BUSINESS_ID);
        params.put("version", "v1");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("nonce", UUID.randomUUID().toString().replace("-", ""));
        // params.put("signatureMethod", "SM3"); // 可选参数，可选值为MD5、SHA1、SHA256、SM3。如果不传该字段，默认采用MD5算法生成签名。

        // 2.设置私有参数
        List<CollectInfo> texts = new ArrayList<>();
        CollectInfo collectInfo = new CollectInfo();
        collectInfo.setAccount("123456");
        collectInfo.setTitle("暴力");
        collectInfo.setContent("暴力是一种导致或可能导致身体、性或心理伤害的明确的或象征性的行为。");
        collectInfo.setDataId("123456");
        collectInfo.setIp("123.23.23.23");
        collectInfo.setPhone("12312312312");
        collectInfo.setPublishTime(System.currentTimeMillis());
        collectInfo.setReceiveUid("456789");
        texts.add(collectInfo);
        params.put("texts", JSON.toJSONString(texts));

        // 3.生成签名信息
        String signature = SignatureUtils.genSignature(SECRET_KEY, params);
        params.put("signature", signature);

        // 4.发送HTTP请求，这里使用的是HttpClient工具包，产品可自行选择自己熟悉的工具包发送请求
        String response = HttpClient4Utils.sendPost(httpClient, API_URL, params);

        //5.解析报文返回
        JSONObject jsonObject = JSON.parseObject(response);
        System.out.printf("response=%s%n", jsonObject);
        int code = jsonObject.getIntValue("code");
        if (code == 200) {
            JSONObject resultObject = jsonObject.getJSONObject("data");
            boolean status = resultObject.getBoolean("success");
            String taskId = resultObject.getString("taskId");
            if (status) {
                System.out.printf("taskId=%s，数据上传成功%n", taskId);
            } else {
                System.out.printf("taskId=%s，数据上传失败%n", taskId);
            }
        } else {
            System.out.printf("数据上传失败，详情见接口文档说明，response=%s%n", jsonObject);
        }
    }
}
