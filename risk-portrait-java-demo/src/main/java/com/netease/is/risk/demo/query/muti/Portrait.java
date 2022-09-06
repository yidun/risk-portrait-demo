/*
 * @(#) Portrait.java 2022-06-09
 *
 * Copyright 2022 NetEase.com, Inc. All rights reserved.
 */

package com.netease.is.risk.demo.query.muti;

import lombok.Data;

/**
 * @author yidun
 * @version 2022-06-09
 */
@Data
public class Portrait {
    /**
     * 手机号信息
     */
    private QueryRiskInfo queryPhoneInfo;
    /**
     * 账号信息
     */
    private QueryRiskInfo queryAccountInfo;
    /**
     * ip信息
     */
    private QueryRiskInfo queryIpInfo;
}
