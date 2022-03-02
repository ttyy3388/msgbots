package org.beuwi.msgbots.view.gui.control.base;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

import org.beuwi.msgbots.view.gui.base.Control;

public class SplitViewBase extends javafx.scene.control.SplitPane implements Control {
    @Override public void setPseudoClass(String pseudo, boolean active) { setPseudoClass(this, pseudo, active); }

    @Override public <P> ObservableValue<P> getFXProperty(String name) { return getFXProperty(this, name); }

    @Override public <C> void addChangeListener(String property, ChangeListener<C> listener) { addChangeListener(getFXProperty(property), listener); }
    @Override public void addChangeListener(String property, InvalidationListener listener) { addChangeListener(getFXProperty(property), listener); }
    @Override public void removeChangeListener(String property, ChangeListener listener) { removeChangeListener(getFXProperty(property), listener); }
    @Override public void removeChangeListener(String property, InvalidationListener listener) { removeChangeListener(getFXProperty(property), listener); }

    @Override public Node findById(String id) { return findById(this, id); }

    @Override public void addStyleClass(String... style) { addStyleClass(this, style); }
    @Override public void addStyleClass(int index, String style) { addStyleClass(this, index, style); }
    @Override public void setStyleClass(int index, String style) { setStyleClass(this, index, style); }
    @Override public void initStyleClass(String... style) { initStyleClass(this, style); }
}
