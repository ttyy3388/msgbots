package org.beuwi.msgbots.compiler.api;

import org.beuwi.msgbots.base.Manager;
import org.beuwi.msgbots.base.Project;

import java.util.HashMap;
import java.util.Map;

/* class Debugger implements Manager {
	protected static void replyChat(Project project, String message, boolean isBot) {
		OnChatListener handler = CHAT_MAP.get(project);
		if (handler != null) {
			handler.onEvent(message, isBot);
		}
	}

	protected static void popupToast(Project project, int type, String title, String message) {
		OnToastListener handler = TOAST_MAP.get(project);
	    if (handler != null) {
	        handler.onEvent(type, title, message);
        }
    }

	protected interface OnChatListener {
		void onEvent(String message, boolean isBot);
	}
	protected interface OnToastListener {
		void onEvent(int type, String title, String message);
	}
	protected interface onErrorListener {
		void onEvent();
	}
	protected interface onCompileListener {
		void onEvent();
	}

	protected static final Map<Project, OnChatListener> CHAT_MAP = new HashMap<>();
	protected static void setOnChatListener(Project project, OnChatListener listener) {
		CHAT_MAP.put(project, listener);
	}

	protected static final Map<Project, OnToastListener> TOAST_MAP = new HashMap<>();
	protected static void setOnToastListener(Project project, OnToastListener listener) {
		TOAST_MAP.put(project, listener);
	}

	protected static final Map<Project, onErrorListener> ERROR_MAP = new HashMap<>();
	protected void setOnErrorListener(Project project, onErrorListener listener) {
		ERROR_MAP.put(project, listener);
	}

	protected static final Map<Project, onCompileListener> COMPILE_MAP = new HashMap<>();
	protected void setOnCompileListener(Project project, onCompileListener listener) {
		COMPILE_MAP.put(project, listener);
	}
} */