package com.crhistianm.netrwoon.controllers;


import javax.swing.InputMap;
import javax.swing.KeyStroke;

class KeyBinds {

    static InputMap getMainViewKeyBinds() {
        InputMap im = new InputMap();
        im.put(KeyStroke.getKeyStroke('-'), "goBack");
        im.put(KeyStroke.getKeyStroke('k'), "moveUp");
        im.put(KeyStroke.getKeyStroke('j'), "moveDown");
        return im;
    }

}
