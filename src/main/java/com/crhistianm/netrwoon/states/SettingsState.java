package com.crhistianm.netrwoon.states;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.*;

@State(
    name = "com.crhistianm.netrwoon.states.SettingsState",
    storages = @Storage("netrwoon.xml")
)
public class SettingsState implements PersistentStateComponent<SettingsState.State> {

    public static class State {
        public int width = 0;
        public int height = 0;
    }

    private State state = new State();

    public static SettingsState getInstance() {
        return ApplicationManager.getApplication().getService(SettingsState.class);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void loadState(State state) {
        this.state = state;
    }
}

    
