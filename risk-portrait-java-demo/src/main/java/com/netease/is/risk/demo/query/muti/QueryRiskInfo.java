/*
 * @(#) QueryRiskInfo.java 2022-08-01
 *
 * Copyright 2022 NetEase.com, Inc. All rights reserved.
 */

package com.netease.is.risk.demo.query.muti;

import lombok.Data;

import java.util.List;

/**
 * @author yidun
 * @version 2022-08-01
 */
@Data
public class QueryRiskInfo {
    private String businessKey;
    private List<String> accounts;
    private List<String> phones;
    private List<String> ips;
}
