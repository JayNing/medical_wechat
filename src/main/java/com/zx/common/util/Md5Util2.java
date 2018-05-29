package com.zx.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ning
 * 创建于 2017年11月2日下午12:24:28
 * //TODO md5工具类
 */
public class Md5Util2 {
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',  '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
    protected static MessageDigest messagedigest = null;

    /**
     * MessageDigest初始化
     * 
     * @author 高焕杰
     */
    static {
            try {
                    messagedigest = MessageDigest.getInstance("MD5");  
            } catch (NoSuchAlgorithmException e) {  
                    System.err.println("MD5FileUtil messagedigest初始化失败");  
                    e.printStackTrace();
            }  
    }

    /**
     * 对文件进行MD5加密
     * 
     * @author 高焕杰
     */
    public static String getFileMD5String(File file) throws IOException {
            @SuppressWarnings("resource")
            FileInputStream in = new FileInputStream(file);  
            FileChannel ch = in.getChannel();  
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());  
            messagedigest.update(byteBuffer);  
            return bufferToHex(messagedigest.digest());  
    }

    /**
     * 对字符串进行MD5加密
     * 
     * @author 高焕杰
     */
    public static String getMD5String(String s) {
            return getMD5String(s.getBytes());  
    }

    /**
     * 对byte类型的数组进行MD5加密
     * 
     * @author 高焕杰
     */
    public static String getMD5String(byte[] bytes) {
            messagedigest.update(bytes);  
            return bufferToHex(messagedigest.digest());  
    }

    private static String bufferToHex(byte bytes[]) {
            return bufferToHex(bytes, 0, bytes.length);  
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
            StringBuffer stringbuffer = new StringBuffer(2 * n);  
            int k = m + n;  
            for (int l = m; l < k; l++) {  
                    char c0 = hexDigits[(bytes[l] & 0xf0) >> 4];
                    char c1 = hexDigits[bytes[l] & 0xf];
                    stringbuffer.append(c0);  
                    stringbuffer.append(c1);  
            }  
            return stringbuffer.toString();  
    }

    public static void main(String[] args) throws IOException {
            String filePath = "C:/Users/seekcy/Desktop/second_navigation_template.xlsx";
            long fileBegin = System.currentTimeMillis();
            System.out.println("文件加密结果为："+getFileMD5String(new File(filePath)));
            long fileEnd = System.currentTimeMillis();
            System.out.println("文件加密耗时:" + ((fileEnd - fileBegin) / 1000) + "s");
            byte[] bytes = FileUtil.readAsByteArray(new File(filePath));
            System.out.println("字节数组加密结果：" + getMD5String(bytes));
    }
}
