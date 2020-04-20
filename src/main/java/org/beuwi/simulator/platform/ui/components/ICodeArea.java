package org.beuwi.simulator.platform.ui.components;

import org.beuwi.simulator.platform.application.views.actions.SaveEditorTabAction;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ICodeArea extends CodeArea
{
	private static final String[] KEYWORDS = new String[]
	{
		// JAVA
		"abstract", "assert", "boolean", "break", "byte",
		"case", "catch", "char", "class", "const",
		"continue", "default", "do", "double", "else",
		"enum", "extends", "final", "finally", "float",
		"for", "goto", "if", "implements", "import",
		"instanceof", "int", "interface", "long", "native",
		"new", "package", "private", "protected", "public",
		"return", "short", "static", "strictfp", "super",
		"switch", "synchronized", "this", "throw", "throws",
		"transient", "try", "void", "volatile", "while",

		// JAVA_SCRIPT
		"arguments", "await", "debugger", "do",
		"eval", "function", "in", "let",
		"typeof", "var", "with", "yield"
	};

	private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
	private static final String PAREN_PATTERN = "\\(|\\)";
	private static final String BRACE_PATTERN = "\\{|\\}";
	private static final String BRACKET_PATTERN = "\\[|\\]";
	private static final String SEMICOLON_PATTERN = "\\;";
	private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
	private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

	private static final Pattern PATTERN = Pattern.compile
	(
		"(?<KEYWORD>" + KEYWORD_PATTERN + ")|"
		+ "|(?<PAREN>" + PAREN_PATTERN + ")"
		+ "|(?<BRACE>" + BRACE_PATTERN + ")"
		+ "|(?<BRACKET>" + BRACKET_PATTERN + ")"
		+ "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
		+ "|(?<STRING>" + STRING_PATTERN + ")"
		+ "|(?<COMMENT>" + COMMENT_PATTERN + ")"
	);

	public ICodeArea()
	{
		this(null);
	}

	public ICodeArea(String text)
	{
		IntFunction<String> format = (digits -> " %" + digits + "d ");

		this.replaceText(0, 0, text);
		this.setParagraphGraphicFactory(LineNumberFactory.get(this, format));

		this.textProperty().addListener((observable, oldText, newText) ->
		{
			this.setStyleSpans(0, computeHighlighting(newText));

			/* String data = this.getText();

			if (data.isEmpty())
			{
				popup.hide();
			}
			else
			{
				List<String> list = entries.stream()
						.filter(e -> e.toLowerCase().contains(data.toLowerCase()))
						.collect(Collectors.toList());

				if (!list.isEmpty())
				{
					//

					if (!popup.isShowing())
					{
						popup.show(this, Side.BOTTOM, 0, 0);
					}
				}
				else
				{
					popup.hide();
				}
			} */
		});

		this.setOnKeyPressed(event ->
		{
			if (event.isControlDown())
			{
				switch (event.getCode())
				{
					case S : SaveEditorTabAction.update(); break;
				}
			}
		});
	}

	private static StyleSpans<Collection<String>> computeHighlighting(String text)
	{
		Matcher matcher = PATTERN.matcher(text);
		int lastKwEnd = 0;

		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

		while (matcher.find())
		{
			String styleClass =
				matcher.group("KEYWORD")   != null ? "keyword"   :
				matcher.group("PAREN")     != null ? "paren" 	   :
				matcher.group("BRACE")     != null ? "brace"     :
				matcher.group("BRACKET")   != null ? "bracket"   :
				matcher.group("SEMICOLON") != null ? "semicolon" :
				matcher.group("STRING")    != null ? "string"    : null;

			assert styleClass != null;

			spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
			spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());

			lastKwEnd = matcher.end();
		}

		spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
		return spansBuilder.create();
	}

	public void setText(String text)
	{
		this.replaceText(0, 0, text);
	}

	public String getText()
	{
		return this.getText();
	}

	public int getCaretPosition()
	{
		return this.getCaretPosition();
	}
}