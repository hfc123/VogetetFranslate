package com.vogetec.translatetool.test;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class MyFileEditorProvider implements FileEditorProvider {

    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        // 检查此提供程序是否能够为给定文件创建编辑器
        return true;
    }

    @NotNull
    @Override
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        // 创建你的自定义文件编辑器
        return new MyFileEditor(project, file);
    }

    @NotNull
    @Override
    public String getEditorTypeId() {
        // 返回你的自定义编辑器类型的唯一标识符
        return "MyCustomEditor";
    }

    @NotNull
    @Override
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
    }
}
