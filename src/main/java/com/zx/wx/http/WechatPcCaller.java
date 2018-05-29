package com.zx.wx.http;

import com.zx.common.entity.ResponseResult;
import com.zx.common.util.HttpClientUtil;
import com.zx.common.util.HttpUtil;
import com.zx.common.util.JsonUtil;
import com.zx.common.util.StringUtil;
import com.zx.contants.ApiContants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WechatPcCaller {


    private static final Logger logger = Logger.getLogger(WechatPcCaller.class);
    private static final int limit_times = 3;

    public static <T> ResponseResult<T> getPcApi(Map<String, Object> map, String url){

        ResponseResult<T> respResult = new ResponseResult<T>();

        String result = null;

        url = ApiContants.SERVER_DOMAIN + url;
        int i = 0;
        while (result == null && i < limit_times) {
            result = HttpClientUtil.doPostWithJson(url,map);
            i++;
        }
        logger.info(">>>>>>>>>>WechatPcCaller getPcApi url:  ["+ url+"]<<<<<<<<<<");
        logger.info(">>>>>>>>>>WechatPcCaller getPcApi result: ["+result+"]<<<<<<<<<<");
        if (StringUtil.notEmpty(result)) {
            respResult = JsonUtil.toObject(result, respResult);
            logger.info(">>>>>>>>>>WechatPcCaller getPcApi respResult: ["+ respResult +"]<<<<<<<<<<");
            return (ResponseResult<T>) respResult;
        }
        return null;
    }

    public static <T> ResponseResult<T> getPcApiByGet(Map<String, Object> map, String url){

        ResponseResult<T> respResult = new ResponseResult<T>();

        String result = null;

        url = ApiContants.SERVER_DOMAIN + url;
        int i = 0;
        while (result == null && i < limit_times) {
            result = HttpUtil.doGet(url,map);
            i++;
        }
        logger.info(">>>>>>>>>>WechatPcCaller getPcApiByGet url:  ["+ url+"]<<<<<<<<<<");
        logger.info(">>>>>>>>>>WechatPcCaller getPcApiByGet result: ["+result+"]<<<<<<<<<<");
        if (StringUtil.notEmpty(result)) {
            respResult = JsonUtil.toObject(result, respResult);
            logger.info(">>>>>>>>>>WechatPcCaller getPcApiByGet respResult: ["+ respResult +"]<<<<<<<<<<");
            return (ResponseResult<T>) respResult;
        }
        return null;
    }

}