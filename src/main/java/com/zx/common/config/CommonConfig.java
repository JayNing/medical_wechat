package com.zx.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by ning on 2018/4/10 16:33.
 */
@Component
public class CommonConfig {

    @Value("${wechat.template.msg.id}")
    private String wechatTempMsgId;

    @Value("${zx.wechat.merchant.key}")
    private String wechatMerchantKey;

    @Value("${zx.wechat.merchant.id}")
    private String wechatMerchantId;

    @Value("${wechat.devplatform.appid}")
    private String wechatAppid;

    @Value("${wechat.devplatform.appsecret}")
    private String wechatSecret;

    @Value("${wechat.server.name}")
    private String wechatServerName;

    @Value("${wechat.server.ip}")
    private String wechatServerIp;

	@Value("${sms.id}")
	private String smsId;

	@Value("${sms.key}")
	private String smsKey;
	@Value("${open.server.name}")
	private String openServerName;

	public String getWechatTempMsgId() {
		return wechatTempMsgId;
	}

	public void setWechatTempMsgId(String wechatTempMsgId) {
		this.wechatTempMsgId = wechatTempMsgId;
	}

	public String getWechatMerchantKey() {
		return wechatMerchantKey;
	}

	public void setWechatMerchantKey(String wechatMerchantKey) {
		this.wechatMerchantKey = wechatMerchantKey;
	}

	public String getWechatMerchantId() {
		return wechatMerchantId;
	}

	public void setWechatMerchantId(String wechatMerchantId) {
		this.wechatMerchantId = wechatMerchantId;
	}

	public String getOpenServerName() {
		return openServerName;
	}

	public void setOpenServerName(String openServerName) {
		this.openServerName = openServerName;
	}

	public String getWechatAppid() {
		return wechatAppid;
	}

	public void setWechatAppid(String wechatAppid) {
		this.wechatAppid = wechatAppid;
	}

	public String getWechatSecret() {
		return wechatSecret;
	}

	public void setWechatSecret(String wechatSecret) {
		this.wechatSecret = wechatSecret;
	}

	public String getWechatServerName() {
		return wechatServerName;
	}

	public void setWechatServerName(String wechatServerName) {
		this.wechatServerName = wechatServerName;
	}

	public String getWechatServerIp() {
		return wechatServerIp;
	}

	public void setWechatServerIp(String wechatServerIp) {
		this.wechatServerIp = wechatServerIp;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getSmsKey() {
		return smsKey;
	}

	public void setSmsKey(String smsKey) {
		this.smsKey = smsKey;
	}
}
