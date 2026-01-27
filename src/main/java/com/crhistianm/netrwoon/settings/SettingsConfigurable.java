package com.crhistianm.netrwoon.settings;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.crhistianm.netrwoon.states.SettingsState;
import com.crhistianm.netrwoon.states.SettingsState.State;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.util.NlsContexts.ConfigurableName;
import static com.crhistianm.netrwoon.utils.Constants.SETTINGS_HEADER;


public class SettingsConfigurable implements Configurable {

    private JTextField widthField;
    private JTextField heightField;

    private State settingsState;

    public SettingsConfigurable() {
    }

    @Override
    public JComponent createComponent() {
        settingsState = SettingsState.getInstance().getState();
        JPanel panel = new JPanel();

        widthField = new JTextField(String.valueOf(settingsState.width));
        heightField = new JTextField(String.valueOf(settingsState.height));

        panel.add(new JLabel("Width:"));
        panel.add(widthField);
        panel.add(new JLabel("Height:"));
        panel.add(heightField);

        return panel;
    }

    @Override
    public void apply() {
        SettingsState.State state = SettingsState.getInstance().getState();
        state.width = Integer.parseInt(widthField.getText());
        state.height = Integer.parseInt(heightField.getText());
    }

    @Override
    public boolean isModified() {
        return settingsState.width != Integer.parseInt(widthField.getText()) ||
            settingsState.height != Integer.parseInt(heightField.getText());
    }

    @Override
    public void reset() {
        widthField.setText(String.valueOf(settingsState.width));
        heightField.setText(String.valueOf(settingsState.height));
    }

    @Override
    public @ConfigurableName String getDisplayName() {
        return SETTINGS_HEADER;
    }
}

