/**
 * 
 */
package com.zx.common.cache;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Component
public class AccessTokenCache {

	 private static final Logger logger = LoggerFactory.getLogger(AccessTokenCache.class);
	 
	  private static Map<String, String> tokenCache = new HashMap<>();
	  
	  public void refreshToken(String appid, String accessToken){
		  logger.info("refreshToken : " + accessToken);
		  tokenCache.put(appid, accessToken);
	  }
	  
	  public String getAccessToken(String appid){
		  String accessToken = tokenCache.get(appid);
		  logger.info("getAccessToken : " + accessToken);
		  return accessToken;
	  }
	
}
