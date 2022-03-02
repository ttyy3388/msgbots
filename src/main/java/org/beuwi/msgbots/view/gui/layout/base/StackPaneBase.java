package org.beuwi.msgbots.view.gui.layout.base;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

import org.beuwi.msgbots.view.gui.base.Layout;

import java.util.Collection;

public class StackPaneBase extends javafx.scene.layout.StackPane implements Layout {
    @Override public void setPseudoClass(String pseudo, boolean active) { setPseudoClass(this, pseudo, active); }

    @Override public <P> ObservableValue<P> getFXProperty(String name) { return getFXProperty(this, name); }

    @Override public <C> void addChangeListener(String property, ChangeListener<C> listener) { addChangeListener(getFXProperty(property), listener); }
    @Override public void addChangeListener(String property, InvalidationListener listener) { addChangeListener(getFXProperty(property), listener); }
    @Override public void removeChangeListener(String property, ChangeListener listener) { removeChangeListener(getFXProperty(property), listener); }
    @Override public void removeChangeListener(String property, InvalidationListener listener) { removeChangeListener(getFXProperty(property), listener); }

    // @Override public void addChild(Node child) { addChild(this, child); }
    @Override public void addChild(int index, Node child) { addChild(this, index, child); }
    @Override public void addChildren(Node... children) { addChildren(this, children); }
    @Override public void addChildren(Collection<? extends Node> children) { addChildren(this, children); }
    @Override public void setChild(int index, Node child) { setChild(this, index, child); }
    @Override public void setChildren(Node... children) { setChildren(this, children); }
    @Override public void setChildren(Collection<? extends Node> children) { setChildren(this, children); }
    @Override public void initChildren(Node... children) { initChildren(this, children); }
    @Override public void initChildren(Collection<? extends Node> children) { initChildren(this, children); }

    @Override public Node findById(String id) { return findById(this, id); }

    @Override public void addStyleClass(String... style) { addStyleClass(this, style); }
    @Override public void addStyleClass(int index, String style) { addStyleClass(this, index, style); }
    @Override public void setStyleClass(int index, String style) { setStyleClass(this, index, style); }
    @Override public void initStyleClass(String... style) { initStyleClass(this, style); }
}