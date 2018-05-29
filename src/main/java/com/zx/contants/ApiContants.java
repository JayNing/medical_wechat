package com.zx.contants;

public class ApiContants {

    public static final String SERVER_DOMAIN = "http://ningjj.mppstore.com:8081";


    public static final String LOGIN_URL = "/normalUser/weixinLogin";
    public static final String LOAD_USER_INFO_BY_CONDITION = "/normalUser/weixinLoadUserByPhone";
    public static final String REGIST_USER = "/normalUser/weixinRegist";
    public static final String RESET_USER_PASSWORD = "/normalUser/weixinResetPs";

    /***
     * 查询体检记录
     */
    public static final String REPORT_LIST = "/api/tjgl/getrecord";
    public static final String REPORT_DETAIL = "/api/tjgl/getreport";

    public static final String LOAD_PAPER_BY_ORGANIZATION_ID = "/exam/getDefaultPaper";
    public static final String SEARCH_QUESTION_RESULT = "/exam/getUnitermsByAnswers";
    public static final String SMS_URL = "/api/sms/vcode/wechat/mas";
    public static final String SEARCH_PDF_BASE64 = "";
}