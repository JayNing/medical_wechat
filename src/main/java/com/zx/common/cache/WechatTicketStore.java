package com.zx.common.cache;

import com.zx.common.util.StringUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WechatTicketStore {

    private static Map<String,String> repository = new HashMap<>();

    public static void storeTicket(String appid, String value) {
        if(StringUtil.notEmpty(appid) && StringUtil.notEmpty(value)){
            repository.put(appid, value);
        }
    }

    public static String get(String appid) {
        if(StringUtil.notEmpty(appid)){
            return repository.get(appid);
        }
        return null;
    }

}