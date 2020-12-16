package org.beuwi.msgbots.platform.app.impl;

public interface Action
{
	default void init() {
		return ;
	};

	String getName(); // getActionName();
}