package com.crhistianm.netrwoon.controllers;


import java.util.Arrays;

import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import com.crhistianm.netrwoon.components.CommandTextField;
import com.crhistianm.netrwoon.components.NetrwoonDialogWrapper;

import static com.crhistianm.netrwoon.controllers.KeyBinds.getCommandFieldKeyBinds;
import static com.crhistianm.netrwoon.controllers.KeyBinds.getMainViewKeyBinds;
import static com.crhistianm.netrwoon.utils.Utils.createPropertyListener;
import com.crhistianm.netrwoon.services.NetrwoonService;
import com.crhistianm.netrwoon.utils.CommandOperationType;

import static com.crhistianm.netrwoon.utils.CommandOperationType.*;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.IdeFocusManager;
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
        loadCommandFieldBindings();
        loadCommandFieldListeners();
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
    private void requestFocus(JComponent component) {
        IdeFocusManager.getInstance(this.project).requestFocus(component, true);
    }

    private void loadListBindings() {

        CommandTextField commandTextField = dialog.getCommandTextField();
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
        
        .onCreateFile( () -> {
            dialog.setCommandLabelText("Creating file: ");
            commandTextField.setEnabled(true);
            commandTextField.setCurrentOperation(CREATE_FILE);
        })

        .onCreateDirectory( () -> {
            dialog.setCommandLabelText("Creating directory: ");
            commandTextField.setEnabled(true);
            commandTextField.setCurrentOperation(CREATE_DIRECTORY);
        })
        .build();


        list.setInputMap(JComponent.WHEN_FOCUSED, getMainViewKeyBinds());
        list.setActionMap(keyBindActions);

    }

    private void loadCommandFieldListeners() {
        dialog.getCommandTextField().addPropertyChangeListener("enabled", createPropertyListener( evt -> {
            Boolean enabled = (Boolean) evt.getNewValue();
            if(enabled){
                SwingUtilities.invokeLater(() ->{
                    dialog.getCommandTextField().setEditable(true);
                    requestFocus(dialog.getCommandTextField());
                });
            }
            if(!enabled){
                SwingUtilities.invokeLater(() ->{
                    dialog.getCommandTextField().setText("");
                    dialog.setCommandLabelText("");
                    dialog.getCommandTextField().setCurrentOperation(NONE);
                    requestFocus(list);
                    selectFirstIndex();
                });
            }
        }));
    }

    private void loadCommandFieldBindings() {
        ActionMap actualActions = dialog.getCommandTextField().getActionMap();
        ActionMap keyBindActions = new CommandFieldKeyActionsBuilder()

        .onEnter(() -> {
            CommandOperationType operation = dialog.getCommandTextField().getCurrentOperation();
            if(operation.equals(CREATE_FILE)){
                service.createFile(dialog.getCommandTextField().getText());
            }
            if(operation.equals(CREATE_DIRECTORY)){
                service.createDirectory(dialog.getCommandTextField().getText());
            }
            dialog.getCommandTextField().setEnabled(false);
        })

        .onEscape(() -> {
            dialog.getCommandTextField().setEnabled(false);
        }).build();

        InputMap actualInputs = dialog.getCommandTextField().getInputMap();
        InputMap customInputs = getCommandFieldKeyBinds();

        Arrays.stream(keyBindActions.allKeys()).forEach(key -> {
            actualActions.put(key, keyBindActions.get(key));
        });
        
        Arrays.stream(customInputs.allKeys()).forEach(key -> {
            actualInputs.put(key, customInputs.get(key));
        });
    
    }

    @Override
    public void dispose() {
        service.setPathListener(null);
        this.dialog.close(0);
        this.dialog = null;
        this.list = null;
    }
    
}
