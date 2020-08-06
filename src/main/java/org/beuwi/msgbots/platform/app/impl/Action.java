package org.beuwi.msgbots.platform.app.impl;

public interface Action
{
	// void init();

	void execute();

	String getName(); // getActionName();
}