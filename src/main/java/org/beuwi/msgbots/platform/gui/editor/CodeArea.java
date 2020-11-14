package org.beuwi.msgbots.platform.gui.editor;

import eu.mihosoft.monacofx.Document;
import eu.mihosoft.monacofx.Editor;
import eu.mihosoft.monacofx.MonacoFX;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

import java.io.File;
import java.lang.reflect.Field;

// Theme : [ VS Dark , VS Light , HC Black ]
public class CodeArea extends StackPanel
{
	private static final String DEFAULT_STYLE_CLASS = "code-area";

	private final ObjectProperty<File> file = new SimpleObjectProperty();

	private final MonacoFX monaco = new MonacoFX();
	private final Document document;

	private final Editor editor;

	public CodeArea()
	{
		this(null);
	}

	public CodeArea(File file)
	{
		editor = monaco.getEditor();
		document = editor.getDocument();

		if (file != null)
		{
			setFile(file);
		}

		getFileProperty().addListener(change ->
		{
			if (getFile() != null)
			{
				setText(getFile());

				FileManager.link(getFile(), () ->
				{
					setText(getFile());
				});
			}
		});

		setItem(monaco);
		setTheme("vs-dark");
		setLanguage("javascript");
		addStyleClass("code-area");
	}

	public void setFile(File file)
	{
		this.file.set(file);
	}

	private void setText(String text)
	{
		document.setText(text);
	}

	public void setText(File file)
	{
		document.setText(FileManager.read(file));
	}

	public void setTheme(String theme)
	{
		editor.setCurrentTheme(theme);
	}

	public void setLanguage(String language)
	{
		editor.setCurrentLanguage(language);
	}

	public File getFile()
	{
		return file.get();
	}

	public String getText()
	{
		return document.getText();
	}

	public StringProperty getTextProperty()
	{
		return document.textProperty();
	}

	public ObjectProperty<File> getFileProperty()
	{
		return file;
	}
}