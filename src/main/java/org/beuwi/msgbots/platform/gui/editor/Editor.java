package org.beuwi.msgbots.platform.gui.editor;

import java.io.File;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.gui.control.TextArea;

public final class Editor extends TextArea
{
	private static final String DEFAULT_STYLE_CLASS = "editor";

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

			setOnKeyPressed(event ->
			{
				if (event.isControlDown())
				{
					switch (event.getCode())
					{
						case S : save(); break;
						case C : copy(); break;
						case Z : redo(); break;
						case Y : undo(); break;
					}
				}
			});
		}
	});

	public Editor()
	{
		this(null);
	}

	public Editor(File file)
	{
		if (file != null)
		{
			setFile(file);
		}

		setStyleClass("editor");
	}

	public void save()
	{
		FileManager.save(getFile(), getText());
	}

	public void setFile(File file)
	{
		this.file.set(file);
	}

	private void setText(File file)
	{
		setText(FileManager.read(file));
	}

	public File getFile()
	{
		return file.get();
	}

	private class FileProperty extends SimpleObjectProperty<File>
	{
		public FileProperty(InvalidationListener listener)
		{
			addListener(listener);
		}
	}
}
