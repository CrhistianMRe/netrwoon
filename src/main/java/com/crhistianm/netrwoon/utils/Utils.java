package com.crhistianm.netrwoon.utils;

import java.awt.event.ActionEvent;

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

}
