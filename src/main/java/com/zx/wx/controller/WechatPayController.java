package com.zx.wx.controller;

import com.zx.common.config.CommonConfig;
import com.zx.common.controller.BaseController;
import com.zx.common.entity.ReturnMsg;
import com.zx.common.util.DateUtil;
import com.zx.contants.ApiContants;
import com.zx.wx.api.SysUserApiCaller;
import com.zx.wx.http.WechatMsgCaller;
import com.zx.wx.http.entity.UserInfo;
import com.zx.wx.service.WechatPayService;
import com.zx.wx.util.WXPayUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RequestMapping("pay")
@Controller
public class WechatPayController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(WechatPayController.class);

    @Autowired
    private WechatMsgCaller wechatMsgCaller;

    @Autowired
    private CommonConfig commonConfig;

    @Autowired
    private WechatPayService wechatPayService;

    @Autowired
    private SysUserApiCaller sysUserApiCaller;

    @RequestMapping(value = "payMoney")
    public @ResponseBody
    ReturnMsg payMoney(HttpServletRequest request, String total_fee, String body) throws Exception {
        String mch_id = commonConfig.getWechatMerchantId();
        String seqName = mch_id + DateUtil.formatDate(new Date(), "yyyyMMdd");
        UserInfo weiXinUserInfo = getWeiXinUserInfo(request);
        return wechatPayService.payMoney(weiXinUserInfo,total_fee, body, new ReturnMsg(), seqName);
    }

    /**
     * weixin_notify 2018年5月14日  19:10:58
     *
     * @param request
     * @param response
     * @throws Exception
     *             TODO：微信支付成功后的回调地址
     */
    @RequestMapping(value = "weixin_notify")
    public String  weixin_notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //System.out.println("微信支付成功,微信发送的callback信息,请注意修改订单信息");
        InputStream is = null;
        try {
            is = request.getInputStream();//获取请求的流信息(这里是微信发的xml格式所有只能使用流来读)
            String xml = WXPayUtil.inputStream2String(is, "UTF-8");
            Map<String, String> notifyMap = WXPayUtil.xmlToMap(xml);//将微信发的xml转map

            if(notifyMap.get("return_code").equals("SUCCESS")){
                if(notifyMap.get("result_code").equals("SUCCESS")){
                    logger.info("notifyMap : " + notifyMap);
                    String ordersSn = notifyMap.get("out_trade_no");//商户订单号
                    String amountpaid = notifyMap.get("total_fee");//实际支付的订单金额:单位 分
                    BigDecimal amountPay = (new BigDecimal(amountpaid).divide(new BigDecimal("100"))).setScale(2);//将分转换成元-实际支付金额:元
                    String openid = notifyMap.get("openid");  //如果有需要可以获取
                    //String trade_type = notifyMap.get("trade_type");
                    Map<String, Object> map = new HashMap<>();
                    map.put("openid",openid);
                    map.put("conditionType","openid");
                    ReturnMsg loginMsg = sysUserApiCaller.getPcApi(map, ApiContants.LOAD_USER_INFO_BY_CONDITION);
                    LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) loginMsg.getData();
                    Map<String, Object> result = new HashMap<>();

                    /*以下是自己的业务处理------仅做参考
                     * 更新order对应字段/已支付金额/状态码
                     */

                    JSONObject  json = wechatMsgCaller.getWeixinTmpMsg("您有一条新的体检结果通知",(String) data.get("name"), DateUtil.formatDate(new Date()),"湖南省人民医院","如有疑问，请联系医院客服。");

                    String sendTmpMsg = wechatMsgCaller.sendTmpMsg(openid ,
                            commonConfig.getWechatTempMsgId(),
                            json,
                            null);
                }
            }

            //告诉微信服务器收到信息了，不要在调用回调action了========这里很重要回复微信服务器信息用流发送一个xml即可
            response.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code></xml>");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}