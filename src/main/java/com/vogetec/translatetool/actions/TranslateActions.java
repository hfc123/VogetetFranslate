package com.vogetec.translatetool.actions;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.vogetec.translatetool.ui.MyJTable;
import com.vogetec.translatetool.utils.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;

public class TranslateActions extends AnAction {


    public TranslateActions() {
        this("Open Lang Editor", "edit strings.xml", IconLoader.getIcon("/img/logo.png"));
    }

    public TranslateActions(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
       super(text,description,icon);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        final Project project = getEventProject(event);
        Logger.init("TranslateAction",10);
//        Editor editor = event.getData(PlatformDataKeys.EDITOR);
//        PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        // 从 AnActionEvent 中获取 DataContext
        DataContext dataContext = event.getDataContext();

        // 从 DataContext 中获取 PsiFile
        PsiFile psiFile = LangDataKeys.PSI_FILE.getData(dataContext);

        VirtualFile file = psiFile.getVirtualFile();
        try {
            // 设置外观
            // UIManager.setLookAndFeel(new FlatDarkLaf());
            // 更新组件外观
            MyJTable example = new MyJTable(file.getPath());
            // SwingUtilities.updateComponentTreeUI(example);
            example.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Logger.info(file.getPath());
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }
}
