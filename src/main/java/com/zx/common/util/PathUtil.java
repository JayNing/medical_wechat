package com.zx.common.util;

import java.io.File;

public class PathUtil {

    private static final String UPLOAD_PATH = System.getProperty("user.home") + "/tomcat-medical-data/";

    public static String getPropertiesPath() {
        return PropertiesUtil.PROPERTIES_PATH.substring(PropertiesUtil.PROPERTIES_PATH.indexOf(":") + 1);
    }
    
    public static String getUploadPath() {
        return UPLOAD_PATH;
    }

    public static String getExcelTempPath(String type) {
        String path = getUploadPath() + File.separator + "Excel" + File.separator + type + File.separator;
        FileUtil.generateFolder(path);
        return path;
    }

      public static String getFmapPath(String buildingId) {
        String path = UPLOAD_PATH + "fmapFile" + File.separator + buildingId + File.separator;
        FileUtil.generateFolder(path);
        return path;
    }

    public static String getTagIconPath(String newIconPic) {
        String path = newIconPic.substring(0,newIconPic.lastIndexOf("/"));
        FileUtil.generateFolder(path);
        return path;
    }
}
