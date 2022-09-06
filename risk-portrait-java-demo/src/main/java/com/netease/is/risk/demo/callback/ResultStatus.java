/*
 * @(#) ResultStatus.java 2018-08-09
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package com.netease.is.risk.demo.callback;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yidun
 * @version 2018-08-09
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public enum ResultStatus{
    /**
     * 200 成功 使用约定的成功状态码
     */
    SUCCESS(200, "成功"),

    /**
     * 500 异常 使用约定的失败状态码
     */
    ERROR(500, "服务异常"),

    /**
     * 400 参数错误
     */
    ILLEGAL_ARGUMENT(400, "参数错误");

    /**
     * 返回状态码,{@link ResultStatus#SUCCESS SUCCESS(200)}表示成功
     */
    private int code;

    /**
     * 返回描述信息
     */
    private String msg;
}
