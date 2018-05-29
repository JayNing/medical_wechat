package com.zx.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	
	private static Properties prop = null;
	
	static{
		prop = new Properties();
		InputStream in = ConfigUtil.class.getResourceAsStream("/sysconfig.properties");
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static String getProperty(String key){
		return prop.getProperty(key);
	}
}
