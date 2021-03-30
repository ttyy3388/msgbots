package org.beuwi.msgbots.openapi;

import java.util.EventListener;

public interface FileListener extends EventListener {
    public void changed();
    // public void created();
    // public void deleted();
    // public void modified();
}