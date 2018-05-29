package com.zx.wx.http;

import com.zx.common.cache.AccessTokenCache;
import com.zx.common.config.CommonConfig;
import com.zx.common.util.HttpUtil;
import com.zx.common.util.JsonUtil;
import com.zx.common.util.StringUtil;
import com.zx.wx.http.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class WechatAuthServiceCaller {
	
	@Autowired
	private CommonConfig commonConfig;
	@Autowired
	private AccessTokenCache accessTokenCache;

	private static final Logger logger = LoggerFactory.getLogger(WechatAuthServiceCaller.class);
	private static final int limit_times = 3;
	
	
	public static final String AccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
	/**
	 * @return 获取accessToken
	 */
	public String getAccessToken() {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("grant_type", "client_credential");
		params.put("appid", commonConfig.getWechatAppid());
		params.put("secret", commonConfig.getWechatSecret());

		String result = null;
		int i = 0;
		while (result == null && i < limit_times) {
			result = HttpUtil.doGet(AccessTokenUrl, params);
			i++;
		}
		if (StringUtil.notEmpty(result)) {
			if (result.indexOf("access_token") >= 0) {
				AccessToken token = JsonUtil.toObject(result, new AccessToken());
				if (token != null) {
					return token.getAccess_token();
				}
			}
		}
		logger.info("WechatAuthServiceCaller getAccessToken result : " + result);
		return null;
	}
	
	/**
	 *getUserInfoByBase
	 *2016年6月7日 上午11:15:07
	 *@param openid
	 *@param access_token
	 *@return
	 *TODO：通过基础access_token获取用户基本信息
	 */
	public static final String getUserInfoByBaseUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
	public UserInfoDTO getUserInfoByBase(String openid){
		String accessToken = accessTokenCache.getAccessToken(commonConfig.getWechatAppid());
		String result = null;

		int i = 0;
		while (result == null && i < limit_times) {
			result = HttpUtil.doGet(getUserInfoByBaseUrl + accessToken + "&openid=" + openid + "&lang=zh_CN" , null);
			i++;
		}
		logger.info(">>>>>>>>>>getUserInfoByBaseToken result["+result+"]<<<<<<<<<<");
		if (StringUtil.notEmpty(result)) {
			if (result.indexOf("errcode") == -1) {
				UserInfoDTO userInfo = JsonUtil.toObject(result, new UserInfoDTO());
				return userInfo;
			}
		}
		return null;
	}
	
	public static final String menuCreateUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	public void menuCreate(Menu menu){
		String accessToken = accessTokenCache.getAccessToken(commonConfig.getWechatAppid());
		String result = null;
		int i = 0;
		while (result == null && i < limit_times) {
			result = HttpUtil.doPost(menuCreateUrl + accessToken, JsonUtil.toJson(menu));
			i++;
		}
		logger.info(">>>>>>>>>>menuCreate result["+result+"]<<<<<<<<<<");
	}

	public static final String openidUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public UserIdentifier getUserAuthOpenId(String code, String appid) {
			String secret = commonConfig.getWechatSecret();
			Map<String, Object> params = new LinkedHashMap<String, Object>();
			params.put("appid", appid);
			params.put("secret", secret);
			params.put("code", code);
			params.put("grant_type", "authorization_code");
			String result = null;

			int i = 0;
			while (result == null && i < limit_times) {
				result = HttpUtil.doGet(openidUrl, params);
				i++;
			}
			logger.info(">>>>>>>>>>getUserAuthOpenId result["+result+"]<<<<<<<<<<");
			if (StringUtil.notEmpty(result)) {
				if (result.indexOf("access_token") >= 0) {
					UserIdentifier identifier = JsonUtil.toObject(result, new UserIdentifier());
					if (identifier != null) {
						return identifier;
					}
				}
			}
			return null;
    }

	/**
	 *getUserInfo
	 *2016年6月6日 上午10:49:37
	 *@param openid
	 *@param access_token
	 *@return
	 *TODO：通过网页授权access_token获取用户基本信息
	 */
	public static final String getUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=";
	public static UserInfo getUserInfo(String openid, String access_token){
		String result = null;

		int i = 0;
		while (result == null && i < limit_times) {
			result = HttpUtil.doGet(getUserInfoUrl + access_token + "&openid=" + openid + "&lang=zh_CN" , null);
			i++;
		}
		logger.info(">>>>>>>>>>getUserInfo result["+result+"]<<<<<<<<<<");
		if (StringUtil.notEmpty(result)) {
			if (result.indexOf("errcode") == -1) {
				UserInfo userInfo = JsonUtil.toObject(result, new UserInfo());
				return userInfo;
			}
		}
		return null;
	}

	public static final String ticketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

	public static String getTicket(String accessToken,String type) {
		if (!StringUtil.isEmpty(accessToken)) {
			Map<String, Object> params = new LinkedHashMap<>();
			params.put("type", type);
			params.put("access_token", accessToken);
			String result = null;
			int i = 0;
			while (result == null && i < limit_times) {
				result = HttpUtil.doGet(ticketUrl, params);
				i++;
			}
			if (result != null && result.indexOf("ticket") >= 0) {
				Ticket ticket = JsonUtil.toObject(result, new Ticket());
				if (ticket != null) {
					return ticket.getTicket();
				}
			}
		}
		return null;
	}
}
