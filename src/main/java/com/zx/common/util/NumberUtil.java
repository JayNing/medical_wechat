package com.zx.common.util;

import java.text.DecimalFormat;

/**
 * Created by ning on 2018/4/17 11:53.
 */
public class NumberUtil {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("######0"); //四色五入转换成整数

        Double x=0.33333333336;
        Double y=0.66666666666;

        System.out.println(convertDoubleFromDouble(x));
        System.out.println(convertDoubleFromDouble(y));
    }

    public static int convertIntFromDouble(double x){
        DecimalFormat df = new DecimalFormat("###0.00");
        return Integer.valueOf(df.format(x));
    }

    public static Double convertDoubleFromDouble(double x){
        DecimalFormat df = new DecimalFormat("###0.00");
        return Double.valueOf(df.format(x));
    }
}
