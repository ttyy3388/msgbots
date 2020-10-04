package org.beuwi.msgbots.platform.gui.control;

import javafx.application.Platform;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.beuwi.msgbots.platform.app.view.actions.SaveScriptTabAction;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.function.IntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeArea extends VirtualizedScrollPane<org.fxmisc.richtext.CodeArea>
{
	final private static String[] KEYWORDS = new String[]
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

		// JAVA SCRIPT
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
	// private static final String PARAMETER_PATTERN = "\\{(.*)\\}"; // "\\{([\\w.]+)}";

	final private static Pattern PATTERN = Pattern.compile
	(
		"(?<KEYWORD>" + KEYWORD_PATTERN + ")|" +
		"(?<PAREN>" + PAREN_PATTERN + ")|" +
		"(?<BRACE>" + BRACE_PATTERN + ")|" +
		"(?<BRACKET>" + BRACKET_PATTERN + ")|" +
		"(?<SEMICOLON>" + SEMICOLON_PATTERN + ")|" +
		"(?<STRING>" + STRING_PATTERN + ")|" +
		"(?<COMMENT>" + COMMENT_PATTERN + ")"
	);

	/* private SortedSet<String> entries;
	private ContextMenu popup = new ContextMenu
	(
		new MenuItem("TEST")
	); */

	final org.fxmisc.richtext.CodeArea area;

	public CodeArea()
	{
		this("");
	}

	public CodeArea(String content)
	{
		this(new org.fxmisc.richtext.CodeArea(content));
	}

	public CodeArea(org.fxmisc.richtext.CodeArea area)
	{
		super(area);

		this.area = area;

		IntFunction<String> format = (digits -> " %" + digits + "d ");

		// this.replaceText(0, 0, text);
		area.setParagraphGraphicFactory(LineNumberFactory.get(area, format));
		area.setContextMenu(new ContextMenu
		(
			new MenuItem("Undo", "Ctrl + Z", event -> area.undo()),
			new MenuItem("Redo", "Ctrl + Y", event -> area.redo()),
			new SeparatorMenuItem(),
			new MenuItem("Cut", "Ctrl + X", event -> area.cut()),
			new MenuItem("Copy", "Ctrl + C", event -> area.copy()),
			new MenuItem("Paste", "Ctrl + V", event -> area.paste()),
			new SeparatorMenuItem(),
			new MenuItem("Select All", "Ctrl + A", event -> area.selectAll())
		));

		area.setStyleSpans(0, computeHighlighting(area.getText()));
		area.textProperty().addListener((observable, oldText, newText) ->
		{
			area.setStyleSpans(0, computeHighlighting(newText));
		});

		final Pattern space = Pattern.compile("^\\s+");

		area.addEventHandler(KeyEvent.KEY_PRESSED, event ->
		{
			if (event.isControlDown())
			{
				switch (event.getCode())
				{
					case S : SaveScriptTabAction.execute(); break;
				}
			}
		});

		area.addEventHandler(KeyEvent.KEY_PRESSED, event ->
		{
			if (KeyCode.ENTER.equals(event.getCode()))
			{
				int position = area.getCaretPosition();
				int paragraph = area.getCurrentParagraph();

				Matcher matcher = space.matcher(area.getParagraph(paragraph - 1).getSegments().get(0));

				if (matcher.find())
				{
					Platform.runLater(() -> area.insertText(position, matcher.group()));
				}
			}
		});

		Platform.runLater(() ->
		{
			area.requestFocus();
		});
	}

	private static StyleSpans<Collection<String>> computeHighlighting(String text)
	{
		Matcher matcher = PATTERN.matcher(text);

		int lastKwEnd = 0;

		StyleSpansBuilder<Collection<String>> builder = new StyleSpansBuilder<>();

		while (matcher.find())
		{
			String styleClass =
					matcher.group("KEYWORD")   != null ? "keyword"   :
					matcher.group("PAREN")     != null ? "paren" 	   :
					matcher.group("BRACE")     != null ? "brace"     :
					matcher.group("BRACKET")   != null ? "bracket"   :
					matcher.group("SEMICOLON") != null ? "semicolon" :
					matcher.group("STRING")    != null ? "string"    :
					matcher.group("COMMENT")   != null ? "comment"   : null;

			assert styleClass != null;

			builder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
			builder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());

			lastKwEnd = matcher.end();
		}

		builder.add(Collections.emptyList(), text.length() - lastKwEnd);

		return builder.create();
	}

	public String getText()
	{
		return area.getText();
	}

	public void setText(String text)
	{
		area.clear();
		area.appendText(text);
	}

	public void appendText(String text)
	{
		area.appendText(text);
	}
}
