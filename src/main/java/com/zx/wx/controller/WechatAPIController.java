package com.zx.wx.controller;

import com.zx.common.cache.WechatTicketStore;
import com.zx.common.config.CommonConfig;
import com.zx.common.controller.BaseController;
import com.zx.common.util.SignatureUtil;
import com.zx.common.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/WeChatAPI")
public class WechatAPIController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(WechatAPIController.class);

	@Autowired
	private CommonConfig commonConfig;
	@Autowired
	private WechatTicketStore wechatTicketStore;

	@RequestMapping(value = "GetSignature")
	public @ResponseBody Map<String, Object> getSignature(String url, HttpServletResponse response) {
		setAllowAllAccessControlHeader(response);
		return generateSignature(url, commonConfig.getWechatAppid());
	}

	private Map<String,Object> generateSignature(String url, String appid) {

		Map<String, Object> map = new HashMap<>();
		String ticket = wechatTicketStore.get(appid);
		if (StringUtil.notEmpty(ticket)) {
			long timestamp = System.currentTimeMillis() / 1000;
			String nonceStr = UUID.randomUUID().toString().substring(0, 16);
			String signature = SignatureUtil.getSignature(appid, nonceStr, timestamp, url, ticket);
			logger.info("======signature[" + signature + "]======");
			if (StringUtil.notEmpty(signature)) {
				map.put("appId", appid);
				map.put("timestamp", timestamp);
				map.put("nonceStr", nonceStr);
				map.put("signature", signature);
				List<String> jsApiList = new ArrayList<String>();
				jsApiList.add("checkJsApi");
				jsApiList.add("startSearchBeacons");
				jsApiList.add("stopSearchBeacons");
				jsApiList.add("onSearchBeacons");
				map.put("jsApiList", jsApiList);
			} else {
				map.put("error", "get signature failed");
			}
		} else {
			map.put("error", "get signature failed");
		}

		return map;
	}

}
