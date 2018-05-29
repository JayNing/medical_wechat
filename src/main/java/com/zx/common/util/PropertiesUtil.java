package com.zx.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @author ning
 * 创建于 2017年10月19日下午3:35:55
 * //TODO Properties工具类
 */
public class PropertiesUtil {

    public static final String PROPERTIES_PATH = PropertiesUtil.class.getResource("/").toString() + "service.properties";
    //测试使用
//    public static final String PROPERTIES_PATH = "E:\\seekcy\\workspace\\chemical\\src\\main\\resources\\service.properties";

    public static List<String> getAllKeys(String filePath) {
        List<String> keys = new ArrayList<>();
        Properties props = new Properties();
        try (InputStream in = new BufferedInputStream(new FileInputStream(filePath))) {
            props.load(in);
            Set<Object> keyValue = props.keySet();
            for (Iterator<Object> it = keyValue.iterator(); it.hasNext();) {
                String key = (String) it.next();
                keys.add(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return keys;
    }

    public static void main(String[] args) {
        String s = "E:\\seekcy\\workspace\\chemical\\src\\main\\resources\\service.properties";
        String path = s.replace("/", "//");
        System.out.println(path);

    }

    /**  
     * 根据主键key读取主键的值value  
     * @param filePath 属性文件路径  
     * @param key 键名  
     */
    public static String readValue(String filePath, String key) {
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            String value = props.getProperty(key);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**  
     * 更新（或插入）一对properties信息(主键及其键值)  
     * 如果该主键已经存在，更新该主键的值；  
     * 如果该主键不存在，则插件一对键值。  
     * @throws IOException
     */
    public static void writeProperties(String filePath, String key, String value) throws IOException {
        Properties props = new Properties();
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (OutputStream fos = new FileOutputStream(filePath)) {
            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。   
            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。   
            ;
            props.setProperty(key, value);
            // 以适合使用 load 方法加载到 Properties 表中的格式，   
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流   
            props.store(fos, "Update '" + key + "' value");
        } catch (IOException e) {
            System.err.println("属性文件更新错误");
        }
    }

    public static String getPropertiesPath(){
        return PropertiesUtil.PROPERTIES_PATH.substring(PropertiesUtil.PROPERTIES_PATH.indexOf(":") + 1);
    }
}
