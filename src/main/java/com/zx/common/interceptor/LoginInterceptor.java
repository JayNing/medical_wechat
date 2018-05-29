package com.zx.common.interceptor;

import com.zx.common.config.CommonConfig;
import com.zx.common.entity.NormalUser;
import com.zx.contants.Contants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ning
 * //TODO 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private CommonConfig commonConfig;

    /**
     * 在DispatcherServlet完全处理完请求后被调用(可以在该方法进行一些资源的清理操作)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle, Exception arg3) throws Exception {

    }

    /**
     * 在业务处理完成请求后，在DispatcherServlet向客户端返回响应前被调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView arg3) throws Exception {

    }

    /**
     * 业务处理器处理之前被调用，被拦截返回false，反之能正常到Controller层
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {

        request.setCharacterEncoding("UTF-8");
        NormalUser user = (NormalUser) request.getSession().getAttribute(Contants.LOGIN_USER_SESSION_KEY);
        if (user == null) {
            String redirectUrl = commonConfig.getWechatServerName() + "/authInfo/1";
            logger.info("redirectUrl : " + redirectUrl);
            String wxUserAuthUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + commonConfig.getWechatAppid()
                    + "&redirect_uri=" + redirectUrl + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
            logger.info("wxUserAuthUrl=========>" + wxUserAuthUrl);
            response.sendRedirect(wxUserAuthUrl);
            return false;
        }
        return true;
    }

}
