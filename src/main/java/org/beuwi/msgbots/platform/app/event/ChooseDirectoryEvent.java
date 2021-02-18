package org.beuwi.msgbots.platform.app.event;

import javafx.event.Event;
import javafx.event.EventType;

public class ChooseDirectoryEvent extends Event {

    private String path;

    public static final EventType<ChooseDirectoryEvent> CUSTOM = new EventType(ANY, "CUSTOM");
    public ChooseDirectoryEvent(String path) {
        super(ChooseDirectoryEvent.CUSTOM);
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

}
