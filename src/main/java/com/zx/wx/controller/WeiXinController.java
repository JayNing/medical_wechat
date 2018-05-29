package com.zx.wx.controller;

import com.alibaba.dubbo.common.utils.IOUtils;
import com.zx.common.util.StringUtil;
import com.zx.contants.Contants;
import com.zx.wx.http.entity.TextMessage;
import com.zx.wx.util.SHA1;
import com.zx.wx.util.XMLParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


@RequestMapping("weixin")
@Controller
public class WeiXinController{

	private static Logger logger = LoggerFactory.getLogger(WeiXinController.class);

	private String token = "hello";

	@RequestMapping("msg")
	public void msg(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		logger.info("signature:" + signature + ",\n timestamp:" + timestamp + ",\n nonce:" + nonce + ", echostr:" + echostr);
		String[] str = {token,timestamp,nonce};
		// 将token、timestamp、nonce三个参数进行字典序排序：     A:65  a:97
		Arrays.sort(str);
		// 拼接成字符串的时候顺序不能变
		String bigStr = str[0] + str[1] + str[2];
		String signature2;
		try {
//				signature2 = SHA1.getSHA1(token, timestamp, nonce, echostr);
			signature2 = new SHA1().getDigestOfString(bigStr.getBytes());
			logger.info("signature2:" + signature2);
			// �?定要不区分大小写比较.因为微信服务器在传入signature参数时已经转化了小写,但是API并没有提�?
			if(signature.equalsIgnoreCase(signature2)){
				// 如果比对成功,则将随机字符串原样返�?
				if(StringUtil.notEmpty(echostr)){
					out.write(echostr);
				}else{
					out.write("success");
				}
			}else{
				out.write("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String openid = "";
		Map<String, String[]> parameterMap = request.getParameterMap();
		logger.info("parameterMap : " + parameterMap);
		if(parameterMap != null && parameterMap.size() > 0){
			for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
				String key = entry.getKey();
				if(key.equals("openid")){
					openid = entry.getValue()[0];
					logger.info("openid : " + openid);
				}
			}
		}
		String EventKey = "";
		String event = "";
		String postData = null;
		try {
			postData = IOUtils.read(new InputStreamReader(request.getInputStream(), "UTF-8"));
			logger.info("====== postData[" + postData + "] ======");
			if(StringUtil.notEmpty(postData)){
				String msgType = XMLParse.extractXmlElementValue(postData, "MsgType");
				logger.info("====== msgType [" + msgType + "] ======");
				if (StringUtil.notEmpty(msgType) && msgType.equalsIgnoreCase("event")){
					event = XMLParse.extractXmlElementValue(postData, "Event");
					EventKey = XMLParse.extractXmlElementValue(postData, "EventKey");
					logger.info("event -------> " + event);
					logger.info("EventKey -------> " + EventKey);
					String toUserName = XMLParse.extractXmlElementValue(postData, "ToUserName");
					logger.info("toUserName -------> " + toUserName);
					String fromUserName = XMLParse.extractXmlElementValue(postData, "FromUserName");
					logger.info("fromUserName -------> " + fromUserName);
					/*if(StringUtil.notEmpty(event)){
						if (event.equalsIgnoreCase("subscribe")){
							if(StringUtil.notEmpty(openid)){
								//根据openid获取用户信息
								UserInfoDTO userInfoByBase = wechatAuthServiceCaller.getUserInfoByBase(openid);
								logger.info("userInfoByBase : " + userInfoByBase);
								//TODO 根据openid查看本地服务器是否已有此用户信息，如果有，则更新，没有，则添加
								SysUser sysUser = new SysUser();
								sysUser.setNickName(userInfoByBase.getNickname());
								sysUser.setHeadImgUrl(userInfoByBase.getHeadimgurl());
								sysUser.setOpenid(userInfoByBase.getOpenid());
								//TODO 将用户信息存入数据库
							}
						}else if (event.equalsIgnoreCase(Contants.WECHAT_MENU_TYPE_VIEW)){
							logger.info("save EventKey -------> " + EventKey);
							eventKeyCache.addKeyCache("ning",EventKey);
						}else if(event.equalsIgnoreCase("unsubscribe")){
							eventKeyCache.removeKeyCache("ning");
						}
					}*/
				}
			}

		} catch (UnsupportedEncodingException e) {
			logger.info("callback   UnsupportedEncodingException  -----------> " + e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("callback   IOException  -----------> " + e);
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

	@RequestMapping("msg1")
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			String postData = null;
			String EventKey = null;
			try {
				postData = IOUtils.read(new InputStreamReader(request.getInputStream(), "UTF-8"));
				logger.info("====== postData[" + postData + "] ======");
				String event = XMLParse.extractXmlElementValue(postData, "Event");
				EventKey = XMLParse.extractXmlElementValue(postData, "EventKey");
				logger.info("event -------> " + event);
				logger.info("EventKey -------> " + EventKey);
			}catch (UnsupportedEncodingException e) {
				logger.info("callback   UnsupportedEncodingException  -----------> " + e);
				e.printStackTrace();
			} catch (IOException e) {
				logger.info("callback   IOException  -----------> " + e);
				e.printStackTrace();
			}

			// 发送方帐号（open_id）
			String fromUserName = XMLParse.extractXmlElementValue(postData, "FromUserName");
			logger.info("fromUserName -------> " + fromUserName);
			// 公众帐号
			String toUserName = XMLParse.extractXmlElementValue(postData, "ToUserName");
			logger.info("toUserName -------> " + toUserName);
			// 消息类型
			String msgType = XMLParse.extractXmlElementValue(postData, "MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType("text");
			textMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals("text")) {
				respContent = "您发送的是文本消息！";
			}
	   /*// 图片消息
	   else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
	    respContent = "您发送的是图片消息！";
	   }
	   // 地理位置消息
	   else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
	    respContent = "您发送的是地理位置消息！";
	   }
	   // 链接消息
	   else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
	    respContent = "您发送的是链接消息！";
	   }
	   // 音频消息
	   else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
	    respContent = "您发送的是音频消息！";
	   } */
			// 事件推送
			else if (msgType.equals("event")) {
				// 事件类型
				String eventType = XMLParse.extractXmlElementValue(postData, "Event");
				// 订阅
				if (eventType.equalsIgnoreCase("subscribe")) {
					respContent = "谢谢您的关注！";
				}
				// 取消订阅
				else if (eventType.equalsIgnoreCase("unsubscribe")) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equalsIgnoreCase("click")) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = XMLParse.extractXmlElementValue(postData, "EventKey");

					if (eventKey.equals(Contants.SUB_MENU_GUIDE_CHECK_SERVER)) {
						respContent = "导检服务被点击！";
					}
				}
			}

			textMessage.setContent(respContent);
			respMessage = XMLParse.convertToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

}
