package org.beuwi.msgbots.platform.app.impl;

public interface View
{
    default void init() {
        return ;
    } /* throws Exception */;

	// T getRoot(); : return Main Panel

    // T getComponent(); : return Main Component

	// ObservableMap<String, Object> getNamespace(); : findById
}