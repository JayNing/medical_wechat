package com.zx.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ning
 * 创建于 2017年10月18日下午4:05:54
 * //TODO 表单校验工具类
 */
public class Validators {

    private static final String PHONE_PATTERN = "^1\\d{10}$";

    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9][a-zA-Z0-9.!@#$%^&*_]{3,17}$";

    private static final String POINT = ".";

    private static final String COLON = ":";

    private static final String POINT_ZERO = ".0";

    private static final String MAX = "max";

    private static final String MIN = "min";

    private static final String TRANSVERSE_LINE = "-";

    private static final int TIME_FORMAT_LENGTH = 3;

    private static final int HOUR_VALUE = 23;

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    public static void main(String[] args) {
        /*    System.out.println(checkNumber("20.0.2.0"));
        String minor = "156.3.0";
        System.out.println( minor = minor.substring(0, minor.lastIndexOf(".")));*/
        System.out.println(isIDCard("341204199110091812"));

        //System.err.println(validateIp("1.1.0.0"));
        /*  String str = "1.0";
        int idx = str.lastIndexOf(".");//查找小数点的位置
        String strNum = str.substring(0,idx);//截取从字符串开始到小数点位置的字符串，就是整数部分
        int num = Integer.valueOf(strNum);//把整数部分通过Integer.valueof方法转换为数字
        
        System.out.println(isDigit(str) && isNumeric(strNum));*/
        /**
         * 声明字符串dong
         */
        //   String dong = "$%$%$34584yuojk@#￥#%%￥……%&";
        /**
         * 调用过滤出中文的方法
         */
        //  dong = filterChinese(dong);
        /**
         * 打印结果
         */
        //  System.out.println("过滤出中文：" + dong);
        /*  
        if(dong.getBytes().length == dong.length()){
            System.out.println("不含中文");
        }else{
            System.out.println("含有中文");
        }
        */
    }

    public Validators() {

    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 验证不同的set集合含有相同的元素
     * @return boolean
     */
    public static <T>  boolean validDifferentSetHaveSameElement(Set<T> set1, Set<T> set2){
        Set<T> sumSet = new HashSet<>();
        if (set1 == null){
            set1 = new HashSet<>();
        }
        if (set2 == null){
            set2 = new HashSet<>();
        }
        sumSet.addAll(set1);
        sumSet.addAll(set2);
        if (sumSet.size() < (set1.size() + set2.size())){
            //说明有重复的元素
            return true;
        }
        return false;
    }

    /**
     * 验证时间点字符串，加上前后时间段后，是否跨天
     * 如：23:50， 此时间点往前30分钟，往后30分钟，就是23:20-25:20，即下一条的凌晨，跨天了
     * */
    public static boolean validSingleTimePeriod(String str, Integer beforeTime, Integer afterTime) {
        boolean valid = true;
        String[] hourMinute = str.split(":");
        if(hourMinute.length != 2) {
            valid = false;
        } else if(hourMinute[0].length() == 2 && hourMinute[1].length() == 2) {
            int hour;
            for(hour = 0; hour < 2; ++hour) {
                if(!Character.isDigit(hourMinute[0].charAt(hour)) || !Character.isDigit(hourMinute[1].charAt(hour))) {
                    valid = false;
                }
            }

            if(valid) {
                hour = Integer.parseInt(hourMinute[0]);
                int min = Integer.parseInt(hourMinute[1]);
                if(hour < 0 || hour > 24 || min < 0 || min > 59) {
                    valid = false;
                }
                Integer startMin = min;
                Integer endMin = min;
                if (valid){
                    startMin = min - beforeTime;
                    endMin = min + afterTime;
                    if (hour == 0 && startMin < 0){
                        valid = false;
                    }else if (hour == 23 && endMin > 59){
                        valid = false;
                    }
                }
            }
        } else {
            valid = false;
        }

        return valid;
    }

    
    private static boolean pointInPolygon(float x, float y, float[] polyX, float[] polyY) {
        int polyCorners = polyX.length;
        int i, j = polyCorners - 1;
        boolean oddNodes = false;
        for (i = 0; i < polyCorners; i++) {
            if (polyY[i] < y && polyY[j] >= y || polyY[j] < y && polyY[i] >= y) {
                if (polyX[i] + (y - polyY[i]) / (polyY[j] - polyY[i]) * (polyX[j] - polyX[i]) < x) {
                    oddNodes = !oddNodes;
                }
            }
            j = i;
        }
        return oddNodes;
    }

    /**
     *checkNumber
     *2017年4月21日 下午2:29:08
     *@param str
     *@return
     *TODO：判断字符串格式是否为"1.0,2.0"格式字符串
     */
    public static boolean checkNumber(String str) {
        if (StringUtil.notEmpty(str)) {
            if (str.indexOf(POINT) > 0 && str.endsWith(POINT_ZERO) && str.indexOf(POINT) == str.lastIndexOf(POINT)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 
     * @Title : filterNumber
     * @Type : FilterStr
     * @date : 2014年3月12日 下午7:23:03
     * @Description : 过滤出数字
     * @param number
     * @return
     */
    public static String filterNumber(String number) {
        number = number.replaceAll("[^(0-9)]", "");
        return number;
    }

    /**
     * 
     * @Title : filterAlphabet
     * @Type : FilterStr
     * @date : 2014年3月12日 下午7:28:54
     * @Description : 过滤出字母
     * @param alph
     * @return
     */
    public static String filterAlphabet(String alph) {
        alph = alph.replaceAll("[^(A-Za-z)]", "");
        return alph;
    }

    /**
     * 
     * @Title : filterChinese
     * @Type : FilterStr
     * @date : 2014年3月12日 下午9:12:37
     * @Description : 过滤出中文
     * @param chin
     * @return
     */
    public static String filterChinese(String chin) {
        chin = chin.replaceAll("[^(\\u4e00-\\u9fa5)]", "");
        return chin;
    }

    /**
     * 
     * @Title : filter
     * @Type : FilterStr
     * @date : 2014年3月12日 下午9:17:22
     * @Description : 过滤出字母、数字和中文
     * @param character
     * @return
     */
    public static String filter(String character) {
        character = character.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
        return character;
    }

    public static boolean isValidInt(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean notListEmpty(@SuppressWarnings("rawtypes") List list) {
        boolean flag = false;
        if (list != null && list.size() > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     *validateRequest
     *2016年6月17日 下午5:12:10
     *@param isRequest
     *@param msg
     *@return
     *TODO：验证是否是必填字段
     */
    public static boolean validateRequest(boolean isRequest, String msg) {
        boolean result = false;
        if (isRequest) {
            if (StringUtil.notEmpty(msg)) {
                result = true;
            }
        }
        return result;
    }

    /**
     *validateNumValue
     *2016年6月17日 下午5:56:20
     *@param num
     *@param limit
     *@param type
     *@return
     *TODO：验证数字的值的大小
     */
    public static boolean validateNumValue(int num, int limit, String type) {
        boolean result = false;
        if (MAX.equals(type)) {
            //数值不能大于limit
            if (num <= limit) {
                result = true;
            }
        } else if (MIN.equals(type)) {
            //数值不能小于limit
            if (num >= limit) {
                result = true;
            }
        }
        return result;
    }

    /**
     *validateLength
     *2016年6月17日 下午5:12:20
     *@param msg
     *@param length
     *@param type
     *@return
     *TODO：输入字段长度验证
     */
    public static boolean validateLength(String msg, int length, String type) {
        boolean result = false;
        if (StringUtil.notEmpty(msg)) {
            if (MAX.equals(type)) {
                //长度不能大于length
                if (msg.length() <= length) {
                    result = true;
                }
            } else if (MIN.equals(type)) {
                //长度不能小于length
                if (msg.length() >= length) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     *validateMobileNo
     *2016年6月17日 下午5:12:59
     *@param mobNo
     *@return
     *TODO：是否是电话号码
     */
    public static boolean validateMobileNo(String mobNo) {
        boolean result = false;
        if (StringUtil.notEmpty(mobNo)) {
            Pattern p = Pattern.compile(PHONE_PATTERN);
            result = p.matcher(mobNo).matches();
        }
        return result;
    }

    /**
     *isNumeric
     *2016年6月17日 下午5:14:27
     *@param str
     *@return
     *TODO：判断是否为数字
     */
    public static boolean isNumeric(String str) {
        if (!StringUtil.notEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNegative(String str) {
        if (!StringUtil.notEmpty(str)) {
            return false;
        }
        if (str.startsWith(TRANSVERSE_LINE)) {
            for (int i = 1; i < str.length(); i++) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     *isDigit
     *2016年12月22日 下午2:25:22
     *@param str
     *@return
     *TODO：包含是否为负数,小数
     */
    public static boolean isDigit(String str) {
        if (!StringUtil.notEmpty(str)) {
            return false;
        }

        boolean strResult = str.matches("-?[0-9]+.*[0-9]*");
        return strResult;
    }

    /**
     *isValidDate
     *2016年6月17日 下午5:25:34
     *@param str
     *@param template
     *@return
     *TODO：验证是否是想要的日期格式
     */
    public static boolean isValidDate(String str, String template) {
        boolean convertSuccess = true;
        //默认模板为yyyy-MM-dd HH:mm:ss
        String pattern = DateUtil.DEFAULT_FORMAT_DATE_TIME;
        if (StringUtil.notEmpty(pattern)) {
            pattern = template;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            //设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     *isValidTime
     *2016年6月17日 下午5:50:08
     *@param str
     *@return
     *TODO：验证时间格式是否正确
     */
    public static boolean isValidTime(String str) {
        boolean convertSuccess = false;
        //格式为 HH:mm:ss
        if (StringUtil.notEmpty(str)) {
            String[] nums = StringUtil.splitByPattern(str, COLON);
            if (nums != null && nums.length == TIME_FORMAT_LENGTH) {
                for (String num : nums) {
                    if (!isNumeric(num)) {
                        return false;
                    }
                }
                //如果全部为数字，则继续往下运行
                //小时
                if (0 <= Integer.valueOf(nums[0]) && Integer.valueOf(nums[0]) <= HOUR_VALUE
                //分钟
                        && 0 <= Integer.valueOf(nums[1]) && Integer.valueOf(nums[1]) <= 59
                        //秒数
                        && 0 <= Integer.valueOf(nums[2]) && Integer.valueOf(nums[2]) <= 59) {
                    //若全部符合，则是正确的时间格式,其他情况全部返回false
                    convertSuccess = true;
                }

            }
        }
        return convertSuccess;
    }

    /**
     *compTime
     *2016年6月20日 下午3:42:37
     *@param startTime
     *@param endTime
     *@return true:表示 开始时间小于结束时间， false:表示结束时间小于开始时间
     *TODO：比较时间字符串大小
     */
    public static boolean compTime(String startTime, String endTime) {
        if (startTime.indexOf(COLON) < 0 || endTime.indexOf(COLON) < 0) {
            System.out.println("格式不正确");
        } else {
            String[] array1 = startTime.split(COLON);
            long total1 = Long.valueOf(array1[0]) * 3600 + Long.valueOf(array1[1]) * 60 + Long.valueOf(array1[2]);
            //System.out.println(total1);
            String[] array2 = endTime.split(COLON);
            long total2 = Long.valueOf(array2[0]) * 3600 + Long.valueOf(array2[1]) * 60 + Long.valueOf(array2[2]);
            //System.out.println(total2);
            return total1 - total2 <= 0 ? true : false;
        }
        return false;
    }

    /**
     *compareDate
     *2016年6月17日 下午8:18:14
     *@param start
     *@param end
     *@return true:活动开始时间是否早于活动结束时间，  false:活动开始时间晚于活动结束时间
     *TODO：比较活动开始时间是否晚于活动结束时间
     */
    public static boolean compareDate(Date start, Date end) {
        boolean result = true;
        if (start.getTime() > end.getTime()) {
            //说明活动开始时间在活动结束时间之后，不正确
            return false;
        }
        return result;
    }

    /**
    * @des 待验证的字符串
    * @return 如果是符合邮箱格式的字符串,返回<b>true</b>,否则为<b>false</b>
    */
    public static boolean isEmail(String str) {
        //String regex = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}" ;
        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return match(regex, str);
    }

    /**
     * @des 待验证的字符串
     * @return 如果是符合网址格式的字符串,返回<b>true</b>,否则为<b>false</b>
     */
    public static boolean isHomepage(String str) {
        String regex = "http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";
        return match(regex, str);
    }

    /** 
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        boolean result = false;
        if (StringUtil.notEmpty(password)) {
            Pattern p = Pattern.compile(PASSWORD_PATTERN);
            result = p.matcher(password).matches();
        }
        return result;
    }

    /**
     * 
     * @param str
     * @param minLen -1 表示无最小长度,最小(默认)为1
     * @param maxLen -1　表示无最大长度,无默认值
     * @return
     */
    public static boolean validateAlphabetNumber(String str, int minLen, int maxLen) {
        boolean result = false;
        if (StringUtil.notEmpty(str)) {
            if (minLen <= 0) {
                minLen = 1;
            }
            if (maxLen > 0 && maxLen > minLen) {
                Pattern p = Pattern.compile("^[a-zA-Z0-9]{" + minLen + "," + maxLen + "}$");
                result = p.matcher(str).matches();
            } else {
                Pattern p = Pattern.compile("^[a-zA-Z0-9]{" + minLen + ",}$");
                result = p.matcher(str).matches();
            }
        }
        return result;
    }

    /**
     * 
     * @param str
     * @param minLen -1 表示无最小长度,最小(默认)为1
     * @param maxLen -1　表示无最大长度,无默认值
     * @return
     */
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_@.]{4,18}$";

    public static boolean validateUsername(String str) {
        boolean result = false;
        if (StringUtil.notEmpty(str)) {
            Pattern p = Pattern.compile(USERNAME_PATTERN);
            result = p.matcher(str).matches();
        }
        return result;
    }

    /**
     * 验证mac地址
     * @param mac
     * @return
     */
    private static final String MAC_PATTERN = "^[a-fA-F0-9]{12}$";

    public static boolean validateMac(String str) {
        boolean result = false;
        if (StringUtil.notEmpty(str)) {
            Pattern p = Pattern.compile(MAC_PATTERN);
            result = p.matcher(str).matches();
        }
        return result;
    }

    private static final String DEVICE_UUID_NO_LINE_PATTERN = "^[a-fA-F0-9]{8}[a-fA-F0-9]{4}[a-fA-F0-9]{4}[a-fA-F0-9]{4}[a-fA-F0-9]{12}$";

    public static boolean validateDeviceUuidNotContainLine(String str) {
        boolean result = false;
        if (StringUtil.notEmpty(str)) {
            Pattern p = Pattern.compile(DEVICE_UUID_NO_LINE_PATTERN);
            result = p.matcher(str).matches();
        }
        return result;
    }

    private static final String DEVICE_UUID_PATTERN = "^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$";

    public static boolean validateDeviceUuid(String str) {
        boolean result = false;
        if (StringUtil.notEmpty(str)) {
            Pattern p = Pattern.compile(DEVICE_UUID_PATTERN);
            result = p.matcher(str).matches();
        }
        return result;
    }

    public static boolean validateMajorMinor(int major) {
        return major >= 0 && major <= 65535;
    }

    private static final String HEX_PATTERN = "^[a-fA-F0-9]+$";

    public static boolean validateHex(String hex) {
        boolean result = false;
        if (StringUtil.notEmpty(hex)) {
            Pattern p = Pattern.compile(HEX_PATTERN);
            result = p.matcher(hex).matches();
        }
        return result;
    }

    private static final String FLOOR_NO_PATTERN = "^Floor[B]?[1-9][0-9]*$";

    public static boolean validateFloorNo(String floorNo) {
        boolean result = false;
        if (StringUtil.notEmpty(floorNo)) {
            Pattern p = Pattern.compile(FLOOR_NO_PATTERN);
            result = p.matcher(floorNo).matches();
        }
        return result;
    }

    private static final String DOMAIN_PATTERN = "^[A-Za-z0-9_]+(\\.[A-Za-z0-9_]+)+$";

    public static boolean validateDomain(String ip) {
        boolean result = false;
        if (StringUtil.notEmpty(ip)) {
            Pattern p = Pattern.compile(DOMAIN_PATTERN);
            result = p.matcher(ip).matches();
        }
        return result;
    }

    private static final String IP_PATTERN = "^[1-9][0-9]{0,2}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";

    public static boolean validateIp(String ip) {
        boolean result = false;
        if (StringUtil.notEmpty(ip)) {
            Pattern p = Pattern.compile(IP_PATTERN);
            result = p.matcher(ip).matches();
        }
        return result;
    }
}
