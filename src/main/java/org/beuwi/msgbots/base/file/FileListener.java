package org.beuwi.msgbots.base.file;

import java.util.EventListener;

public interface FileListener extends EventListener {
    public void changed();
    // public void created();
    // public void deleted();
    // public void modified();
}