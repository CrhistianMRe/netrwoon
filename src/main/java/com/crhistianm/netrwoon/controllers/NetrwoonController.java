package com.crhistianm.netrwoon.controllers;



import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;

import com.crhistianm.netrwoon.components.NetrwoonDialogWrapper;

import static com.crhistianm.netrwoon.controllers.KeyBinds.getMainViewKeyBinds;
import com.crhistianm.netrwoon.services.NetrwoonService;


import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBList;

public class NetrwoonController implements Disposable{

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

        loadListBindings();
        setPathLabelText();
        selectFirstIndex();
    }

    public void showDialog() {
        this.dialog.show();
    }

    private void selectFirstIndex(){
        if(list.getModel().getSize() != 0) list.setSelectedIndex(0);
    }

    private void setPathLabelText(){
        dialog.setPathText(service.getCurrentPath());
        service.setPathListener(path -> dialog.setPathText(path));
    }

    private void loadListBindings() {

        ActionMap keyBindActions = new MainViewKeyActionsBuilder()
        .onMoveUp( () -> {
            int selected = list.getSelectedIndex();
            if (selected > 0) {
                list.setSelectedIndex(selected - 1);
                list.ensureIndexIsVisible(selected - 1);
            }
        })

        .onMoveDown( () -> {
            int selected = list.getSelectedIndex();
            if (selected < list.getModel().getSize() - 1) {
                list.setSelectedIndex(selected + 1);
                list.ensureIndexIsVisible(selected + 1);
            }
        })

        .onGoInto( () -> {
            service.goInto(list.getSelectedValue(), () -> {
                dialog.close(0);
            });
            selectFirstIndex();
        })

        .onGoBack( () -> {
            service.goBack();
            selectFirstIndex();
        })

        .onEscape( () -> {
            this.dispose();
        })
        .build();


        list.setInputMap(JComponent.WHEN_FOCUSED, getMainViewKeyBinds());
        list.setActionMap(keyBindActions);

    }


    @Override
    public void dispose() {
        service.setPathListener(null);
        this.dialog.close(0);
        this.dialog = null;
        this.list = null;
    }
    
}
