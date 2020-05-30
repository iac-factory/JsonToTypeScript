package com.rmondjone.common;


import com.intellij.ide.util.PropertiesComponent;
import com.intellij.psi.PsiFile;
import com.rmondjone.json.java.src.org.json.JSONArray;
import com.rmondjone.json.java.src.org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 注释：JSONObject转TS实体工具
 * 时间：2020/5/27 0027 16:23
 * 作者：郭翰林
 */
public class Utils {
    /**
     * 默认空格
     */
    public static String defaultSpace = " ";
    /**
     * 分割行
     */
    public static String lineSeparator = "\n";

    /**
     * 注释：创建返回TS实体
     * 时间：2020/5/27 0027 10:59
     * 作者：郭翰林
     *
     * @param json
     * @param filedList
     * @param psiFile
     * @return
     */
    public static String createCommentString(JSONObject json, List<String> filedList, PsiFile psiFile) {
        List<String> objectKeys = new ArrayList<>();
        List<String> arrayKeys = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("export default class ").append(getPsiClassName(psiFile)).append(" {");
        stringBuilder.append(lineSeparator);
        for (String key : filedList) {
            //记录值为数组或者对象的key
            if (json.get(key) instanceof JSONObject) {
                JSONObject jsonObject = json.getJSONObject(key);
                if (jsonObject.length() != 0) {
                    objectKeys.add(key);
                }
            } else if (json.get(key) instanceof JSONArray) {
                JSONArray jsonArray = json.getJSONArray(key);
                if (jsonArray.length() != 0 && jsonArray.get(0) instanceof JSONObject) {
                    arrayKeys.add(key);
                }
            }
            stringBuilder.append(getAttributesByKey(json, key));
        }
        stringBuilder.append("}");
        //如果有JSONObject对象转换为对应的TS对象
        for (String objectKey : objectKeys) {
            stringBuilder.append(createObjectString(json.getJSONObject(objectKey), objectKey));
        }
        //如果有JSONArray并且数组对象为JSONObject对象
        for (String arrayKey : arrayKeys) {
            stringBuilder.append(createObjectString(json.getJSONArray(arrayKey).getJSONObject(0), arrayKey));
        }
        return stringBuilder.toString();
    }

    /**
     * 注释：创建JSONObject对应的实体
     * 时间：2020/5/27 0027 15:39
     * 作者：郭翰林
     *
     * @param json
     * @param ObjectKey
     * @return
     */
    public static String createObjectString(JSONObject json, String ObjectKey) {
        List<String> objectKeys = new ArrayList<>();
        List<String> arrayKeys = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(StringUtils.repeatStr(lineSeparator, 2));
        stringBuilder.append("export class ").append(StringUtils.captureStringLeaveUnderscore(ObjectKey)).append(" {");
        stringBuilder.append(lineSeparator);
        for (String key : collectGenerateFiled(json)) {
            //记录值为数组或者对象的key
            if (json.get(key) instanceof JSONObject) {
                JSONObject jsonObject = json.getJSONObject(key);
                if (jsonObject.length() != 0) {
                    objectKeys.add(key);
                }
            } else if (json.get(key) instanceof JSONArray) {
                JSONArray jsonArray = json.getJSONArray(key);
                if (jsonArray.length() != 0 && jsonArray.get(0) instanceof JSONObject) {
                    arrayKeys.add(key);
                }
            }
            stringBuilder.append(getAttributesByKey(json, key));
        }
        stringBuilder.append("}");
        //如果有JSONObject对象转换为对应的TS对象
        for (String objectKey : objectKeys) {
            stringBuilder.append(createObjectString(json.getJSONObject(objectKey), objectKey));
        }
        //如果有JSONArray并且数组对象为JSONObject对象
        for (String arrayKey : arrayKeys) {
            stringBuilder.append(createObjectString(json.getJSONArray(arrayKey).getJSONObject(0), arrayKey));
        }
        return stringBuilder.toString();
    }

    /**
     * 注释：获取当前编译文件类名
     * 时间：2020/5/27 0027 13:47
     * 作者：郭翰林
     *
     * @param psiFile
     * @return
     */
    private static String getPsiClassName(PsiFile psiFile) {
        return psiFile.getName().replaceAll("\\.ts$", "").replaceAll("\\.js$", "");
    }

