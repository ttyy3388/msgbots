package org.beuwi.simulator.platform.ui.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import org.beuwi.simulator.platform.ui.components.IButton;
import org.beuwi.simulator.platform.ui.dialog.IDialogBoxType.DOCUMENT;
import org.beuwi.simulator.platform.ui.window.IWindowScene;
import org.beuwi.simulator.platform.ui.window.IWindowStage;
import org.beuwi.simulator.platform.ui.window.IWindowType;
import org.beuwi.simulator.platform.ui.window.IWindowView;
import org.beuwi.simulator.utils.ResourceUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class IDialogBoxView extends StackPane implements Initializable
{
	@FXML private BorderPane bopRootPane;
	@FXML private ImageView  imvDialogIcon;
	@FXML private HBox	 	 hoxButtonBox;

	@FXML private IButton btnOK;
	@FXML private IButton btnNO;

	final IWindowView  main;
	final IWindowStage stage;

	IDialogBoxType type;
	DOCUMENT document;
	Region content;
	String title;

	// Document Type
	public IDialogBoxView(DOCUMENT document)
	{
		this(IDialogBoxType.DOCUMENT);

		this.document = document;
	}

	public IDialogBoxView(IDialogBoxType type)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ResourceUtils.getForm("DialogBoxView"));
		loader.setController(this);

		try
		{
			loader.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		this.type  = type;
		this.main  = new IWindowView();
		this.stage = new IWindowStage();
	}

	public void setUseButton(boolean ok, boolean no)
	{
		if (!ok) hoxButtonBox.getChildren().remove(btnOK);
		if (!no) hoxButtonBox.getChildren().remove(btnNO);
	}

	public void setContent(Region content)
	{
		this.content = content;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public IButton getOKButton()
	{
		return btnOK;
	}

	public IButton getNOButton()
	{
		return btnNO;
	}

	public void create()
	{
		switch (type)
		{
			case APPLICATION :

				bopRootPane.getChildren().remove(bopRootPane.getLeft());
				this.setMinSize(content.getMinWidth(), content.getMinHeight() + 47);
				this.setPrefSize(content.getPrefWidth(), content.getPrefHeight() + 47);
				break;

			case DOCUMENT :

				imvDialogIcon.setImage
				(
					switch (document)
					{
						case ERROR   -> ResourceUtils.getImage("error_big");
						case WARNING -> ResourceUtils.getImage("warning_big");
						case EVENT   -> ResourceUtils.getImage("event_big");
						default 	 -> null;
					}
				);

				this.setMinSize(content.getMinWidth() + 70, content.getMinHeight() + 47);
				this.setPrefSize(content.getPrefWidth() + 70, content.getPrefHeight() + 47);
				break;
		};

		bopRootPane.setCenter(content);

		btnOK.setButtonType(IButton.ACTION);

		this.getChildren().add(bopRootPane);
		this.getStyleClass().add("dialog");
		this.getStyleClass().add(ResourceUtils.getStyle("DialogBoxView"));

		main.setContent(this);
		main.setType(IWindowType.DIALOG);
		main.setStage(stage);
		main.setTitle(title);
		main.create();

		stage.addEventFilter(KeyEvent.KEY_PRESSED, event ->
		{
			switch (event.getCode())
			{
				case ESCAPE : stage.close(); break;
			}
		});

		stage.setType(IWindowType.DIALOG);
		stage.setScene(new IWindowScene(main));
		stage.show();
	}

	public void close()
	{
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		btnOK.addEventHandler(ActionEvent.ACTION, event ->
		{
			stage.close();
		});

		btnNO.addEventHandler(ActionEvent.ACTION, event ->
		{
			stage.close();
		});
	}
}