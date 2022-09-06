/*
 * @(#) CallBackRequest.java 2022-09-02
 *
 * Copyright 2022 NetEase.com, Inc. All rights reserved.
 */

package com.netease.is.risk.demo.callback;

import lombok.Data;

/**
 * @author zouwenzhuo01
 * @version 2022-09-02
 */
@Data
public class CallBackRequest {
    private String secretId;
    private String signature;
    private String businessId;
    private String callbackData;
}
