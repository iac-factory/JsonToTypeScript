package com.rmondjone.common;

import org.apache.http.util.TextUtils;

/**
 * 注释：字符串工具
 * 时间：2020/5/27 0027 14:07
 * 作者：郭翰林
 */
public class StringUtils {

    /**
     * 注释：转化驼峰写法（首字母大写）
     * 时间：2020/5/27 0027 14:26
     * 作者：郭翰林
     *
     * @param text
     * @return
     */
    public static String captureStringLeaveUnderscore(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        String[] strings = text.split("_");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(captureName(strings[0]));
        for (int i = 1; i < strings.length; i++) {
            stringBuilder.append(captureName(strings[i]));
        }
        return stringBuilder.toString();
    }

    /**
     * 注释：第一个字母大写其余字母全部小写
     * 时间：2020/5/27 0027 15:09
     * 作者：郭翰林
     *
     * @param text
     * @return
     */
    public static String captureName(String text) {
        if (text.length() > 0) {
            text = text.substring(0, 1).toUpperCase() + text.substring(1);
        }
        return text;
    }

    /**
     * 注释：重复字符串多少次
     * 时间：2020/5/27 0027 14:07
     * 作者：郭翰林
     *
     * @param repeat
     * @param number
     * @return
     */
    public static String repeatStr(String repeat, int number) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < number; i++) {
            stringBuilder.append(repeat);
        }
        return stringBuilder.toString();
    }
}
