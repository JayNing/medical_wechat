package com.zx.wx.service.impl;

import com.zx.common.config.CommonConfig;
import com.zx.common.entity.ReturnMsg;
import com.zx.common.util.CertHttpUtil;
import com.zx.common.util.StringUtil;
import com.zx.common.util.Validators;
import com.zx.contants.Contants;
import com.zx.wx.http.entity.UserInfo;
import com.zx.wx.service.WechatPayService;
import com.zx.wx.util.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service("wechatPayService")
public class WechatPayServiceImpl implements WechatPayService {

    private static Logger logger = LoggerFactory.getLogger(WechatPayServiceImpl.class);

    @Autowired
    private CommonConfig commonConfig;

    @Override
    public ReturnMsg payMoney(UserInfo weiXinUserInfo, String total_fee, String body, ReturnMsg returnMsg, String seqName) throws Exception {
        ReturnMsg msg = new ReturnMsg();
        if (weiXinUserInfo != null){
            if(StringUtil.notEmpty(total_fee)){
                if(Validators.isNumeric(total_fee)) {
                    if (!(Integer.valueOf(total_fee) == 0)) {
                        if(StringUtil.notEmpty(body)){
                            //拼接统一下单地址参数
                            Map<String, String> paraMap = new HashMap<String, String>();

                            String numStr = UUID.randomUUID().toString().replace("-","").substring(0,4);
                            String out_trade_no = seqName + numStr;

                            String openId = weiXinUserInfo.getOpenid();
                            String appid = commonConfig.getWechatAppid();
                            String mch_id = commonConfig.getWechatMerchantId();
                            String url = commonConfig.getWechatServerName();
                            String paternerKey = commonConfig.getWechatMerchantKey();
                            String notify_url = url + "/pay/weixin_notify";
                            InetAddress addr = InetAddress.getLocalHost();
                            String spbill_create_ip = addr.getHostAddress().toString();//获得本机IP　　

                            paraMap.put("appid", appid);
                            paraMap.put("body", body);
                            paraMap.put("mch_id", mch_id);
                            paraMap.put("nonce_str", WXPayUtil.generateNonceStr());
                            paraMap.put("notify_url",notify_url);// 此路径是微信服务器调用支付结果通知路径随意写
                            paraMap.put("openid", openId);
                            paraMap.put("out_trade_no", out_trade_no);//订单号
                            paraMap.put("spbill_create_ip",spbill_create_ip);
                            paraMap.put("total_fee","1");
                            paraMap.put("trade_type", "JSAPI");
                            String sign = WXPayUtil.generateSignature(paraMap, paternerKey);
                            paraMap.put("sign", sign);
                            String xml = WXPayUtil.mapToXml(paraMap);//将所有参数(map)转xml格式

                            // 统一下单 https://api.mch.weixin.qq.com/pay/unifiedorder
                            String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

                            String xmlStr = null;//HttpRequest.sendPost(unifiedorder_url, xml);//发送post请求"统一下单接口"返回预支付id:prepay_i
//                            xmlStr = HttpUtil.postPay(unifiedorder_url,xml);
                            xmlStr = CertHttpUtil.postData(unifiedorder_url,xml,mch_id,"/home/ningjianjian/apiclient_cert.p12");
                            logger.info("pay xmlStr : " + xmlStr);

                            //以下内容是返回前端页面的json数据
                            String prepay_id = "";//预支付id
                            if (xmlStr.indexOf("SUCCESS") != -1) {
                                Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);
                                prepay_id = (String) map.get("prepay_id");
                            }
                            Map<String, String> payMap = new HashMap<String, String>();
                            payMap.put("appId", appid);
                            payMap.put("timeStamp", WXPayUtil.getCurrentTimestamp()+"");
                            payMap.put("nonceStr", WXPayUtil.generateNonceStr());
                            payMap.put("signType", "MD5");
                            payMap.put("package", "prepay_id=" + prepay_id);
                            String paySign = WXPayUtil.generateSignature(payMap, paternerKey);
                            payMap.put("paySign", paySign);

                            msg.setData(payMap);
                            msg.setErrorCode(Contants.SUCCESS);

                        }else {
                            msg.addErrorMsg("商品详情不能为空!");
                        }
                    } else{
                        msg.addErrorMsg("金额不能为0");
                    }
                }else{
                    msg.addErrorMsg("金额必须为数字");
                }
            }else {
                msg.addErrorMsg("金额不能不空!");
            }
        }else{
            msg.addErrorMsg("当前账户不存在!");
        }
        return msg;
    }

}