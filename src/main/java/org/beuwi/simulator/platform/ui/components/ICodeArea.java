package org.beuwi.simulator.platform.ui.components;

import javafx.application.Platform;
import javafx.scene.control.SeparatorMenuItem;
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

	/* private SortedSet<String> entries;
	private IContextMenu popup = new IContextMenu
	(
		new IMenuItem("TEST")
	); */

	public ICodeArea(String content)
	{
		super(content);

		IntFunction<String> format = (digits -> " %" + digits + "d ");

		this.setStyleSpans(0, computeHighlighting(content));

		// this.replaceText(0, 0, text);
		this.setParagraphGraphicFactory(LineNumberFactory.get(this, format));

		this.setContextMenu(new IContextMenu
		(
			new IMenuItem("Undo", "Ctrl + Z", event -> this.undo()),
			new IMenuItem("Redo", "Ctrl + Y", event -> this.redo()),
			new SeparatorMenuItem(),
			new IMenuItem("Cut", "Ctrl + X", event -> this.cut()),
			new IMenuItem("Copy", "Ctrl + C", event -> this.copy()),
			new IMenuItem("Paste", "Ctrl + V", event -> this.paste()),
			new SeparatorMenuItem(),
			new IMenuItem("Select All", "Ctrl + A", event -> this.selectAll())
		));

		this.textProperty().addListener((observable, oldText, newText) ->
		{
			this.setStyleSpans(0, computeHighlighting(newText));

			/* this.addEventFilter(KeyEvent.KEY_PRESSED, event ->
			{
				if (KeyCode.TAB.equals(event.getCode()))
				{
					this.insertText(this.getCaretPosition(), );
					event.consume();
				}
			});

			/* String text = this.getText();

			if (text.isEmpty())
			{
				popup.hide();
			}
			else
			{
				List<String> list = entries.stream()
						.filter(entry -> entry.toLowerCase().contains(text.toLowerCase()))
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

		this.getStyleClass().add("ifx-code-area");

		Platform.runLater(() ->
		{
			this.requestFocus();
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
}