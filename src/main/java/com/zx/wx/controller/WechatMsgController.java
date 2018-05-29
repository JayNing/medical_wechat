package com.zx.wx.controller;

import com.zx.common.config.CommonConfig;
import com.zx.common.controller.BaseController;
import com.zx.common.entity.NormalUser;
import com.zx.common.entity.ReturnMsg;
import com.zx.common.util.DateUtil;
import com.zx.contants.Contants;
import com.zx.wx.http.WechatMsgCaller;
import com.zx.wx.http.entity.UserInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RequestMapping("wechatMsg")
@Controller
public class WechatMsgController extends BaseController {

    @Autowired
    private WechatMsgCaller wechatMsgCaller;
    @Autowired
    private CommonConfig commonConfig;

    @RequestMapping(value = "sendTmpMsg")
    public @ResponseBody
    ReturnMsg payMoney(HttpServletRequest request, String total_fee, String body) throws Exception {
        ReturnMsg msg = new ReturnMsg();

        UserInfo weiXinUserInfo = getWeiXinUserInfo(request);
        NormalUser normalUser = getNormalUser(request);
        JSONObject  json = wechatMsgCaller.getWeixinTmpMsg("您有一条新的体检结果通知",normalUser.getName(), DateUtil.formatDate(new Date()),"湖南省人民医院","如有疑问，请联系医院客服。");

        String sendTmpMsg = wechatMsgCaller.sendTmpMsg(weiXinUserInfo.getOpenid() ,
                commonConfig.getWechatTempMsgId(),
                json,
                null);
        msg.setData(sendTmpMsg);
        msg.setErrorCode(Contants.SUCCESS);
        return msg;
    }
}