package com.zx.contants;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ning
 * 创建于 2017年10月19日下午3:08:10
 * //TODO 全局web服务常量类
 */
public class Contants {

    public static final String NULL = "null";

    public static final String DEFAULT_PROJECT_LANGUAGE = "zh_CN";

    public static final String PROJECT_LANGUAGE = "project_language_";

    public static final String LOGIN_USER_SESSION_KEY = "login_user_session_key";

    public static final String WEXIN_USER_INFO_KEY = "weixin_userinfo";

    public static final int NOT_LOGIN = -1;


    /**
     * 登录用户类型
     **/
    public static final int MAJOR_USER_TYPE = 1;

    public static final int MINOR_USER_TYPE = 2;
    
    /**
     * 菜單常量類
     * **/

    public static final String SUB_MENU_PERSONAL_APPOINTMENT = "personalAppointment";
    public static final String SUB_MENU_TEAM_APPOINTMENT = "teamAppointment";
    public static final String SUB_MENU_PACKAGE_DETAILS = "packageDetails";
    
    public static final String SUB_MENU_HEALTH_MANAGER_REPORT = "healthManagerReport";
    public static final String SUB_MENU_MEDICAL_EXAMINATION_REPORT = "medicalExaminationReport";
    
    public static final String SUB_MENU_APPOINTMENT_MANAGEMENT = "appointmentManagement";
    public static final String SUB_MENU_ORDER_SERVER = "orderServer";
    public static final String SUB_MENU_GUIDE_CHECK_SERVER = "guideCheckServer";
    public static final String SUB_MENU_HISTORY_ASQ = "historyASQ";
    public static final String SUB_MENU_MY_COLLECTION = "myCollection";
    
    /**
     * 菜单URL
     * **/
    
    public static final String SUB_MENU_PERSONAL_APPOINTMENT_URL = "/medical/personalAppointment";
    public static final String SUB_MENU_TEAM_APPOINTMENT_URL = "/medical/teamAppointment";
    public static final String SUB_MENU_PACKAGE_DETAILS_URL = "/medical/packageDetails";
    
    public static final String SUB_MENU_HEALTH_MANAGER_REPORT_URL = "/medical/healthManagerReport";
    public static final String SUB_MENU_MEDICAL_EXAMINATION_REPORT_URL = "/medical/medicalExaminationReport";
    
    public static final String SUB_MENU_APPOINTMENT_MANAGEMENT_URL = "/medical/appointmentManagement";
    public static final String SUB_MENU_ORDER_SERVER_URL = "/medical/orderServer";
    public static final String SUB_MENU_GUIDE_CHECK_SERVER_URL = "/medical/guideCheckServer";
    public static final String SUB_MENU_HISTORY_ASQ_URL = "/medical/historyASQ";
    public static final String SUB_MENU_MY_COLLECTION_URL = "/medical/myCollection";
    
    /**
     * 自定義菜單名稱
     * **/
    public static final String LEVEL_MENU_MEDICAL_APPOINTMENT_NAME = "预约体检";
    public static final String LEVEL_MENU_HEALTH_REPORT_NAME = "健康报告";
    public static final String LEVEL_MENU_PERSONAL_CENTER_NAME = "个人中心";
    
    public static final String SUB_MENU_PERSONAL_APPOINTMENT_NAME = "个人预约";
    public static final String SUB_MENU_TEAM_APPOINTMENT_NAME = "团队预约";
    public static final String SUB_MENU_PACKAGE_DETAILS_NAME = "套餐详情";
    
    public static final String SUB_MENU_HEALTH_MANAGER_REPORT_NAME = "健康管理报告";
    public static final String SUB_MENU_MEDICAL_EXAMINATION_REPORT_NAME = "体检报告";
    
    public static final String SUB_MENU_APPOINTMENT_MANAGEMENT_NAME = "预约管理";
    public static final String SUB_MENU_ORDER_SERVER_NAME = "订单服务";
    public static final String SUB_MENU_GUIDE_CHECK_SERVER_NAME = "导检服务";
    public static final String SUB_MENU_HISTORY_ASQ_NAME = "历史问卷";
    public static final String SUB_MENU_MY_COLLECTION_NAME = "我的收藏";

    
    /**
     * 微信自定義菜單event type
     * **/
    public static final String WECHAT_MENU_TYPE_CLICK = "click";
    public static final String WECHAT_MENU_TYPE_VIEW = "view";
    

    /***
     * 数据统计查询时间类型
     * */
    public static final String SEARCH_DATE_TYPE_DAY = "day";
    public static final String SEARCH_DATE_TYPE_MONTH = "month";
    public static final String SEARCH_DATE_TYPE_YEAR = "year";
    /**
    *
    * 字段验证常用的属性长度常量
    * 
    */
    public static final int SIXTEEN = 16;

