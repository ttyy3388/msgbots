package org.beuwi.msgbots.platform.gui.editor;

import eu.mihosoft.monacofx.Document;
import eu.mihosoft.monacofx.Editor;
import eu.mihosoft.monacofx.MonacoFX;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.gui.layout.StackPanel;

import javax.swing.event.ChangeListener;
import java.io.File;
import java.lang.reflect.Field;

// Theme : [ VS Dark , VS Light , HC Black ]
public class CodeArea extends StackPanel
{
	private static final String DEFAULT_STYLE_CLASS = "code-area";

	private final FileProperty file = new FileProperty(change ->
	{
		File file = this.file.get();

		if (file != null)
		{
			setText(file);

			FileManager.link(file, () ->
			{
				setText(file);
			});
		}
	});

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

		setItem(monaco);
		setTheme("vs-dark");
		setLanguage("javascript");
		addStyleClass("code-area");
	}

	public void setFile(File file)
	{
		this.file.set(file);
	}

	public void setText(File file)
	{
		document.setText(FileManager.read(file));
	}

	private void setText(String text)
	{
		document.setText(text);
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

	public FileProperty getFileProperty()
	{
		return file;
	}

	private class FileProperty extends SimpleObjectProperty<File>
	{
		public FileProperty(InvalidationListener listener)
		{
			addListener(listener);
		}
	}
}