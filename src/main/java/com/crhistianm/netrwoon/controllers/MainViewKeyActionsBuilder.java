package com.crhistianm.netrwoon.controllers;


import javax.swing.ActionMap;

import static com.crhistianm.netrwoon.utils.Utils.createAction;

import static java.util.Objects.isNull;

class MainViewKeyActionsBuilder{

    private Runnable onEscape;

    private Runnable onGoInto;

    private Runnable onGoBack;

    private Runnable onMoveUp;

    private Runnable onMoveDown;

    private Runnable onCreateFile;

    private Runnable onCreateDirectory;

    private Runnable onDeleteSelected;

    private Runnable onRenameSelected;

    MainViewKeyActionsBuilder onRenameSelected(Runnable onRenameSelected) {
        this.onRenameSelected = onRenameSelected;
        return this;
    }


    MainViewKeyActionsBuilder onDeleteSelected(Runnable onDeleteSelected) {
        this.onDeleteSelected = onDeleteSelected;
        return this;
    }


    MainViewKeyActionsBuilder onCreateDirectory(Runnable onCreateDirectory) {
        this.onCreateDirectory = onCreateDirectory;
        return this;
    }


    MainViewKeyActionsBuilder onCreateFile(Runnable onCreateFile) {
        this.onCreateFile = onCreateFile;
        return this;
    }


    MainViewKeyActionsBuilder onMoveDown(Runnable onMoveDown) {
        this.onMoveDown = onMoveDown;
        return this;
    }


    MainViewKeyActionsBuilder onMoveUp(Runnable onMoveUp) {
        this.onMoveUp = onMoveUp;
        return this;
    }


    MainViewKeyActionsBuilder onGoBack(Runnable onGoBack) {
        this.onGoBack = onGoBack;
        return this;
    }

    MainViewKeyActionsBuilder onGoInto(Runnable onGoInto) {
        this.onGoInto = onGoInto;
        return this;
    }

    MainViewKeyActionsBuilder onEscape(Runnable onEscape) {
        this.onEscape = onEscape;
        return this;
    }

    private void validateBuild() {
        if(isNull(this.onRenameSelected)) throw new IllegalStateException("onRenameSelected action missing!");
        if(isNull(this.onDeleteSelected)) throw new IllegalStateException("onDeleteSelected action missing!");
        if(isNull(this.onCreateDirectory)) throw new IllegalStateException("onCreateDirectory action missing!");
        if(isNull(this.onCreateFile)) throw new IllegalStateException("onCreateFile action missing!");
        if(isNull(this.onMoveDown)) throw new IllegalStateException("onMoveDown action missing!");
        if(isNull(this.onMoveUp)) throw new IllegalStateException("onMoveUp action missing!");
        if(isNull(this.onGoBack)) throw new IllegalStateException("onGoBack action missing!");
        if(isNull(this.onGoInto)) throw new IllegalStateException("onGoInto action missing!");
        if(isNull(this.onEscape)) throw new IllegalStateException("onEscape action missing!");
    }

    ActionMap build() {
        validateBuild();
        ActionMap map = new ActionMap();
        
        map.put("goInto", createAction(this.onGoInto));
        map.put("goBack", createAction(this.onGoBack));
        map.put("escape", createAction(this.onEscape));

        //Movement
        map.put("moveUp", createAction(this.onMoveUp));
        map.put("moveDown", createAction(this.onMoveDown));

        //Persistence
        map.put("createFile", createAction(this.onCreateFile));
        map.put("createDirectory", createAction(this.onCreateDirectory));
        map.put("deleteSelected", createAction(this.onDeleteSelected));
        map.put("renameSelected", createAction(this.onRenameSelected));
        return map;
    }


}
