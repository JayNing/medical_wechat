/**
 * 
 */
package com.zx.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 *
 */
@Component
public class AuthCodeCache {

	 private static final Logger logger = LoggerFactory.getLogger(AuthCodeCache.class);
	 
	  private static Map<String, String> authCodeCache = new HashMap<>();


	public void removeCodeCache(String phone){
		logger.info("phone : " + phone);
		authCodeCache.remove(phone);
	}
	  
	 public void addCodeCache(String phone, String key){
	   logger.info("key : " + key);
		 authCodeCache.put(phone, key);
	 }
	  
	  public String getCodeCache(String phone){
		  String eventKey = authCodeCache.get(phone);
		  logger.info("authCode : " + eventKey);
		  return eventKey;
	  }
	
}
