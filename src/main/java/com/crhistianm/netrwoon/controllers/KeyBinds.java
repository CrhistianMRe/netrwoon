package com.crhistianm.netrwoon.controllers;

import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.KeyStroke;

class KeyBinds {

    static InputMap getMainViewKeyBinds() {
        InputMap im = new InputMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "goInto");
        im.put(KeyStroke.getKeyStroke('-'), "goBack");
        im.put(KeyStroke.getKeyStroke('k'), "moveUp");
        im.put(KeyStroke.getKeyStroke('j'), "moveDown");
        im.put(KeyStroke.getKeyStroke('%'), "createFile");
        im.put(KeyStroke.getKeyStroke('d'), "createDirectory");
        return im;
    }

    static InputMap getCommandFieldKeyBinds() {
        InputMap im = new InputMap();
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");
        return im;
    }
    
}
