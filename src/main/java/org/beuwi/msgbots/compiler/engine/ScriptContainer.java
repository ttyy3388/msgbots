package org.beuwi.msgbots.compiler.engine;

import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptableObject;

public class ScriptContainer 
{
	private Function responder = null;
	private ScriptableObject execscope = null;
	private Function onStartCompile = null;
	private ScriptableObject scope = null;
	private int optimization = 0;

	private Function onCreate = null;
	private Function onStop = null;
	private Function onResume = null;
	private Function onPause = null;

	public void constructor(Function responder, ScriptableObject execscope, Function onStartCompile, ScriptableObject scope)
	{
		this.responder = responder;
		this.execscope = execscope;
		this.onStartCompile = onStartCompile;
		this.scope = scope;
	}

	public ScriptContainer setResponder(Function responder)
	{
		this.responder = responder;
		return this;
	}

	public ScriptContainer setExecScope(ScriptableObject execscope)
	{
		this.execscope = execscope;
		return this;
	}

	public ScriptContainer setOnStartCompile(Function onStartCompile)
	{
		this.onStartCompile = onStartCompile;
		return this;
	}

	public ScriptContainer setOptimization(int optimization)
	{
		this.optimization = optimization;
		return this;
	}

	public ScriptableObject getScope()
	{
		return scope;
	}

	public ScriptContainer setScope(ScriptableObject scope)
	{
		this.scope = scope;
		return this;
	}

	public ScriptContainer setScriptActivity(Function onCreate, Function onStop, Function onResume, Function onPause)
	{
		this.onCreate = onCreate;
		this.onStop = onStop;
		this.onResume = onResume;
		this.onPause = onPause;
		return this;
	}

	public ScriptableObject getExecScope()
	{
		return execscope;
	}

	public Function getOnStartCompile()
	{
		return onStartCompile;
	}

	public Function getResponder()
	{
		return responder;
	}

	public int getOptimization()
	{
		return optimization;
	}
}