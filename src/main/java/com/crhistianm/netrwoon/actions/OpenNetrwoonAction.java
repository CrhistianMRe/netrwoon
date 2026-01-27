package com.crhistianm.netrwoon.actions;

import org.jetbrains.annotations.NotNull;

import com.crhistianm.netrwoon.controllers.NetrwoonController;
import com.crhistianm.netrwoon.services.NetrwoonService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

public class OpenNetrwoonAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent arg0) {
        Project project = arg0.getProject();
        if(project == null) return;

        NetrwoonService service = project.getService(NetrwoonService.class);

        //For current directory
        VirtualFile[] openFiles = FileEditorManager.getInstance(project).getSelectedFiles();
        VirtualFile currentFile = openFiles.length > 0 ? openFiles[0] : null;
        VirtualFile currentDirectory = null;
        if(currentFile == null) {
            currentDirectory = LocalFileSystem.getInstance().findFileByPath(project.getBasePath());
        }else {
            currentDirectory = currentFile != null ? currentFile.getParent() : null;
        }

        service.setCurrentDirectory(currentDirectory);

        NetrwoonController controller = new NetrwoonController(project, service);

        controller.showDialog();
    }
    
}

