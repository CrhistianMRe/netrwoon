package com.crhistianm.netrwoon.controllers;

import static com.crhistianm.netrwoon.utils.Utils.createAction;
import static java.util.Objects.isNull;

import javax.swing.ActionMap;

class CommandFieldKeyActionsBuilder {

    Runnable onEnter;

    Runnable onEscape;

    CommandFieldKeyActionsBuilder onEnter(Runnable onEnter){
        this.onEnter = onEnter;
        return this;
    }
    CommandFieldKeyActionsBuilder onEscape(Runnable onEscape){
        this.onEscape = onEscape;
        return this;
    }

    private void validateBuild() {
        if(isNull(onEnter)) throw new IllegalStateException("onEnter action missing!");
        if(isNull(onEscape)) throw new IllegalStateException("onEscpe action missing!");
    }

    ActionMap build() {
        validateBuild();
        ActionMap map = new ActionMap();
        map.put("enter", createAction(this.onEnter));
        map.put("escape", createAction(this.onEscape));
        return map;
    }

    
}
