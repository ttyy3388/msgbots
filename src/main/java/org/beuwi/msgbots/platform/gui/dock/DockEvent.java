package org.beuwi.msgbots.platform.gui.dock;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class DockEvent extends Event {

    public static final EventType<DockEvent> ANY = new EventType<>(Event.ANY, "DOCK");

    public static final EventType<DockEvent> DOCK_OVER = new EventType<DockEvent>(DockEvent.ANY, "DOCK_OVER");

    public static final EventType<DockEvent> DOCK_EXIT = new EventType<DockEvent>(DockEvent.ANY, "DOCK_EXIT");

    public static final EventType<DockEvent> DOCK_RELEASED = new EventType<DockEvent>(DockEvent.ANY, "DOCK_RELEASED");

    public DockEvent(Object source, EventTarget target, EventType<? extends DockEvent> type)
    {
        super(source, target, type);
    }

}