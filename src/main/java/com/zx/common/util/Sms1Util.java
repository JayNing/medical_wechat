package com.zx.common.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Sms1Util {


	private Sms1Util(){
	}
	private static final Logger logger = Logger.getLogger(Sms1Util.class);
	private static final String sms_user_id = ConfigUtil.getProperty("sms.id");
	private static final String sms_user_key = ConfigUtil.getProperty("sms.key");

	private static final String APID = "301583";
	private static final String EC_NAME = "集团客户";
	private static final String SECRET_KEY = "301583";
	private static final String ADDSERIAL = "111";
	private static final String SIGN = "lsign001";

	public static int sendSms(String mobileNo, String content){
		if(StringUtil.notEmpty(mobileNo) && StringUtil.notEmpty(content)){
			HttpURLConnection httpUrlConn = null;
			PrintWriter pw = null;
			BufferedReader bufferedReader = null;
			try {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(EC_NAME);
				stringBuilder.append(APID);
				stringBuilder.append(SECRET_KEY);
				stringBuilder.append(mobileNo);
				stringBuilder.append(content);
				stringBuilder.append(SIGN);
				stringBuilder.append(ADDSERIAL);
				String mac = Md5Util.md5(stringBuilder.toString());
				String postStr = "apId=" + URLEncoder.encode(APID, "GBK") + "&ecName=" + URLEncoder.encode(EC_NAME, "GBK") + "&secretKey=" + URLEncoder.encode(SECRET_KEY, "GBK")
						+ "&content=" + URLEncoder.encode(content, "GBK") + "&mobiles=" + URLEncoder.encode(mobileNo, "GBK") + "&sign=" + URLEncoder.encode(SIGN,"GBK")
						+ "&addSerial=" + URLEncoder.encode(ADDSERIAL, "GBK") + "&mac=" + URLEncoder.encode(mac, "GBK");
				URL url = new URL("http:// 112.35.1.155:1992/sms/norsubmit");
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
		sendSms("15551821403","我不好");
	}
	
}
