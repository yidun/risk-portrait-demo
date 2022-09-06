/*
 * @(#) CallBackDemoController.java 2022-09-06
 *
 * Copyright 2022 NetEase.com, Inc. All rights reserved.
 */

package com.netease.is.risk.demo.callback;

import com.google.common.collect.Maps;
import com.netease.is.risk.demo.utils.SignatureUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Objects;

/**
 * 易盾采用push方式将结果推送，接入方需仿照该例子开发一个API，并将地址告知易盾方
 * @author yidun
 * @version 2022-09-06
 */
@RestController
@RequestMapping("/v1/callback/")
public class CallBackDemoController {

    private static final String BUSINESS_ID = "businessId";
    private static final String CALLBACK_DATA = "callbackData";
    private static final String SECRETID = "secretId";
    private static final String SIGNATURE = "signature";
    //易盾方分配的secretKey
    private static final String SECRET_KEY = "your_secret_key";


    @RequestMapping("risk/get")
    public CallBackResponse riskGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (Objects.isNull(request)) {
                System.out.println("请求参数为空，抛出异常" );
            }
            CallBackRequest callBackRequest = getRequest(request);
            if (StringUtils.isNotEmpty(callBackRequest.getCallbackData())) {
                System.out.println("数据接收成功");
                //添加对数据的使用逻辑
                return new CallBackResponse();
            } else {
                System.out.println("数据接收失败");
                return new CallBackResponse(ResultStatus.ILLEGAL_ARGUMENT.getCode(),ResultStatus.ILLEGAL_ARGUMENT.getMsg());
            }
        } catch (Exception e) {
            System.out.println("数据接收失败");
            return new CallBackResponse(ResultStatus.ERROR.getCode(), ResultStatus.ERROR.getMsg());
        }
    }

    private CallBackRequest getRequest(HttpServletRequest request) throws UnsupportedEncodingException {
        String businessKey = request.getParameter(BUSINESS_ID);
        String signature = request.getParameter(SIGNATURE);
        String callBackData = request.getParameter(CALLBACK_DATA);
        String secretId = request.getParameter(SECRETID);
        if (StringUtils.isEmpty(businessKey)) {
            System.out.println("业务ID参数为空，抛出异常" );
        }
        Map<String, String> params = Maps.newHashMap();
        params.put("secretId", secretId);
        params.put("businessId", businessKey);
        params.put("callbackData", callBackData);
        //计算签名
        String sign = SignatureUtils.genSignature(SECRET_KEY, params);
        if (!sign.equals(signature)) {
            System.out.println("签名错误，抛出异常");
        }
        CallBackRequest callBackRequest = new CallBackRequest();
        callBackRequest.setBusinessId(request.getParameter(BUSINESS_ID));
        callBackRequest.setSecretId(request.getParameter(SECRETID));
        callBackRequest.setCallbackData(request.getParameter(CALLBACK_DATA));
        return callBackRequest;
    }
}
