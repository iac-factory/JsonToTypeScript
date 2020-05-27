package com.rmondjone.ui;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.rmondjone.ConvertBridge;

import org.apache.http.util.TextUtils;

import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;


public class JsonDialog extends JFrame implements ConvertBridge.Operator {

    private JPanel contentPane2;
    private JButton okButton;
    private JButton cancelButton;
    private JLabel errorLB;
    private JTextPane editTP;
    private JButton formatBtn;
    private Project project;
    private Editor editor;
    private String errorInfo = null;

    public JsonDialog(Editor editor, Project project) throws HeadlessException {
        this.editor = editor;
        this.project = project;
        setContentPane(contentPane2);
        setTitle("Json To TypeScript");
        getRootPane().setDefaultButton(okButton);
        this.setAlwaysOnTop(true);
        initListener();
    }

    private void initListener() {

        okButton.addActionListener(e -> onOK());
        formatBtn.addActionListener(e -> {
            String json = editTP.getText();
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = JSON.parseObject(json);
                String formatJson = JSON.toJSONString(jsonObject, SerializerFeature.PrettyFormat);
                editTP.setText(formatJson);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = JSON.parseArray(json);
                String formatJson = JSON.toJSONString(jsonArray, SerializerFeature.PrettyFormat);
                editTP.setText(formatJson);
            }

        });
        editTP.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
                    onOK();
                }
            }
        });

        errorLB.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (errorInfo != null) {
                    ErrorDialog errorDialog = new ErrorDialog(errorInfo);
                    errorDialog.setSize(800, 600);
                    errorDialog.setLocationRelativeTo(null);
                    errorDialog.setVisible(true);
                }
            }
        });
        cancelButton.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane2.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {

        this.setAlwaysOnTop(false);
        String jsonSTR = editTP.getText().trim();
        if (TextUtils.isEmpty(jsonSTR)) {
            return;
        }
        new ConvertBridge(this, jsonSTR, project, editor).run();
    }

    private void onCancel() {
        dispose();
    }

    public void setProject(Project mProject) {
        this.project = mProject;
    }

    public void cleanErrorInfo() {
        errorInfo = null;
    }

    public void setErrorInfo(String error) {
        errorInfo = error;
    }

    @Override
    public void showError(ConvertBridge.Error err) {
        switch (err) {
            case DATA_ERROR:
                errorLB.setText("data err !!");
                Toast.make(errorLB, MessageType.ERROR, "click to see details");
                break;
            case PARSE_ERROR:
                errorLB.setText("parse err !!");
                Toast.make(errorLB, MessageType.ERROR, "click to see details");
                break;
        }
    }

}
