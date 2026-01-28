package com.crhistianm.netrwoon.utils;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Consumer;

import javax.swing.AbstractAction;

public class Utils {

    public static AbstractAction createAction(Runnable onAction) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAction.run();
            }
        };
    }

    public static PropertyChangeListener createPropertyListener(Consumer<PropertyChangeEvent> onAction){
        return new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                onAction.accept(evt);
            }
        };
    }

}
