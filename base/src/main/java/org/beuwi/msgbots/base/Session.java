package org.beuwi.msgbots.base;

import org.beuwi.msgbots.base.type.LogType;
import org.beuwi.msgbots.base.type.ToastType;

import java.text.SimpleDateFormat;
import java.util.Date;

// 채팅 룸(GUI & Compiler)과의 소통을 위한 것임
public class Session {
    public static final Session GLOBAL = new Session("GLOBAL", true);

    private Project project;
    private String name;
    private boolean global;

    private OnChatListener onChatListener;
    private OnLogListener onLogListener;
    private OnErrorListener onErrorListener;
    private OnCompileListener onCompileListener;
    private OnToastListener onToastListener;

    public Session(Project project) {
        this(project.getName(), false);
    }

    public Session(String name, boolean global) {
        this.name = name;
        this.global = global;
    }

    // 어차피 컴파일러에서 챗 보내는 경우는 봇이 보내는 경우밖에 없으므로
    // "IsBot()"은 항상 TRUE 값을 반환하므로 생략함
    public void chat(String message, boolean isBot) {
        OnChatListener listener = onChatListener;
        if (listener != null) {
            listener.onEvent(message, isBot);
        }
    }

    public void log(LogType type, String data) {
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ").format(new Date());
        OnLogListener listener = onLogListener;
        if (listener != null) {
            listener.onEvent(type, date, data);
        }
    }

    // Notice 이름도 좋을듯?
    public void toast(ToastType type, String title, String content) {
        OnToastListener listener = onToastListener;
        if (listener != null) {
            listener.onEvent(type, title, content);
        }
    }

    public interface OnChatListener {
        void onEvent(String message, boolean isBot);
    }
    public interface OnLogListener {
        void onEvent(LogType type, String date, String data);
    }
    public interface OnErrorListener {
        void onEvent();
    }
    public interface OnCompileListener {
        void onEvent();
    }
    public interface OnToastListener {
        void onEvent(ToastType type, String title, String content);
    }

    public void setOnChatListener(OnChatListener listener) {
        onChatListener = listener;
    }
    public void setOnLogListener(OnLogListener listener) {
        onLogListener = listener;
    }
    public void setOnErrorListener(OnErrorListener listener) {
        onErrorListener = listener;
    }
    public void setOnCompileListener(OnCompileListener listener) {
        onCompileListener = listener;
    }
    public void setOnToastListener(OnToastListener listener) {
        onToastListener = listener;
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
