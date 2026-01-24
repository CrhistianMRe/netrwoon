package com.crhistianm.netrwoon.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.components.JBList;

public class NetrwoonDialogWrapper extends DialogWrapper {

    private final NetrwoonMainView mainView;

    public NetrwoonDialogWrapper(JBList<String> list, Project project) {
        super(true);
        getRootPane().getInputMap().clear();
        loadSize(project);
        mainView = new NetrwoonMainView(list);
        setTitle("Netrwoon");
        init();
    }

    private void loadSize(Project project){
        Dimension ideSize = WindowManager.getInstance().getFrame(project).getSize();

        this.setSize(ideSize.width, ideSize.height);
    }

    @Override
    //Remove ok and cancel buttons
    protected @NotNull JPanel createButtonsPanel(@NotNull List<? extends JButton> buttons) {
        return new JPanel();
    }

    @Override
    //Remove esc event
    protected @Nullable ActionListener createCancelAction() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {}
        };
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return mainView.getPanel();
    }

    public void setPathText(String text) {
        mainView.getPathLabel().setText(text);
    }

    public void setCommandLabelText(String text) {
        mainView.getCommandLabel().setText(text);
    }

    public CommandTextField getCommandTextField() {
        return mainView.getCommandTextField();
    }

}
