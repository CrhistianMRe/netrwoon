package com.crhistianm.netrwoon.services;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;

import com.crhistianm.netrwoon.states.NetrwoonState;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

@Service(Service.Level.PROJECT)
public final class NetrwoonService {

    private final NetrwoonState state; 


    private Consumer<String> pathListener;

    public NetrwoonService(FileEditorManager fileEditorManager, NetrwoonState state) {
        this.state = state;
    }

    public NetrwoonService(Project project) {
        this.state = new NetrwoonState();
    }

    private void loadList(){
        state.getCurrentDirectoryList().clear();
        Arrays.stream(state.getCurrentDirectory().getChildren()).forEach(file -> {
            if(file.isDirectory()){
                state.getCurrentDirectoryList().addElement(file.getName() + "/");
            }
            if(!file.isDirectory()){
                state.getCurrentDirectoryList().addElement(file.getName());
            }
        });
        sortListByDirectory();
    }

    private void sortListByDirectory(){
        List<String> temp = Arrays.stream(state.getCurrentDirectoryList().toArray()).map(String::valueOf).collect(Collectors.toList());
        temp.sort(new CustomComparator());
        state.getCurrentDirectoryList().clear();
        temp.forEach(state.getCurrentDirectoryList()::addElement);
    }

    private class CustomComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            int result = 0;
            if(o1.contains("/")) result -=1;
            if(o2.contains("/")) result += 1;
            if(result == 0) result = o1.compareTo(o2);

            return result;
        }

    }

    public void goBack(){
        setCurrentDirectory(state.getCurrentDirectory().getParent());
    }

    public DefaultListModel<String> getList() {
        return this.state.getCurrentDirectoryList();
    }

    public String getCurrentPath() {
        return this.state.getCurrentDirectory().getPath();
    }

    private void notifyPathChanged() {
        if(pathListener != null){
            pathListener.accept(getCurrentPath());
        }
    }

    public void setPathListener(Consumer<String> listener) {
        pathListener = listener;
    }

    public void setCurrentDirectory(VirtualFile currentDirectory) {
        this.state.setCurrentDirectory(currentDirectory);
        loadList();
        notifyPathChanged();
    }

    private String removeSlash(String arg){ 
        return arg.replace("/", ""); 
    }

}
