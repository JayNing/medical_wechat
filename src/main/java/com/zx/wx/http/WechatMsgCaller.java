package com.zx.wx.http;

import com.zx.common.cache.AccessTokenCache;
import com.zx.common.config.CommonConfig;
import com.zx.common.util.HttpUtil;
import com.zx.common.util.JsonUtil;
import com.zx.common.util.StringUtil;
import com.zx.wx.http.entity.WechatMsgDTO;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WechatMsgCaller {

    @Autowired
    private CommonConfig commonConfig;
    @Autowired
    private AccessTokenCache accessTokenCache;

    private static final Logger logger = LoggerFactory.getLogger(WechatMsgCaller.class);
    private static final int limit_times = 3;

    /**
     * @return 微信端发送模板消息
     */
    public static final String sendTmpMsgUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    public String sendTmpMsg(String openid, String templateId, JSONObject content, String url) {
        String accessToken = accessTokenCache.getAccessToken(commonConfig.getWechatAppid());
        Map<String, Object> map = new HashMap<>();
        map.put("touser",openid);
        map.put("template_id",templateId);
        if (StringUtil.notEmpty(url)){
            map.put("url",url);
        }
        map.put("data",content);
        String result = null;
        int i = 0;
        while (result == null && i < limit_times) {
            result = HttpUtil.doPost(sendTmpMsgUrl + accessToken, JsonUtil.toJson(map));
            i++;
        }
        logger.info(">>>>>>>>>>sendTmpMsg result["+result+"]<<<<<<<<<<");
        if (StringUtil.notEmpty(result)) {
            WechatMsgDTO wechatMsgDTO = JsonUtil.fromJson(result, WechatMsgDTO.class);
            if (wechatMsgDTO != null && wechatMsgDTO.getErrcode().equals("0")){
                return wechatMsgDTO.getMsgid();
            }
        }
        return null;
    }

    public JSONObject getWeixinTmpMsg(String first, String keyword1, String keyword2, String keyword3, String remark) {
        JSONObject json = new JSONObject();
        try {
            JSONObject jsonFirst = new JSONObject();
            jsonFirst.put("value", first);
            jsonFirst.put("color", "#173177");
            json.put("first", jsonFirst);

            JSONObject keywordOneObj = new JSONObject();
            keywordOneObj.put("value", keyword1);
            keywordOneObj.put("color", "#173177");
            json.put("keyword1", keywordOneObj);
            JSONObject keywordTwoObj = new JSONObject();
            keywordTwoObj.put("value", keyword2);
            keywordTwoObj.put("color", "#173177");
            json.put("keyword2", keywordTwoObj);
            JSONObject keywordThreeObj = new JSONObject();
            keywordThreeObj.put("value", keyword3);
            keywordThreeObj.put("color", "#173177");
            json.put("keyword3", keywordThreeObj);
            JSONObject remarkObj = new JSONObject();
            remarkObj.put("value", remark);
            remarkObj.put("color", "#173177");
            json.put("remark", remarkObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}