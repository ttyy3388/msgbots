package org.beuwi.msgbots.platform.gui.editor;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Theme
{
	public final String name;
	public final String base;
	public final boolean inherit;
	public final Rule[] rules;

	public Theme(String name, String base, boolean inherit, Rule... rules)
	{
		this.name = name;
		this.base = base;
		this.inherit = inherit;
		this.rules = rules;
	}

	@Override
	public String toString()
	{
		return "{\n" +
                    "base: '" + base + "',\n" +
                    "inherit: " + inherit +",\n" +
                    "rules: [\n" +  String.join(",", Arrays.asList(rules).stream().map(rule -> rule.toString()).collect(Collectors.toList())) + "]" +
                "\n}";
	}
}