package org.beuwi.msgbots.compiler.manager;

import org.beuwi.msgbots.base.Manager;
import org.beuwi.msgbots.base.Project;

import java.util.HashMap;
import java.util.Map;

public class CompileManager implements Manager {
	public static void sendChat(Project project, String message, boolean isBot) {
		OnChatListener handler = CHAT_MAP.get(project);
		if (handler != null) {
			handler.onEvent(message, isBot);
		}
	}

	public static void sendToast(Project project, int type, String title, String message) {
		OnToastListener handler = TOAST_MAP.get(project);
	    if (handler != null) {
	        handler.onEvent(type, title, message);
        }
    }

    public interface OnChatListener {
		void onEvent(String message, boolean isBot);
	}
	public interface OnToastListener {
		void onEvent(int type, String title, String message);
	}
	public interface onErrorListener {
		void onEvent();
	}
	public interface onCompileListener {
		void onEvent();
	}

	public static final Map<Project, OnChatListener> CHAT_MAP = new HashMap<>();
	public static void setOnChatListener(Project project, OnChatListener listener) {
		CHAT_MAP.put(project, listener);
	}

	public static final Map<Project, OnToastListener> TOAST_MAP = new HashMap<>();
	public static void setOnToastListener(Project project, OnToastListener listener) {
		TOAST_MAP.put(project, listener);
	}

	public static final Map<Project, onErrorListener> ERROR_MAP = new HashMap<>();
	public void setOnErrorListener(Project project, onErrorListener listener) {
		ERROR_MAP.put(project, listener);
	}

	public static final Map<Project, onCompileListener> COMPILE_MAP = new HashMap<>();
	public void setOnCompileListener(Project project, onCompileListener listener) {
		COMPILE_MAP.put(project, listener);
	}
}
