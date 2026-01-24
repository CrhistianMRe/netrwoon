package com.crhistianm.netrwoon.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBList;
import static com.crhistianm.netrwoon.utils.Constants.HELP_LABEL_TEXT;

class NetrwoonMainView{

    private JLabel pathLabel;

    private JLabel helpLabel;
    
    private JPanel panel;

    private JScrollPane scrollListPane;

    private CommandPanel commandPanel;

    public NetrwoonMainView(){
        panel = new JPanel(new GridBagLayout());
        pathLabel = new JLabel();
        helpLabel = new JLabel(HELP_LABEL_TEXT);
        commandPanel = new CommandPanel();
        scrollListPane = new JScrollPane();

        panel.setFocusable(false);
    }

    public NetrwoonMainView(JBList<String> listComponent){
        this();
        loadIdeStyle();
        listComponent.setBackground(JBColor.background());
        listComponent.setForeground(JBColor.foreground());
        loadComponents(listComponent);
    }

    private void loadComponents(JBList<String> listComponent){
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.0;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(pathLabel, c);

        c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(helpLabel, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 1.0;
        c.weighty = 0.9;
        c.fill = GridBagConstraints.BOTH;
        scrollListPane.setViewportView(listComponent);
        panel.add(scrollListPane, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 1.0; 
        c.weighty = 0.0; 
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(commandPanel, c);

    }


    private void loadIdeStyle(){
        Color colorBackground = JBColor.background();
        Color colorForeground = JBColor.foreground();
        Font font = UIManager.getFont("List.font");
        panel.setBackground(colorBackground);
        panel.setForeground(colorForeground);
        panel.setFont(font);
        scrollListPane.setBackground(colorBackground);
        scrollListPane.setForeground(colorForeground);
        scrollListPane.setFont(font);
        scrollListPane.setBorder(null);
        pathLabel.setFont(font);
        pathLabel.setBackground(colorBackground);
        getCommandTextField().setBackground(colorBackground);
        getCommandTextField().setBorder(BorderFactory.createEmptyBorder());
        getCommandTextField().setDisabledTextColor(Color.WHITE);
        getCommandTextField().setFont(font);
    }

    JLabel getCommandLabel() {
        return commandPanel.getCommandLabel();
    }

    JLabel getPathLabel() {
        return pathLabel;
    }

    CommandTextField getCommandTextField() {
        return commandPanel.getCommandTextField();
    }

    JPanel getPanel() {
        return panel;
    }

    JScrollPane getScrollListPane(){
        return this.scrollListPane;
    }

}
