package org.beuwi.msgbots.base;

import org.beuwi.msgbots.base.type.LogType;
import org.beuwi.msgbots.base.type.ToastType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 채팅 룸(GUI & Compiler)과의 소통을 위한 것임
public class Session {
    public static final Session GLOBAL = new Session("GLOBAL", true);

    private final Logger logger = new Logger(this);

    private Project project;
    private String name;
    private boolean global;

    private List<OnChatListener> onChatListeners = new ArrayList<>();
    private List<OnLogListener> onLogListeners = new ArrayList<>();
    private List<OnErrorListener> onErrorListeners = new ArrayList<>();
    private List<OnMessageListener> onMessageListeners = new ArrayList<>();
    private List<OnToastListener> onToastListeners = new ArrayList<>();

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
        for (OnChatListener listener : onChatListeners) {
            if (listener != null) {
                listener.onEvent(message, isBot);
            }
        }
    }

    // 주의: 프로그램과 소통을 위한 기능으로, Chat과는 완전 다름
    public void message(String message) {
        for (OnMessageListener listener : onMessageListeners) {
            if (listener != null) {
                listener.onEvent(message);
            }
        }
    }

    public void log(LogType type, String data) {
        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ").format(new Date());
        for (OnLogListener listener : onLogListeners) {
            if (listener != null) {
                listener.onEvent(type, date, data);
            }
        }
    }

    // Notice 이름도 좋을듯?
    public void toast(ToastType type, String title, String content) {
        for (OnToastListener listener : onToastListeners) {
            if (listener != null) {
                listener.onEvent(type, title, content);
            }
        }
    }

    // 디버그룸과 소통하기 위한 리스너
    public interface OnChatListener {
        void onEvent(String message, boolean isBot);
    }
    public interface OnLogListener {
        void onEvent(LogType type, String date, String data);
    }
    public interface OnErrorListener {
        void onEvent();
    }
    // 프로그램과 소통하기 위한 리스너
    public interface OnMessageListener {
        void onEvent(String message);
    }
    public interface OnToastListener {
        void onEvent(ToastType type, String title, String content);
    }

    public void addOnChatListener(OnChatListener listener) {
        onChatListeners.add(listener);
    }
    public void addOnLogListener(OnLogListener listener) {
        onLogListeners.add(listener);
    }
    public void addOnErrorListener(OnErrorListener listener) {
        onErrorListeners.add(listener);
    }
    public void addOnMessageListener(OnMessageListener listener) {
        onMessageListeners.add(listener);
    }
    public void addOnToastListener(OnToastListener listener) {
        onToastListeners.add(listener);
    }

    public String getName() {
        return name;
    }

    public Logger getLogger() {
        return logger;
    }

    /* public Project getProject() {
        return project;
    } */

    public boolean isGlobal() {
        return global;
    }
}
