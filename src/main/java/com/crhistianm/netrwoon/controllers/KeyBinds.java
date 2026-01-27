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
        return im;
    }
    
}