    /**
     * 注释：根据Key获取属性
     * 时间：2020/5/27 0027 11:56
     * 作者：郭翰林
     *
     * @param json
     * @param key
     * @return
     */
    private static String getAttributesByKey(JSONObject json, String key) {
        StringBuilder builder = new StringBuilder();
        Object object = json.get(key);
        if (object instanceof Number) {
            builder.append(StringUtils.repeatStr(defaultSpace, 2));
            builder.append("public");
            builder.append(defaultSpace);
            builder.append(key).append("?").append(":");
            builder.append(defaultSpace);
            builder.append("number;");
            builder.append(lineSeparator);
        } else if (object instanceof String) {
            builder.append(StringUtils.repeatStr(defaultSpace, 2));
            builder.append("public");
            builder.append(defaultSpace);
            builder.append(key).append("?").append(":");
            builder.append(defaultSpace);
            builder.append("string;");
            builder.append(lineSeparator);
        } else if (object instanceof Boolean) {
            builder.append(StringUtils.repeatStr(defaultSpace, 2));
            builder.append("public");
            builder.append(defaultSpace);
            builder.append(key).append("?").append(":");
            builder.append(defaultSpace);
            builder.append("boolean;");
            builder.append(lineSeparator);
        } else if (object instanceof JSONObject) {
            if (((JSONObject) object).length() != 0) {
                //是否开启@Type注解
                if (PropertiesComponent.getInstance().getBoolean("fieldType", true)) {
                    builder.append(StringUtils.repeatStr(defaultSpace, 2));
                    builder.append("@Type(() =>").append(StringUtils.captureStringLeaveUnderscore(key)).append(")");
                    builder.append(lineSeparator);
                }
                builder.append(StringUtils.repeatStr(defaultSpace, 2));
                builder.append("public");
                builder.append(defaultSpace);
                builder.append(key).append("?").append(":");
                builder.append(defaultSpace);
                builder.append(StringUtils.captureStringLeaveUnderscore(key)).append(";");
                builder.append(lineSeparator);
            }
        } else if (object instanceof JSONArray) {
            if (((JSONArray) object).length() != 0) {
                Object arrayObject = ((JSONArray) object).get(0);
                if (arrayObject instanceof String) {
                    builder.append(StringUtils.repeatStr(defaultSpace, 2));
                    builder.append("public");
                    builder.append(defaultSpace);
                    builder.append(key).append("?").append(":");
                    builder.append(defaultSpace);
                    builder.append("string[];");
                } else if (arrayObject instanceof Number) {
                    builder.append(StringUtils.repeatStr(defaultSpace, 2));
                    builder.append("public");
                    builder.append(defaultSpace);
                    builder.append(key).append("?").append(":");
                    builder.append(defaultSpace);
                    builder.append("number[];");
                } else if (arrayObject instanceof JSONObject) {
                    //是否开启@Type注解
                    if (PropertiesComponent.getInstance().getBoolean("fieldType", true)) {
                        builder.append(StringUtils.repeatStr(defaultSpace, 2));
                        builder.append("@Type(() =>").append(StringUtils.captureStringLeaveUnderscore(key)).append(")");
                        builder.append(lineSeparator);
                    }
                    builder.append(StringUtils.repeatStr(defaultSpace, 2));
                    builder.append("public");
                    builder.append(defaultSpace);
                    builder.append(key).append("?").append(":");
                    builder.append(defaultSpace);
                    builder.append(StringUtils.captureStringLeaveUnderscore(key)).append("[];");
                }
                builder.append(lineSeparator);
            }
        } else {
            builder.append(StringUtils.repeatStr(defaultSpace, 2));
            builder.append("public");
            builder.append(defaultSpace);
            builder.append(key).append("?").append(":");
            builder.append(defaultSpace);
            builder.append("any;");
            builder.append(lineSeparator);
        }
        return builder.toString();
    }

    /**
     * 注释：获取Json对象Key的集合
     * 时间：2020/5/27 0027 14:20
     * 作者：郭翰林
     *
     * @param json
     * @return
     */
    private static List<String> collectGenerateFiled(JSONObject json) {
        Set<String> keySet = json.keySet();
        return new ArrayList<>(keySet);
    }
}
