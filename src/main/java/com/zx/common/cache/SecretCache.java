package com.zx.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SecretCache {

    private static final Logger logger = LoggerFactory.getLogger(SecretCache.class);

    private static Map<String, String> secretCache = new HashMap<>();


    public void removeSecretCache(String phone){
        logger.info("phone : " + phone);
        secretCache.remove(phone);
    }

    public void addSecretCache(String phone, String key){
        logger.info("secret : " + key);
        secretCache.put(phone, key);
    }

    public String getSecretCache(String phone){
        String secret = secretCache.get(phone);
        logger.info("secret : " + secret);
        return secret;
    }
}