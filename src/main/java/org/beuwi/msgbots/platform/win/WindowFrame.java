package org.beuwi.msgbots.platform.win;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.beuwi.msgbots.platform.app.view.MainView;
import org.beuwi.msgbots.platform.app.view.actions.ChangeThemeAction;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;
import org.beuwi.msgbots.platform.util.ResourceUtils;

public class WindowFrame
{
	private final Stage stage;

	private ThemeType theme;

	private WindowType type;
	private Region content;
	private String title;

	public WindowFrame(Stage stage)
	{
		this.stage = stage;
	}

	public WindowType getType()
	{
		return type;
	}

	public ThemeType getTheme()
	{
		return theme;
	}

	public Stage getStage()
	{
		return stage;
	}

	public Region getContent()
	{
		return content;
	}

	public String getTitle()
	{
		return title;
	}

	public void setType(WindowType type)
	{
		this.type = type;
	}

	public void setTheme(ThemeType theme)
	{
		this.theme = theme;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setContent(Region content)
	{
		this.content = content;
	}

	public void create()
	{
		final WindowScene scene = new WindowScene(content);

		try
		{
			if (type.equals(WindowType.DIALOG))
			{
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initStyle(StageStyle.UNDECORATED);
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.initOwner(MainView.getStage());
			}

			// super.setContent(content);

			stage.addEventHandler(KeyEvent.KEY_PRESSED, event ->
			{
				/* if (event.getCode().equals(KeyCode.ALT))
				{
					ToggleMenuBarAction.execute();
				} */
			});

			stage.getIcons().add(ResourceUtils.getImage("program"));
			stage.setScene(scene);
			stage.setTitle(title);
			stage.toFront();
			stage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			ChangeThemeAction.execute(scene, theme);
		}
	}

	public void open()
	{
		stage.show();
	}
}