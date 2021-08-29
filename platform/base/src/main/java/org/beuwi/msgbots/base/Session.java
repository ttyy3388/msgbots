package org.beuwi.msgbots.base;

import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.base.enums.LogType;

// 채팅 룸(GUI & Compiler)과의 소통을 위한 것임
public class Session {
    public static final Session GLOBAL = new Session("GLOBAL", true);

    private Project project;
    private String name;
    private boolean global;

    private OnChatListener onChatListener;
    private OnLogListener onLogListener;
    private onErrorListener onErrorListener;
    private onCompileListener onCompileListener;

    public Session(Project project) {
        this(project.getName(), false);
    }

    public Session(String name, boolean global) {
        this.name = name;
        this.global = global;
    }

    // 어차피 컴파일러에서 챗 보내는 경우는 봇이 보내는 경우밖에 없으므로
    // "IsBot()"은 항상 TRUE 값을 반환하므로 생략함
    public void chat(String message) {
        OnChatListener listener = onChatListener;
        if (listener != null) {
            listener.onEvent(message);
        }
    }
    public void log(LogType type, String data, String date) {
        OnLogListener listener = onLogListener;
        if (listener != null) {
            listener.onEvent(type, data, date);
        }
    }

    public interface OnChatListener {
        void onEvent(String message);
    }
    public interface OnLogListener {
        void onEvent(LogType type, String date, String data);
    }
    public interface onErrorListener {
        void onEvent();
    }
    public interface onCompileListener {
        void onEvent();
    }

    public void setOnChatListener(OnChatListener listener) {
        onChatListener = listener;
    }
    public void setOnLogListener(OnLogListener listener) {
        onLogListener = listener;
    }
    public void setOnErrorListener(onErrorListener listener) {
        onErrorListener = listener;
    }
    public void setOnCompileListener(onCompileListener listener) {
        onCompileListener = listener;
    }

    public String getName() {
        return name;
    }

    /* public Project getProject() {
        return project;
    } */

    public boolean isGlobal() {
        return global;
    }
}
