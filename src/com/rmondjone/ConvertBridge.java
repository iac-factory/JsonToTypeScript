package com.rmondjone;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiUtilBase;
import com.rmondjone.common.Utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * 注释：Json转换TS实体
 * 时间：2020/5/27 0027 10:55
 * 作者：郭翰林
 */
public class ConvertBridge {

    private Project project;
    private Editor editor;
    private String jsonStr;
    private Operator operator;
    private PsiFile psiFile;

    public ConvertBridge(Operator operator, String jsonStr, Project project, Editor editor) {
        this.operator = operator;
        this.jsonStr = jsonStr;
        this.project = project;
        this.editor = editor;
        this.psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
    }

    public void run() {
        JSONObject json = null;
        operator.cleanErrorInfo();
        try {
            json = parseJSONObject(jsonStr);
        } catch (Exception e) {
            String jsonTS = removeComment(jsonStr);
            jsonTS = jsonTS.replaceAll("^.*?\\{", "{");
            try {
                json = parseJSONObject(jsonTS);
            } catch (Exception e2) {
                handleDataError(e2);
            }
        }
        if (json != null) {
            try {
                operator.setVisible(false);
                parseJson(json);
            } catch (Exception e2) {
                handleDataError(e2);
                operator.setVisible(true);
            }
        }
    }

    private JSONObject parseJSONObject(String jsonStr) {
        if (jsonStr.startsWith("{")) {
            return JSON.parseObject(jsonStr);
        } else if (jsonStr.startsWith("[")) {
            JSONArray jsonArray = JSON.parseArray(jsonStr);
            if (jsonArray.size() > 0 && jsonArray.get(0) instanceof JSONObject) {
                return getJsonObject(jsonArray);
            }
        }
        return null;

    }

    private JSONObject getJsonObject(JSONArray jsonArray) {
        JSONObject resultJSON = jsonArray.getJSONObject(0);
        for (int i = 1; i < jsonArray.size(); i++) {
            Object value = jsonArray.get(i);
            if (!(value instanceof JSONObject)) {
                break;
            }
            JSONObject json = (JSONObject) value;
            for (String key : json.keySet()) {
                if (!resultJSON.keySet().contains(key)) {
                    resultJSON.put(key, json.get(key));
                }
            }
        }
        return resultJSON;
    }

    private void handleDataError(Exception e2) {
        e2.printStackTrace();
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e2.printStackTrace(printWriter);
        printWriter.close();
        operator.showError(Error.DATA_ERROR);
        operator.setErrorInfo(writer.toString());
    }

    /**
     * 过滤掉// 和/** 注释
     *
     * @param str
     * @return
     */
    public String removeComment(String str) {
        String temp = str.replaceAll("/\\*" +
                "[\\S\\s]*?" +
                "\\*/", "");
        return temp.replaceAll("//[\\S\\s]*?\n", "");
    }


    private void parseJson(JSONObject json) {
        List<String> generateFiled = collectGenerateFiled(json);
        handleNormal(json, generateFiled);
    }


    private void handleNormal(JSONObject json, List<String> generateFiled) {
        WriteCommandAction.runWriteCommandAction(project, () -> {
            Document document = editor.getDocument();
            //清除内容
            document.deleteString(0, document.getTextLength());
            //生成Ts实体
            document.insertString(0, Utils.createCommentString(json, generateFiled, psiFile));
        });
    }

    private List<String> collectGenerateFiled(JSONObject json) {
        Set<String> keySet = json.keySet();
        return new ArrayList<>(keySet);
    }

    public interface Operator {

        void showError(Error err);

        void dispose();

        void setVisible(boolean visible);

        void setErrorInfo(String error);

        void cleanErrorInfo();
    }

    public enum Error {
        DATA_ERROR, PARSE_ERROR;
    }
}

