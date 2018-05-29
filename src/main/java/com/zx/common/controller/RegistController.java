package com.zx.common.controller;

import com.zx.common.cache.AuthCodeCache;
import com.zx.common.entity.ResponseResult;
import com.zx.common.entity.ReturnMsg;
import com.zx.common.util.JsonUtil;
import com.zx.common.util.StringUtil;
import com.zx.common.util.Validators;
import com.zx.contants.ApiContants;
import com.zx.contants.Contants;
import com.zx.wx.api.SysUserApiCaller;
import com.zx.wx.http.WechatPcCaller;
import com.zx.wx.http.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by Administrator on 2018/5/7.
 */
@RequestMapping("regist")
@Controller
public class RegistController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(RegistController.class);

    @Autowired
    private WechatPcCaller wechatPcCaller;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private SysUserApiCaller sysUserApiCaller;

    @Autowired
    private AuthCodeCache authCodeCache;
    private static ConcurrentHashMap<String, ScheduledFuture> taskMap = new ConcurrentHashMap<>();

    @RequestMapping(value = "/sendAuthCode")
    public @ResponseBody
    ReturnMsg sendAuthCode(HttpServletRequest request, String phone, String reason, HttpServletResponse response) {
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        if (StringUtil.notEmpty(phone)) {
            if (Validators.validateMobileNo(phone)) {
                ScheduledFuture scheduledFuture = taskMap.get(phone);
                if (scheduledFuture == null || scheduledFuture.isDone()) {
                    //TODO 根据手机号去PC端取用户，查看是否能够取到
                    Map<String, Object> map = new HashMap<>();
                    map.put("phone",phone);
                    map.put("conditionType","phone");
                    ReturnMsg loginMsg = sysUserApiCaller.getPcApi(map, ApiContants.LOAD_USER_INFO_BY_CONDITION);
                    LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) loginMsg.getData();

                    if (data != null) {
                        //判断发送验证码的原因，是注册还是重置密码
                        if("regist".equals(reason)){
                            msg.addErrorMsg("该用户名已注册，请直接登陆！");
                            return msg;
                        }
                    }else{
                        if ("reset".equals(reason) || "phoneLogin".equals(reason)){
                            msg.addErrorMsg("该用户不存在");
                            return msg;
                        }
                    }

                    int vCodeType = 2;
                    if("regist".equals(reason)){
                        vCodeType = 1;
                    }else if("reset".equals(reason)){
                        vCodeType = 3;
                    }else if("phoneLogin".equals(reason)){
                        vCodeType = 2;
                    }

                    Map<String, Object> param = new HashMap<>();
                    param.put("mobile",phone);
                    param.put("vCodeType",vCodeType);

                    ResponseResult<Object> responseResult = wechatPcCaller.getPcApiByGet(param,ApiContants.SMS_URL);
                    if (responseResult == null){
                        msg.addErrorMsg("发送验证码失败");
                    }else {
                        if (responseResult.getState() == 1) {
                            String authCode = (String) responseResult.getData();
                            //创建一个1分钟之后执行的任务，3分钟后清除
                            ScheduledFuture<?> schedule = taskScheduler.schedule(new Runnable() {
                                @Override
                                public void run() {
                                    taskMap.remove(phone);
                                }
                            }, new Date(System.currentTimeMillis() + 1000 * 60));

                            authCodeCache.addCodeCache(phone,authCode);
                            taskMap.put(phone,schedule);
                            msg.setData(authCode);
                            msg.setErrorCode(Contants.SUCCESS);
                        } else {
                            msg.addErrorMsg("验证码发送失败！请确认手机号是否正确！");
                        }
                    }
                } else {
                    // 存在则太快了
                    msg.addErrorMsg("您发送验证码太频繁，请稍后再试！");
                }
            } else {
                msg.addErrorMsg("请输入正确的手机号！");
            }
        } else {
            msg.addErrorMsg("请输入正确的手机号！");
        }
        return msg;
    }

    @RequestMapping(value = "/registUser")
    public @ResponseBody
    ReturnMsg registUser(HttpServletRequest request) {
        ReturnMsg msg = new ReturnMsg();
        String username = getPara(request, "username");
        String phone = getPara(request, "phone");
        String password = getPara(request, "password");
        String authCode = getPara(request, "authCode");
        String birthDay = getPara(request, "birthDay");
        String identityCard = getPara(request, "identityCard");
        String sex = getPara(request, "sex");
        String codeCache = authCodeCache.getCodeCache(phone);
        //、检查验证码是否正确
        logger.info("authCode : " + authCode + ", codeCache : " + codeCache);
        if (authCode.equals(codeCache)){
            //TODO 从session中取出用户微信号的信息，封装存储
            UserInfo weiXinUserInfo = getWeiXinUserInfo(request);
            Map<String, Object> map = new HashMap<>();
            if (weiXinUserInfo != null){
                map.put("openid",weiXinUserInfo.getOpenid());
                map.put("username",username);
                map.put("phone",phone);
                map.put("password",password);
                birthDay = StringUtil.convertBirth(birthDay);
                map.put("birthDay",birthDay);
                map.put("identityCard",identityCard);
                map.put("sex",sex);
                map.put("weiXinUserInfo", JsonUtil.toJson(weiXinUserInfo));
                ReturnMsg loginMsg = sysUserApiCaller.getPcApi(map, ApiContants.REGIST_USER);
                msg = loginMsg;
                if (msg.getErrorCode() == 0){
                    //登录
                    msg = loginCount(identityCard,phone,password,request,msg);
                }
            }else {
                msg.addErrorMsg("未获取到当前微信数据");
            }
        }else {
            msg.addErrorMsg("验证码不正确");
        }
         return msg;
    }

    private ReturnMsg loginCount(String identityCard, String phone, String password, HttpServletRequest request, ReturnMsg msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("type","username");
        map.put("identityCard",identityCard);
        map.put("password",password);

        msg = loginInBaseController(request,map,msg);
        return msg;
    }

    @RequestMapping(value = "/modifyPhone")
    public @ResponseBody
    ReturnMsg modifyPhone(HttpServletRequest request, HttpServletResponse response) {
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        String phone = getPara(request, "phone");
        String newPhone = getPara(request, "newPhone");
        String authCode = getPara(request, "authCode");
        String codeCache = authCodeCache.getCodeCache(phone);
        // 1、检查验证码是否正确
        logger.info("resetPs authCode : " + authCode + ", codeCache : " + codeCache);
        if (authCode.equals(codeCache)) {
            Map<String, Object> map = new HashMap<>();
            map.put("phone",phone);
            map.put("newPhone",newPhone);
            map.put("type","phone");
            ReturnMsg loginMsg = sysUserApiCaller.getPcApi(map, ApiContants.RESET_USER_PASSWORD);
            msg = loginMsg;
        }else {
            msg.addErrorMsg("验证码不正确");
        }
        return msg;
    }

    @RequestMapping(value = "/changeUserInfo")
    public @ResponseBody
    ReturnMsg changeUserInfo(HttpServletRequest request, HttpServletResponse response) {
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        String phone = getPara(request, "phone");
        String type = getPara(request, "type");
        Map<String, Object> map = new HashMap<>();
        map.put("phone",phone);
        if ("sex".equals(type)){
            Integer sex = getParaToInt(request, "sex");
            map.put("sex",sex);
            map.put("type","sex");
        }else if ("birthday".equals(type)){
            String birthday = getPara(request, "birthday");
            map.put("birthday",StringUtil.convertBirth(birthday));
            map.put("type","birthday");
        }
        ReturnMsg loginMsg = sysUserApiCaller.getPcApi(map, ApiContants.RESET_USER_PASSWORD);
        msg = loginMsg;
        return msg;
    }

    @RequestMapping(value = "/resetPs")
    public @ResponseBody
    ReturnMsg resetPs(HttpServletRequest request, HttpServletResponse response) {
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        String phone = getPara(request, "phone");
        String password = getPara(request, "password");
        String authCode = getPara(request, "authCode");
        String codeCache = authCodeCache.getCodeCache(phone);
        // 1、检查验证码是否正确
        logger.info("resetPs authCode : " + authCode + ", codeCache : " + codeCache);
        if (authCode.equals(codeCache)) {
            Map<String, Object> map = new HashMap<>();
            map.put("phone",phone);
            map.put("password",password);
            map.put("type","password");
            ReturnMsg loginMsg = sysUserApiCaller.getPcApi(map, ApiContants.RESET_USER_PASSWORD);
            msg = loginMsg;
        }else {
            msg.addErrorMsg("验证码不正确");
        }
        return msg;
    }

    @RequestMapping(value = "/loadPhone")
    public @ResponseBody
    ReturnMsg loadPhone(HttpServletRequest request, HttpServletResponse response) {
        setAllowAllAccessControlHeader(response);
        ReturnMsg msg = new ReturnMsg();
        UserInfo userInfo = getWeiXinUserInfo(request);
        if (userInfo != null){
            String openid = userInfo.getOpenid();
            //TODO 根据openid去PC端获取用户手机号，返回前端
            logger.info("openid : " + openid);
            Map<String, Object> map = new HashMap<>();
            map.put("openid",openid);
            map.put("conditionType","openid");
            ReturnMsg loginMsg = sysUserApiCaller.getPcApi(map, ApiContants.LOAD_USER_INFO_BY_CONDITION);
            LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) loginMsg.getData();

            if (data != null) {
                msg.setData(data.get("mobile"));
                msg.setErrorCode(Contants.SUCCESS);
            }else {
                msg.addErrorMsg("未查询到用户");
            }
        }else {
            msg.addErrorMsg("请输入手机号");
        }
        return msg;
    }
}
