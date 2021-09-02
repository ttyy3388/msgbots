package org.beuwi.msgbots.view.gui.control;

import javafx.collections.ListChangeListener;

import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.base.JArray;
import org.beuwi.msgbots.base.JObject;
import org.beuwi.msgbots.manager.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LogView extends ListView<LogItem> {
    public LogView() {

        // Init List
        /* if (project != null) {
            setProject(project);
        } */

        getItems().addListener((ListChangeListener<LogItem>) change -> {
            while (change.next()) {
                for (LogItem item : change.getRemoved()) {
                    item.setView(null);
                }
                for (LogItem item : change.getAddedSubList()) {
                    item.setView(this);
                }
            }
        });

        getStyleClass().add("log-view");
    }

    public void load(File file) {
        // 파일이 없거나 제거됐다면 파일 생성
        if (!file.exists()) {
            FileManager.write(file, "[]");
        }
        List<LogItem> list = new ArrayList<>();
        JArray array = new JArray(file);
        for (Object object : array) {
            JObject json = (JObject) object;
            if (!object.toString().equals("{}") || (json.size() > 0)) {
                list.add(new LogItem((JObject) object));
            }
        }
        getItems().setAll(list);
    }

    public void load(Project project) {
        File file = project.getFile("log.json");
        if (file != null && file.exists()) { load(file); }
    }

    @Override
    public LogItem findById(String name) {
        for (LogItem item : getItems()) {
            if (item.getId().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /* public void setProject(Project project) {
        projectProperty.set(project);
    }
    public Project getProject() {
        return projectProperty.get();
    }
    public ObjectProperty<Project> projectProperty() {
        return projectProperty;
    } */
}