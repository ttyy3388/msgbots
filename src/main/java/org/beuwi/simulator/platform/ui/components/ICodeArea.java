package org.beuwi.simulator.platform.ui.components;

import javafx.scene.layout.*;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.*;
import org.fxmisc.richtext.model.*;

import java.util.*;
import java.util.function.IntFunction;
import java.util.regex.*;

public class ICodeArea extends AnchorPane
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

	final IContextMenu    CODE_AREA_MENU = new IContextMenu(IContextMenuType.EDITOR);
	final CodeArea 	      CODE_AREA      = new CodeArea();
	VirtualizedScrollPane SCROLL_PANE 	 = new VirtualizedScrollPane(CODE_AREA);

	{
		AnchorPane.setTopAnchor   (SCROLL_PANE, .0);
		AnchorPane.setRightAnchor (SCROLL_PANE, .0);
		AnchorPane.setBottomAnchor(SCROLL_PANE, .0);
		AnchorPane.setLeftAnchor  (SCROLL_PANE, .0);
	}

	public ICodeArea()
	{
		IntFunction<String> format = (digits -> " %" + digits + "d ");

		CODE_AREA.setParagraphGraphicFactory(LineNumberFactory.get(CODE_AREA, format));

		CODE_AREA.textProperty().addListener((observable, oldText, newText) ->
		{
			CODE_AREA.setStyleSpans(0, computeHighlighting(newText));
		});

		CODE_AREA.setOnMousePressed(event ->
		{
			if (event.isSecondaryButtonDown())
			{
				CODE_AREA_MENU.show(CODE_AREA, event.getScreenX(), event.getScreenY());
			}
			else
			{
				CODE_AREA_MENU.hide();
			}
		});

		getChildren().add(SCROLL_PANE);
		// getChildren().add(getSeparatorLine());
	}

	private static StyleSpans<Collection<String>> computeHighlighting(String text)
	{
		Matcher matcher = PATTERN.matcher(text);
		int lastKwEnd = 0;

		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

		while (matcher.find())
		{
			String styleClass =
				matcher.group("KEYWORD") != null ? "keyword" :
				matcher.group("PAREN") != null ? "paren" :
				matcher.group("BRACE") != null ? "brace" :
				matcher.group("BRACKET") != null ? "bracket" :
				matcher.group("SEMICOLON") != null ? "semicolon" :
				matcher.group("STRING") != null ? "string" : null;

			assert styleClass != null;

			spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
			spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());

			lastKwEnd = matcher.end();
		}

		spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
		return spansBuilder.create();
	}

	private StackPane getSeparatorLine()
	{
		StackPane separator = new StackPane();
		separator.getStyleClass().add("separator");
		separator.setLayoutX(57);

		AnchorPane.setTopAnchor(separator, .0);
		AnchorPane.setBottomAnchor(separator, .0);

		return separator;
	}

	public void setText(String text)
	{
		CODE_AREA.replaceText(0, 0, text);
	}

	public String getText()
	{
		return CODE_AREA.getText();
	}
}