package com.crhistianm.netrwoon.controllers;



import javax.swing.DefaultListModel;

import com.crhistianm.netrwoon.components.NetrwoonDialogWrapper;
import com.crhistianm.netrwoon.services.NetrwoonService;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBList;

public class NetrwoonController{

    private JBList<String> list;

    private final NetrwoonService service;

    private NetrwoonDialogWrapper dialog;

    Project project;

    public NetrwoonController(Project project, NetrwoonService service) {
        this.service = service;
        this.project = project;


        DefaultListModel<String> actualList = service.getList();

        list = new JBList<>(actualList);
        list.setFocusable(true);
        dialog = new NetrwoonDialogWrapper(list, project);

        selectFirstIndex();
    }

    public void showDialog() {
        this.dialog.show();
    }

    private void selectFirstIndex(){
        if(list.getModel().getSize() != 0) list.setSelectedIndex(0);
    }


    
}
