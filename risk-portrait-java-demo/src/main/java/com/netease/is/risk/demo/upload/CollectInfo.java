/*
 * @(#) CollectInfo.java 2022-04-21
 *
 * Copyright 2022 NetEase.com, Inc. All rights reserved.
 */

package com.netease.is.risk.demo.upload;

import lombok.Data;

/**
 * @author yidun
 * @version 2022-04-21
 */
@Data
public class CollectInfo {
    /**
     * 用户唯一标识，与易盾账号画像库匹配
     */
    private String account;
    /**
     * 手机号，与易盾风险库匹配。默认国内手机号，如有海外手机，需包含国家地区代码，格式为“+447410xxx186（+44即为国家码）”。如果需要加密，支持传入hash值，hash算法：md5(phone)
     */
    private String phone;
    /**
     * 接受消息的用户标识，私聊/评论回复场景使用，易盾可根据该id关联检测，辅助精准调优
     */
    private String receiveUid;
    /**
     * 用户IP地址，与易盾IP画像库匹配
     */
    private String ip;
    /**
     * 数据唯一标识，能够根据该值定位到该条数据，如对数据结果有异议，可以发送该值给客户经理查询
     */
    private String dataId;
    /**
     * 用户发表内容，建议对内容中JSON、表情符、HTML标签、UBB标签等做过滤，只传递纯文本，以减少误判概率
     */
    private String content;
    /**
     * 内容标题，适用于贴子、博客的文章标题等场景，建议抄送
     */
    private String title;
    /**
     * 发表时间，UNIX 时间戳(毫秒值)
     */
    private Long publishTime;
}
