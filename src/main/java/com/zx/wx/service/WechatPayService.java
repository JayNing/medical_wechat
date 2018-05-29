package com.zx.wx.service;

import com.zx.common.entity.ReturnMsg;
import com.zx.wx.http.entity.UserInfo;

import java.net.UnknownHostException;

public interface WechatPayService {
    ReturnMsg payMoney(UserInfo weiXinUserInfo, String total_fee, String body, ReturnMsg returnMsg, String seqName) throws Exception;
}