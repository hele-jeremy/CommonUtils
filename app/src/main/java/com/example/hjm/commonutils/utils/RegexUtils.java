package com.example.hjm.commonutils.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hjm on 2017/1/19 13:11.
 * 正则表达式工具类
 */

public class RegexUtils {

    public static final String patter = "^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\\d!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*]+$";

    public static boolean isMobile(String mobile) {
        if (mobile == null) {
            return false;
        }
        //修改下手机号码的匹配规则,支持17开头号段的   黄江明修改于20161118下午
//        Pattern pattern=Pattern.compile("^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8]))\\d{8}$");
        Pattern pattern = Pattern.compile("^((1[3,5,8][0-9])|(14[5,7])|(17[0-8]))\\d{8}$");
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    /**
     * 验证是否是数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if (str == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    public static boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_-]+@\\\\w+\\\\.[a-z]+(\\\\.[a-z]+)?");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 是否是身份证号码。只接受第二代身份证号码（18位）
     *
     * @param idCard
     * @return
     */
    public static boolean isIdCard(String idCard) {
        if (TextUtils.isEmpty(idCard) || idCard.length() != 18) {
            return false;
        }
        String frontIdNum = idCard.substring(0, 17);  //前17位
        String lastIdNum = idCard.substring(17);        //最后一位
        String idNumber = String.valueOf(lastIdCode(frontIdNum));    //通过前17位计算出最后一位
        return lastIdNum.equals(idNumber);

    }

    /**
     * 通过身份证号码的前17位计算出最后一位
     *
     * @param idCardAhead17
     * @return
     */
    private static char lastIdCode(String idCardAhead17) {
        char idNumChar[] = idCardAhead17.toCharArray();
        int idNumWeight[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};  //权重值
        char lastCode[] = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'}; //最后一位
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += (idNumChar[i] - '0') * idNumWeight[i];
        }
        int lastCodeIndex = sum % 11;
        return lastCode[lastCodeIndex];
    }

    /**
     * 是否时银行卡
     *
     * @return
     */
    public static boolean isBankCard(String bankCardCode) {
        if (isNumber(bankCardCode)) {
            if (bankCardCode.length() >= 15) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是中文,包含繁体中文
     * @return
     */
    public static boolean isChinese(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5]{0,}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
