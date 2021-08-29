package org.beuwi.msgbots.program.app.impl;

import javafx.event.EventHandler;

import org.beuwi.msgbots.program.app.keyboard.KeyBinding;

public class Action {
    private final String text;
    private final KeyBinding binding;
    private final EventHandler handler;

    public Action(String text) {
        this(text, null);
    }

    public Action(String text, KeyBinding binding) {
        this(text, binding, null);
    }

    public Action(String text, KeyBinding binding, EventHandler handler) {
        this.text = text;
        this.binding = binding;
        this.handler = handler;
    }

    public String getName() {
        return text;
    }
    public KeyBinding getKeyBinding() {
        return binding;
    }
    public EventHandler getHandler() {
        return handler;
    }

    public void execute() {
        if (handler != null) {
            handler.handle(null);
        }
    }
}