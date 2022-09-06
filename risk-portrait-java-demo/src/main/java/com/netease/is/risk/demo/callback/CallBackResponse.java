/*
 * @(#) CallBackResponse.java 2022-09-01
 *
 * Copyright 2022 NetEase.com, Inc. All rights reserved.
 */

package com.netease.is.risk.demo.callback;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yidun
 * @version 2022-09-01
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class CallBackResponse {
    private @Getter @Setter int code;
    private @Getter @Setter String msg = "";

    public CallBackResponse() {
        this(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg());
    }

    public CallBackResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
