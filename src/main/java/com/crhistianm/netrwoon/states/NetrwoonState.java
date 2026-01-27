package com.crhistianm.netrwoon.states;


import javax.swing.DefaultListModel;

import com.intellij.openapi.vfs.VirtualFile;

public class NetrwoonState {

    private VirtualFile currentDirectory;

    private DefaultListModel<String> currentDirectoryList = new DefaultListModel<>();

    public NetrwoonState() {}

    public VirtualFile getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(VirtualFile currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public DefaultListModel<String> getCurrentDirectoryList() {
        return currentDirectoryList;
    }

    public void setCurrentDirectoryList(DefaultListModel<String> currentDirectoryList) {
        this.currentDirectoryList = currentDirectoryList;
    }


}
