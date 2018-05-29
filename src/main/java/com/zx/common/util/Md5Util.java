package com.zx.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * @author ning
 * 创建于 2017年10月19日下午3:32:16
 * //TODO MD5工具类
 */
public class Md5Util {

    public static void main(String[] args) {
        System.out.println(md5("Seekcy2015"));
        System.out.println(md5("89ae58b666f42d4b6ba097db3717c144"));
        System.out.println(md5("123456"));
        System.out.println(md5("e10adc3949ba59abbe56e057f20f883e"));
        // System.out.println(md5("bbs@8081_admin"));
        /*  System.out.println(md5(md5("123456"),"8b671975b6274bd68c0c79056f3c8e05"));
        System.out.println(md5(md5("open@80_licmgr"),"00aec49b-c314-462b-9ab6-cd720f7f58fa"));
        System.out.println(md5("Seekcy2015","WEB_LOCATE"));
        System.out.println(md5("Seekcy2015","ADMIN_PRIV"));*/
        /*     System.out.println(md5("Seekcy2016"));
        System.out.println(md5("123456"));*/
        //System.out.println(md5("123456"));
        //        String md5 = md5("baisj123");
        //        System.out.println(md5);
    }

    public static final String md5(String src, String salt) {
        String toEncode = src + ((salt == null) ? "" : ("{" + salt + "}"));
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] btInput = toEncode.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final static String md5(String s) {
        return md5(s, null);
    }

    public static String getMd5ByFile(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
            value = StringUtil.leftPad(value, 32, '0');
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
