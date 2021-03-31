package org.beuwi.msgbots.openapi;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCombination;

public class KeyMap {
	final String name; // 해당 키맵이 설명될 이름
	final KeyCombination key; // 해당 키맵의 키(Ctrl + N)
	final EventHandler handler; // 키맵 매칭 시 실행될 코드 등록

	public KeyMap(String name, KeyCombination key, EventHandler handler) {
		this.name = name;
		this.key = key;
		this.handler = handler;
	}

	public KeyCombination getKey() {
		return key;
	}

	public EventHandler getAction() {
		return handler;
	}

	public String getName() {
		return name;
	}
}
