package com.zx.wx.schedule;

import com.zx.common.cache.AccessTokenCache;
import com.zx.common.cache.WechatTicketStore;
import com.zx.common.config.CommonConfig;
import com.zx.common.util.DateUtil;
import com.zx.wx.http.WechatAuthServiceCaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
 * @author ning
 * 创建于 2017年12月19日下午3:32:59
 * //TODO 刷新token定时任务
 */
@Component
public class RefreshTokenTimer {
    private static Logger logger = LoggerFactory.getLogger(RefreshTokenTimer.class);

    @Autowired
    private WechatAuthServiceCaller wechatAuthServiceCaller;
    @Autowired
    private CommonConfig commonConfig;
    @Autowired
    private AccessTokenCache accessTokenCache;
    @Autowired
    private WechatTicketStore wechatTicketStore;

    @Scheduled(fixedDelay = 1000 * 60 * 60, initialDelay = 10 * 60 * 1000)
    public void refreshTokenTimer() {
        logger.info("refreshTokenTimer is called....");
        logger.info("定时刷新token  is called...." + DateUtil.formatDate(new Date(), DateUtil.DEFAULT_FORMAT_DATE_TIME));
        initAccessToken();
    }

    public void initAccessToken() {
        logger.info("initAccessTokentoken  is called...." + DateUtil.formatDate(new Date(), DateUtil.DEFAULT_FORMAT_DATE_TIME));
        try {
            String accessToken = wechatAuthServiceCaller.getAccessToken();
            accessTokenCache.refreshToken(commonConfig.getWechatAppid(), accessToken);
            //根据token获取页面ticket
            String ticket =  wechatAuthServiceCaller.getTicket(accessToken,"jsapi");
            logger.info("====== ticket ["+ ticket + "] ======");
            if(ticket != null){
                wechatTicketStore.storeTicket(commonConfig.getWechatAppid(), ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("initAccessTokentoken is exception", e);
        }
    }
}
