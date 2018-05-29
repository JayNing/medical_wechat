/**
 * 
 */
package com.zx.wx.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zx.common.cache.AccessTokenCache;
import com.zx.common.config.CommonConfig;
import com.zx.common.entity.ReturnMsg;

/**
 * @author Administrator
 *
 */
@RequestMapping("accessToken")
@Controller
public class AccessTokenController {
	
	@Autowired
	private AccessTokenCache accessTokenCache;
	@Autowired
	private CommonConfig commonConfig;
	
	@RequestMapping("getAccessToken")
	public @ResponseBody ReturnMsg getAccessToken(HttpServletRequest request){
		ReturnMsg msg = new ReturnMsg();
		msg.setData(accessTokenCache.getAccessToken(commonConfig.getWechatAppid()));
		return msg;
	}

}
