package com.zx.common.util;


import java.nio.ByteBuffer;
import java.util.regex.Pattern;

public class BaseUtil {

    private static final String FLOOR_NO_PATTERN = "^Floor[B]?[1-9][0-9]*$";
    
    public static boolean validateFloorNo(String floorNo) {
        boolean result = false;
        if (StringUtil.notEmpty(floorNo)) {
            Pattern p = Pattern.compile(FLOOR_NO_PATTERN);
            result = p.matcher(floorNo).matches();
        }
        return result;
    }

    /**
     * IP校验
     *
     * @param ip
     * @return
     */
    public static boolean checkIP(String ip) {
        String reg = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
        if (StringUtil.notEmpty(ip)) {
            Pattern pattern = Pattern.compile(reg);
            return pattern.matcher(ip).matches();
        } else {
            return false;
        }
    }

    /**
     * 将int转化成byte[2]
     *
     * @return
     */
    public static byte[] intToBytes2(int a) {
        return new byte[]{
                // (byte) ((a >> 24) & 0xFF),
                // (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    /**
     * 将int转化成byte[1]
     *
     * @return
     */
    public static byte[] intToBytes1(int a) {
        return new byte[]{
                (byte) (a & 0xFF)
        };
    }

    /**
     * 将long转化成byte[]
     *
     * @return
     */
    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(0, x);
        return buffer.array();
    }
}
