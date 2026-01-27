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

        return map;
    }


}
