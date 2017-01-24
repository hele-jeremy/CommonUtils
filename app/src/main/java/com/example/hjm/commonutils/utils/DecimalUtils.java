package com.example.hjm.commonutils.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by hjm on 2017/1/19 10:41.
 * 保留小数工具类
 */

public class DecimalUtils {

    /**
     * 四舍五入将double类型转为String类型
     * @param value  要格式化的double数值
     * @param digit  最多要保留的小数点的位数:若小数点位数是n,n>=digit,则显示的小数点位数是digit;n<digit,则小数点小数点位数是n
     * @return
     */
    public static String douFormat(double value,int digit){
        NumberFormat format=NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(digit);
        return format.format(value).replace(",","");
    }

    /**
     * 四舍五入将String类型的double数据转为String类型
     * @param value  要格式化的数值
     * @param digit  最多要保留的小数点的位数:若小数点位数是n,n>=digit,则显示的小数点位数是digit;n<digit,则小数点小数点位数是n
     * @return
     */
    public static String douFormat(String value,int digit){
        return douFormat(Double.parseDouble(value.replace(",", "").trim()), digit);
    }

    /**
     * 四舍五入将double类型转为String类型,默认最多保留4位小数
     * @param value
     * @return
     */
    public static String douFormatDef(double value){
        return douFormat(value,4);
    }

    /**
     * 四舍五入将String类型的double数据转为String类型.默认最多保留4位小数
     * @param value
     * @return
     */
    public static String douFormatDef(String value){
        return douFormat(value,4);
    }

    /**
     * 不四舍五入,直接截取保留4位小数
     * @param value
     * @return
     */
    public static String format4Digits(double value){
        String format = new DecimalFormat("0.00000").format(value);
        int index = format.indexOf(".");
        String resultFront = format.substring(0, index);
        String resultBack = format.substring(index+1,index+5);
        if (resultBack.equals("0000")) {
            return resultFront;
        } else {
            return resultFront + "." + resultBack;
        }
    }

}
