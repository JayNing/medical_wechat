package com.zx.common.util;

import com.zx.wx.util.SHA1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SignatureUtil {

    private static Logger logger = LoggerFactory.getLogger(SignatureUtil.class);

    public static void main(String[] args) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", "client");
//        paramMap.put("buildId", "200001");
//        paramMap.put("areaDtoJson", "[{\"areaId\":\"5d71b262ec6411e78842000c292cc9f7\",\"areaName\":\"area1\",\"areaRailRelList\":null,\"selected\":null,\"areas\":null},{\"areaId\":\"216885daec6811e78842000c292cc9f7\",\"areaName\":\"area2\",\"areaRailRelList\":null,\"selected\":null,\"areas\":null},{\"areaId\":\"28331bfaec6811e78842000c292cc9f7\",\"areaName\":\"area3\",\"areaRailRelList\":null,\"selected\":null,\"areas\":null},{\"areaId\":\"08a2d1caec6a11e78842000c292cc9f7\",\"areaName\":\"area4\",\"areaRailRelList\":null,\"selected\":null,\"areas\":null},{\"areaId\":\"\",\"areaName\":\"张三\",\"selected\":false,\"areas\":[],\"areaRailRelList\":[]}]");
//        paramMap.put("deleteAreaIds", "[]");

        System.out.println(getSignature(paramMap, "c4769e40f34e4e048b8b6f99ac4bb118"));

    /*    String str = "[{\"areaId\":\"\",\"areaName\":\"普通区\",\"selected\":false,\"areas\":[],\"areaRailRelList\":[]}]";
        String regex = "[\u4e00-\u9fa5]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String chinese = matcher.group(0) + "";
            System.out.print(chinese);
            System.out.println(" : " + gbEncoding(chinese));
            str = str.replace(chinese, gbEncoding(chinese));
        }
        System.out.println(str);*/

    }

    public static String getSignature(Map<String, Object> paramMap, String secret) {

        Map<String, Object> sortMap = new TreeMap<String, Object>();
        sortMap.putAll(paramMap);
        // 以k1=v1&k2=v2...方式拼接参数
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> s : sortMap.entrySet()) {
            String k = s.getKey();
            Object v = s.getValue();
            if (v == null) {// 过滤空值
                continue;
            }
            builder.append(k).append(v);
        }
        String paJson = builder.toString();
        paJson = StringUtil.convertUnicode(paJson);
        String str = Md5Util.md5(secret + paJson).toUpperCase();

        return str;
    }

    public static String getSignature(String appid, String nonceStr, long timestamp, String url, String ticket) {
        Map<String, Object> params = new TreeMap<String, Object>();
        params.put("noncestr", nonceStr);
        params.put("jsapi_ticket", ticket);
        params.put("timestamp", timestamp);
        params.put("url", url);
        String paramsString = HttpUtil.contactParams(null, params);
        if (StringUtil.notEmpty(paramsString)) {
            paramsString = paramsString.substring(1);
        }
        logger.info("======paramsString[" + paramsString + "]======");
        if (StringUtil.notEmpty(paramsString)) {
            return SHA1.encode(paramsString);
        }
        return null;
    }
}
