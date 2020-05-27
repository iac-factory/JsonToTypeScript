package com.rmondjone;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.rmondjone.ui.JsonDialog;

import org.jetbrains.annotations.NotNull;

/**
 * User: dim
 * Date: 14-7-4
 * Time: 下午1:44
 */
public class MainAction extends AnAction {

    @Override
    public void update(@NotNull AnActionEvent e) {
        super.update(e);
        boolean visible = isActionAvailable(e);
        final Presentation presentation = e.getPresentation();
        presentation.setEnabled(visible);
        presentation.setVisible(visible);
    }

    /**
     * 注释：Action是否可用
     * 时间：2020/5/26 0026 10:54
     * 作者：郭翰林
     *
     * @param e
     * @return
     */
    private boolean isActionAvailable(AnActionEvent e) {
        final VirtualFile file = getVirtualFiles(e);
        if (getEventProject(e) != null && file != null) {
            final FileType fileType = file.getFileType();
            return FileTypeManager.getInstance().getStdFileType("TypeScript").equals(fileType) || StdFileTypes.JS.equals(fileType);
        }
        return false;
    }

    private VirtualFile getVirtualFiles(AnActionEvent e) {
        return PlatformDataKeys.VIRTUAL_FILE.getData(e.getDataContext());
    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        JsonDialog jsonD = new JsonDialog(editor, project);
        jsonD.setProject(project);
        jsonD.setSize(600, 400);
        jsonD.setLocationRelativeTo(null);
        jsonD.setVisible(true);
    }
}
