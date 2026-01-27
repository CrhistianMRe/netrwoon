package com.crhistianm.netrwoon.controllers;


import javax.swing.ActionMap;

import static com.crhistianm.netrwoon.utils.Utils.createAction;

import static java.util.Objects.isNull;

class MainViewKeyActionsBuilder{

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

    private void validateBuild() {
        if(isNull(this.onMoveDown)) throw new IllegalStateException("onMoveDown action missing!");
        if(isNull(this.onMoveUp)) throw new IllegalStateException("onMoveUp action missing!");
    }

    ActionMap build() {
        validateBuild();
        ActionMap map = new ActionMap();
        
        //Movement
        map.put("moveUp", createAction(this.onMoveUp));
        map.put("moveDown", createAction(this.onMoveDown));

        return map;
    }


}
