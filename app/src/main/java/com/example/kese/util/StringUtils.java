package com.example.kese.util;

import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


public class StringUtils {
    public static boolean isEmpty(String str){
        if(str==null||str.length()<=0){
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean isChinese(String str) {
        return Pattern.matches("[\u4e00-\u9fa5]+", str);
    }

    public static boolean isNumberic(String sellectInformantion) {
        return Pattern.matches("\\d+", sellectInformantion);
    }
    public static boolean isPingYing(String sellect_Informantion) {
        return sellect_Informantion.matches("[a-zA-Z]{2,4}");
    }
    public static int get_What_Type(String sellect_Informantion) {
        if(isChinese(sellect_Informantion))
            return 1;//中文返回1
        else if(isNumberic(sellect_Informantion))
            return 2;//数字返回2
        else if(isPingYing(sellect_Informantion))
            return 3;//拼音返回3
        else
            return 0;//匹配失败返回0
    }

    public static String getInitials(String chinese) {
        StringBuilder initials = new StringBuilder();
        for (char c : chinese.toCharArray()) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
            if (pinyinArray != null && pinyinArray.length > 0) {
                initials.append(pinyinArray[0].charAt(0));  // 取第一个拼音的首字母（即声母）
            }
        }
        return initials.toString().toUpperCase();  // 返回大写的声母字符串
    }
}
