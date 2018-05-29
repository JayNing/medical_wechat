package com.zx.common.controller;

import com.zx.common.cache.AuthCodeCache;
import com.zx.common.config.CommonConfig;
import com.zx.common.entity.ReturnMsg;
import com.zx.common.util.DateUtil;
import com.zx.common.util.StringUtil;
import com.zx.contants.ApiContants;
import com.zx.contants.Contants;
import com.zx.wx.api.SysUserApiCaller;
import com.zx.wx.http.WechatAuthServiceCaller;
import com.zx.wx.http.entity.UserIdentifier;
import com.zx.wx.http.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/6.
 */

@Controller
public class LoginController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private CommonConfig commonConfig;
    @Autowired
    private WechatAuthServiceCaller wechatAuthServiceCaller;
    @Autowired
    private SysUserApiCaller sysUserApiCaller;
    @Autowired
    private AuthCodeCache authCodeCache;

    @RequestMapping("authInfo/{times}")
    public String loadLoginInfo(HttpServletRequest request, HttpServletResponse response,@PathVariable int times){
        //获取openid授权信息
        String code = request.getParameter("code");
        String appid = commonConfig.getWechatAppid();
        logger.info("authInfo code ============ > " + code);
        logger.info("authInfo appid ============ > " + appid);
        if(StringUtil.isEmpty(code)){
            if(times < 3){
                times++;
                String redirectUrl = commonConfig.getWechatServerName() + "/authInfo/" + times;
                logger.info("redirectUrl : " + redirectUrl);
                String wxUserAuthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + commonConfig.getWechatAppid()
                        + "&redirect_uri=" + redirectUrl + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
                return wxUserAuthUrl;
            }else{
                request.setAttribute("errorMsg", "没有授权成功，请重新授权！");
                return "redirect: /loginError.html";
            }
        }
        UserIdentifier userIdentifier = wechatAuthServiceCaller.getUserAuthOpenId(code, appid);
        String openId = userIdentifier.getOpenid();
        String access_token = userIdentifier.getAccess_token();//网页授权的token
        UserInfo userInfo = wechatAuthServiceCaller.getUserInfo(openId, access_token);
        logger.info("weixin_userinfo : " + userInfo);
        request.getSession().setAttribute(Contants.WEXIN_USER_INFO_KEY, userInfo);

        return "redirect:/login.html";
    }

    @RequestMapping("doLogin")
    public @ResponseBody
    ReturnMsg login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        setAllowAllAccessControlHeader(response);

        ReturnMsg msg = new ReturnMsg();
        String type = getPara(request, "type");
        String name = getPara(request, "username");
        String password = getPara(request, "password");
        String phone = getPara(request, "phone");
        String authCode = getPara(request, "authCode");
        logger.info("name : " + name + ", password : " + password);

        //TODO 获取PC端的用户信息的secret，登录验证；验证成功，返回SUCCESS
        if ("phone".equals(type)){
            if (!authCode.equals(authCodeCache.getCodeCache(phone))){
                msg.addErrorMsg("验证码不正确");
                return msg;
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("type",type);
        map.put("identityCard",name);
        map.put("password",password);
        map.put("phone",phone);

        msg = loginInBaseController(request,map,msg);
        return msg;

    }

    @RequestMapping("loadInfo")
    public @ResponseBody
    ReturnMsg loadInfo(HttpServletRequest request, HttpServletResponse response){
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        UserInfo userInfo = getWeiXinUserInfo(request);
        msg.setData(userInfo);
        msg.setErrorCode(Contants.SUCCESS);
        return msg;
    }

    @RequestMapping("loadNormalUserInfo")
    public @ResponseBody
    ReturnMsg loadNormalUserInfo(HttpServletRequest request, HttpServletResponse response){
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        UserInfo userInfo = getWeiXinUserInfo(request);
        if (userInfo != null){
            Map<String, Object> map = new HashMap<>();
            map.put("openid",userInfo.getOpenid());
            map.put("conditionType","openid");
            ReturnMsg loginMsg = sysUserApiCaller.getPcApi(map, ApiContants.LOAD_USER_INFO_BY_CONDITION);
            LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) loginMsg.getData();
            Map<String, Object> result = new HashMap<>();
            result.put("headImgUrl",(String) data.get("headImgUrl"));
            result.put("username",(String) data.get("name"));
            result.put("phone",(String) data.get("mobile"));
            result.put("sex",(Integer) data.get("sex"));
            result.put("idCard",(String) data.get("idCard"));
            long date = (long) data.get("birthday");
            result.put("birthday", DateUtil.formatDate(new Date(date)));
            msg.setData(result);
            msg.setErrorCode(Contants.SUCCESS);
        }else {
            msg.addErrorMsg("未获取到用户信息");
        }

        return msg;
    }

}
