package com.crhistianm.netrwoon.components;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

class CommandPanel extends JPanel{

    private CommandTextField commandTextField;

    private JLabel commandLabel;

    CommandPanel() {
        commandTextField = new CommandTextField();
        commandLabel = new JLabel();
        commandLabel.setSize(commandLabel.getSize().width +1, commandLabel.getSize().height + 1);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        commandLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        commandTextField.setAlignmentY(Component.CENTER_ALIGNMENT);

        add(commandLabel);
        add(Box.createHorizontalStrut(8));
        add(commandTextField);
    }

    CommandTextField getCommandTextField() {
        return commandTextField;
    }
    
    JLabel getCommandLabel() {
        return commandLabel;
    }
    
}
