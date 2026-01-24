package com.crhistianm.netrwoon.components;

import javax.swing.JTextField;

import com.crhistianm.netrwoon.utils.CommandOperationType;

public class CommandTextField extends JTextField {

    private CommandOperationType currentOperation = CommandOperationType.NONE;

    CommandTextField() {
        this.setEnabled(false);
        this.setEditable(false);
        this.setFocusable(true);
        this.setSize(20, 20);
    }

    public void setCurrentOperation(CommandOperationType currentOperation) {
        this.currentOperation = currentOperation;
    }

    public CommandOperationType getCurrentOperation() {
        return currentOperation;
    }

}