    public static final int THIRTY_SIX = 36;

    public static final int SIXTH_FOUR = 64;

    public static final int ONE_HUNDRED_AND_TWENTY_EIGHT = 128;

    public static final int TWO_HUNDRED_AND_FIFTY_SIX = 256;

    public static final int FIVE_HUNDRED_AND_TWELVE = 512;

    public static final int ONT_THOUSAND_AND_TWENTY_FOUR = 1024;

    public static final int TWO_THOUSAND_AND_FORTY_EIGHT = 2048;

    public static final int FOUR_THOUSAND_AND_NINETY_SIX = 4096;

    public static final int SIX_FIVE_FIVE_THREE_FIVE = 65535;

    /**
    * excel文档格式
    **/
    public static final String XLS = ".xls";

    public static final String XLSX = ".xlsx";



    /**
    * 操作类型
    **/
    public static final String ADD = "add";

    public static final String EDIT = "edit";

    public static final String DEL = "delete";

    public static final String UPDATE = "update";

    public static final Set<String> WX_UPLOAD_JPG_EXT;

    public static final Set<String> WX_UPLOAD_PIC_EXT;

    public static final Set<String> WX_UPLOAD_EXCEL_EXT;

    public static final Set<String> WX_UPLOAD_MUSIC_EXT;

    public static final String DEFAULT_ACTIVE_PERIOD = "[{\"weekdays\":\"1,2,3,4,5,6,0\",\"times\":\"00:00-24:00\"}]";

    public static final Integer DEFAULT_OVER_BOUNDARY_ALARM_FALSE = 0;

    public static final Integer DEFAULT_STOP_ALARM_FALSE = 0;

    public static final Integer DEFAULT_STOP_LIMIT_SECONDS = 30;

    public static final String NO_EXACT = "noExact";
    public static final String EXACT = "exact";


    public static final String PICTURE_CONVERT = "$$";
    
    public static final String AREA_NAME_SPITE_CHAR = "#";

    public static final String COMMA_CHAR = ",";
    public static final String LINE_CHAR = "-";
    public static final String COLON_CHAR = ":";
    /**
     * 验证字段长度类型，是最大还是最小
     */
    public static final String MAX = "max";
    public static final String MIN = "min";

    /**
     * BEACON的major和minor的取值范围
    * */
    public static final Integer BEACON_MAJOR_MAX_VALUE = 65535;
    public static final Integer BEACON_MAJOR_MIN_VALUE = 0;
    public static final Integer BEACON_MINOR_MAX_VALUE = 65535;
    public static final Integer BEACON_MINOR_MIN_VALUE = 0;
    /**
     * 门限值的取值范围
     * */
    public static final Integer PATROL_RSSI_MAX_VALUE = 0;
    public static final Integer PATROL_RSSI_MIN_VALUE = -128;
    /*数字 1 */
    public static final Integer ONE = 1;
    public static final Integer SUCCESS = 0;
    public static final String WECHAT_EVENT_KEY = "eventKey";
    /**
     * 短信模板
     *
     */
    public static final String REGIST_CODE_TEXT = "您正在注册预约体检平台账号，验证码${CODE}，请在15分钟内按页面提示提交验证码，切勿将验证码泄露于他人。";
    public static final String RESET_CODE_TEXT = "您正在重置预约体检平台账号，验证码${CODE}，请在15分钟内按页面提示提交验证码，切勿将验证码泄露于他人。";
    public static final String PHONE_LOGIN_CODE_TEXT = "您正在登录预约体检平台，验证码${CODE}，请在15分钟内按页面提示提交验证码，切勿将验证码泄露于他人。";



    private Contants() {
    }
    
    static {
        WX_UPLOAD_MUSIC_EXT = new HashSet<>();
        WX_UPLOAD_MUSIC_EXT.add("mp3");
        WX_UPLOAD_MUSIC_EXT.add("mp4");
        WX_UPLOAD_MUSIC_EXT.add("wav");
    }

    static {
        WX_UPLOAD_JPG_EXT = new HashSet<>();
        WX_UPLOAD_JPG_EXT.add("jpg");
        WX_UPLOAD_JPG_EXT.add("jpeg");
    }

    static {
        WX_UPLOAD_PIC_EXT = new HashSet<>();
        WX_UPLOAD_PIC_EXT.add("bmp");
        WX_UPLOAD_PIC_EXT.add("png");
        WX_UPLOAD_PIC_EXT.add("jpg");
        WX_UPLOAD_PIC_EXT.add("jpeg");
        WX_UPLOAD_PIC_EXT.add("gif");
    }

    static {
        WX_UPLOAD_EXCEL_EXT = new HashSet<>();
        WX_UPLOAD_EXCEL_EXT.add("xls");
        WX_UPLOAD_EXCEL_EXT.add("xlsx");
    }
}
