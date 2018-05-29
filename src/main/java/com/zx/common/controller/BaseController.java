package com.zx.common.controller;

import com.zx.common.cache.SecretCache;
import com.zx.common.config.CommonConfig;
import com.zx.common.entity.NormalUser;
import com.zx.common.entity.PageParam;
import com.zx.common.entity.ReturnMsg;
import com.zx.common.entity.SysUser;
import com.zx.common.util.DateUtil;
import com.zx.common.util.FileUtil;
import com.zx.common.util.StringUtil;
import com.zx.contants.ApiContants;
import com.zx.contants.Contants;
import com.zx.wx.api.SysUserApiCaller;
import com.zx.wx.http.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ning
 * // 控制层基础操作
 */
public class BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static final String REDIRECT = "redirect:";

    @Autowired
    private SecretCache secretCache;

    @Autowired
    private SysUserApiCaller sysUserApiCaller;
    @Autowired
    private CommonConfig commonConfig;

    @ExceptionHandler
    @ResponseBody
    public ReturnMsg exp(HttpServletRequest request, HttpServletResponse response, Exception e) {
        setAllowAllAccessControlHeader(response);

        ReturnMsg msg = new ReturnMsg();

        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Type", "application/json;charset=UTF-8");

        msg.addErrorMsg("微信端请求错误，请稍后重试或联系管理员");
        msg.setErrorCode(-90);
        return msg;
    }

    protected ReturnMsg loginInBaseController(HttpServletRequest request, Map<String,Object> map,ReturnMsg msg){
        ReturnMsg loginMsg = sysUserApiCaller.getPcApi(map, ApiContants.LOGIN_URL);
        if (loginMsg.getErrorCode() == 0){
            LinkedHashMap<String, Object> data = (LinkedHashMap<String, Object>) loginMsg.getData();
            NormalUser normalUser = new NormalUser();
            normalUser.setMobile((String) data.get("mobile"));
            normalUser.setIdCard((String) data.get("idCard"));
            Long birthdayTime = (Long) data.get("birthday");
            Long createTime = (Long) data.get("createdTime");
            normalUser.setBirthday(new Date(birthdayTime));
            normalUser.setCreateTime(new Date(createTime));
            normalUser.setHeadImgUrl((String) data.get("headImgUrl"));
            normalUser.setName((String) data.get("name"));
            normalUser.setSecret((String) data.get("secret"));
            normalUser.setOpenId((String) data.get("openId"));
            normalUser.setNickName((String) data.get("nickName"));
            request.getSession().setAttribute(Contants.LOGIN_USER_SESSION_KEY,normalUser);

            //TODO 将PC端返回secret存入缓存
            UserInfo userInfo = getWeiXinUserInfo(request);
            secretCache.addSecretCache(normalUser.getMobile(),normalUser.getSecret());

            msg.setData(userInfo);
            msg.setErrorCode(Contants.SUCCESS);
            return msg;
        }else {
            return loginMsg;
        }
    }

    /**
     * 业务层异常捕获
     *
     * @param response
     * @return
     */

    public void downloadFile(HttpServletResponse response, String fileFullPath) {
        String fileName = FileUtil.getFileNameByFullPath(fileFullPath);
        File downloadFile = new File(fileFullPath);
        if (!downloadFile.exists()) {
            return;
        }
        response.setContentLength((int) downloadFile.length());
        response.setHeader("Accept-Ranges", "bytes");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        // 下载文件流
        InputStream inputStream = null;
        OutputStream os = null;
        try {
            inputStream = new FileInputStream(downloadFile);
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    } 
                } catch (Exception e2) {
                }
            }
        }
    }

    protected UserInfo getWeiXinUserInfo(HttpServletRequest request) {
       return (UserInfo) request.getSession().getAttribute(Contants.WEXIN_USER_INFO_KEY);
    }

    protected NormalUser getNormalUser(HttpServletRequest request){
        return (NormalUser) request.getSession().getAttribute(Contants.LOGIN_USER_SESSION_KEY);
    }

    protected void setAllowAllAccessControlHeader(HttpServletResponse response) {
        if (response != null) {
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            String origin = response.getHeader("Access-Control-Allow-Origin");
            if (StringUtil.isEmpty(origin)) {
                logger.info("set Access-Control-Allow-Origin headers...");
                response.setHeader("Access-Control-Allow-Origin", "*");
            }
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        }
    }

    protected PageParam getPageParam(HttpServletRequest request) {
        return new PageParam(getPageNum(request), getNumPerPage(request));
    }

    private int getNumPerPage(HttpServletRequest request) {
        int numPerPage = 10;
        String numPerPageStr = getValue(request, "numPerPage");
        if (StringUtil.notEmpty(numPerPageStr)) {
            numPerPage = Integer.parseInt(numPerPageStr);
        }
        return numPerPage;
    }

    private int getPageNum(HttpServletRequest request) {
        int pageNum = 1;
        String pageNumStr = getValue(request, "pageNum");
        if (StringUtil.notEmpty(pageNumStr)) {
            pageNum = Integer.parseInt(pageNumStr);
        }
        return pageNum;
    }

    public Map<String, String[]> getParaMap(HttpServletRequest request) {
        return request.getParameterMap();
    }

    private String getValue(HttpServletRequest request, String paramName) {
        return request.getParameter(paramName);
    }

    protected String getPara(HttpServletRequest request, String paramName) {
        return getValue(request, paramName);
    }

    protected Integer getParaToInt(HttpServletRequest request, String paramName) {
        String value = getValue(request, paramName);
        if (StringUtil.notEmpty(value)) {
            return Integer.parseInt(value);
        }
        return null;
    }

    protected Long getParaToLong(HttpServletRequest request, String paramName) {
        String value = getValue(request, paramName);
        if (StringUtil.notEmpty(value)) {
            return Long.parseLong(value);
        }
        return null;
    }

    protected Float getParaToFloat(HttpServletRequest request, String paramName) {
        String value = getValue(request, paramName);
        if (StringUtil.notEmpty(value)) {
            return Float.parseFloat(value);
        }
        return null;
    }

    protected Double getParaToDouble(HttpServletRequest request, String paramName) {
        String value = getValue(request, paramName);
        if (StringUtil.notEmpty(value)) {
            return Double.parseDouble(value);
        }
        return null;
    }

    protected Boolean getParaToBoolean(HttpServletRequest request, String paramName) {
        String value = getValue(request, paramName);
        if (StringUtil.notEmpty(value)) {
            return Boolean.parseBoolean(value);
        }
        return null;
    }

    protected Date getParaToDate(HttpServletRequest request, String paramName) {
        String value = getValue(request, paramName);
        if (StringUtil.notEmpty(value)) {
            return DateUtil.parseDate(request.getParameter(paramName));
        }
        return null;
    }

    protected Date getParaToDatetime(HttpServletRequest request, String paramName) {
        String value = getValue(request, paramName);
        if (StringUtil.notEmpty(value)) {
            return DateUtil.parseDateTime(request.getParameter(paramName));
        }
        return null;
    }

    protected Date getParaToDate(HttpServletRequest request, String paramName, String pattern) {
        String value = getValue(request, paramName);
        if (StringUtil.notEmpty(value)) {
            return DateUtil.parseDate(request.getParameter(paramName), pattern);
        }
        return null;
    }

    protected String[] getParaValues(HttpServletRequest request, String paramName) {
        return request.getParameterValues(paramName);
    }

    protected String getSysUserNameInSession(HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute(Contants.LOGIN_USER_SESSION_KEY);
        String userName = sysUser != null ? sysUser.getUserName() : "";
        return userName;
    }
    
    protected String getSysUserIdInSession(HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute(Contants.LOGIN_USER_SESSION_KEY);
        String userId = sysUser != null ? sysUser.getUuid() : "";
        return userId;
    }
    
    protected SysUser getSysUserFromSession(HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute(Contants.LOGIN_USER_SESSION_KEY);
        return sysUser;
    }
}
