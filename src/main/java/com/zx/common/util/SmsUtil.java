package com.zx.common.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SmsUtil {


	private SmsUtil(){
	}
	private static final Logger logger = Logger.getLogger(SmsUtil.class);
	private static final String sms_user_id = ConfigUtil.getProperty("sms.id");
	private static final String sms_user_key = ConfigUtil.getProperty("sms.key");

	public static int sendSms(String mobileNo, String content){
		if(StringUtil.notEmpty(mobileNo) && StringUtil.notEmpty(content)){
			HttpURLConnection httpUrlConn = null;
			PrintWriter pw = null;
			BufferedReader bufferedReader = null;
			try {
				String postStr = "Uid=" + URLEncoder.encode(sms_user_id, "GBK") + "&Key=" + URLEncoder.encode(sms_user_key, "GBK") + "&smsMob=" + URLEncoder.encode(mobileNo, "GBK") + "&smsText=" + URLEncoder.encode(content, "GBK");
				URL url = new URL("http://gbk.sms.webchinese.cn");
				httpUrlConn = (HttpURLConnection) url.openConnection();
				httpUrlConn.setDoOutput(true);
				httpUrlConn.setDoInput(true);
				httpUrlConn.setUseCaches(false);
				httpUrlConn.setRequestMethod("POST");
				httpUrlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
				pw = new PrintWriter(new OutputStreamWriter(httpUrlConn.getOutputStream(), "GBK"));
				pw.print(postStr);
				pw.flush();
				bufferedReader = new BufferedReader(new InputStreamReader(httpUrlConn.getInputStream(), "GBK"));
				String line = null;
				StringBuffer buffer = new StringBuffer();
				while ((line = bufferedReader.readLine()) != null) {
					buffer.append(line);
				}
				String smsReturn = buffer.toString();
				logger.info("Send SMS Return : " + smsReturn);
				return Integer.parseInt(smsReturn);
			} catch (Exception e) {
				logger.error("****** Send SMS error ******", e);
				e.printStackTrace();
			} finally {
				if (httpUrlConn != null) {
					httpUrlConn.disconnect();
				}
				if (pw != null) {
					pw.close();
				}
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return -90;
	}

	public static void main(){
		sendSms("15551821403","345435");
	}
	
}
