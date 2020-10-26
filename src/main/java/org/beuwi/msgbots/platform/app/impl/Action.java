package org.beuwi.msgbots.platform.app.impl;

public interface Action
{
	default void init() {
		return ;
	};

	/* public static void execute() {

	}; */

	String getName(); // getActionName();
}