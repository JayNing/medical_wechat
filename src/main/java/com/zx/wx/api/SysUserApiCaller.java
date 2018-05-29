package com.zx.wx.api;

import com.zx.common.entity.ReturnMsg;
import com.zx.common.util.*;
import com.zx.contants.ApiContants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SysUserApiCaller {


    private static final Logger logger = Logger.getLogger(SysUserApiCaller.class);
    private static final int limit_times = 3;

    public static ReturnMsg getPcApi(Map<String, Object> map,String url){
        String result = null;

        url = ApiContants.SERVER_DOMAIN + url;
        int i = 0;
        while (result == null && i < limit_times) {
            result = HttpClientUtil.doPostWithJson(url , map);
            i++;
        }
        logger.info(">>>>>>>>>> getPcApi url:  ["+ url+"]<<<<<<<<<<");
        logger.info(">>>>>>>>>>getPcApi result: ["+result+"]<<<<<<<<<<");
        if (StringUtil.notEmpty(result)) {
            ReturnMsg msg = JsonUtil.toObject(result, new ReturnMsg());
            return msg;
        }
        return null;
    }

    public static ReturnMsg getPcApiBySecret(Map<String, Object> map, String url, String secret){
        url = ApiContants.SERVER_DOMAIN + url;
        String result = null;
        String signature = SignatureUtil.getSignature(map,secret);
        map.put("signature",signature);
        int i = 0;
        while (result == null && i < limit_times) {
            result = HttpClientUtil.doPostWithJson(url , map);
            i++;
        }
        logger.info(">>>>>>>>>> getPcApiBySecret url:  ["+ url+"]<<<<<<<<<<");
        logger.info(">>>>>>>>>>getPcApiBySecret result: ["+result+"]<<<<<<<<<<");
        if (StringUtil.notEmpty(result)) {
            ReturnMsg msg = JsonUtil.toObject(result, new ReturnMsg());
            return msg;
        }
        return null;
    }
}