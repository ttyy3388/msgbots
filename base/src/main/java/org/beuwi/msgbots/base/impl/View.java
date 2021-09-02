package org.beuwi.msgbots.base.impl;

public interface View {
    /* default void init() {
        return ;
    } // throws Exception */

    // Node getRoot(); // Return Main Panel

    // Return Main Control
    /* default Node getControl() {
        return null;
    } */

	// ObservableMap<String, Object> getNamespace(); // findById
    Object findById(String id);
}