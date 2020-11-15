package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;
import org.beuwi.msgbots.platform.gui.skins.OptionBoxSkin;
import org.beuwi.msgbots.setting.GlobalSettings;

// Option Box
public class OptionBox extends Control
{
	private static final String DEFAULT_STYLE_CLASS = "option-box";

	private final StringProperty title = new SimpleStringProperty();
	private final ObjectProperty<Node> content = new SimpleObjectProperty<>();
	private final StringProperty address = new SimpleStringProperty();

	public OptionBox()
	{
		addStyleClass(DEFAULT_STYLE_CLASS);

		address.addListener(event ->
		{
			if (getContent() instanceof Button)
			{
				Button control = (Button) getContent();
			}
			else if (getContent() instanceof CheckBox)
			{
				CheckBox control = (CheckBox) getContent();

				control.setSelected(GlobalSettings.getData(getAddress()));

				control.getSelectedProperty().addListener(change ->
				{
					GlobalSettings.setData(getAddress(), control.isSelected());
				});
			}
			else if (getContent() instanceof TextArea)
			{
				TextArea control = (TextArea) getContent();

				control.setText(GlobalSettings.getData(getAddress()));

                control.getTextProperty().addListener(change ->
                {
                    GlobalSettings.setData(getAddress(), control.getText());
                });
			}
			else if (getContent() instanceof TextField)
			{
				TextField control = (TextField) getContent();

				control.setText(GlobalSettings.getData(getAddress()));

				control.getTextProperty().addListener(change ->
				{
                   	GlobalSettings.setData(getAddress(), control.getText());
				});
			}
			else if (getContent() instanceof ToggleButton)
			{
				ToggleButton control = (ToggleButton) getContent();

				control.setSelected(GlobalSettings.getData(getAddress()));

				control.getSelectedProperty().addListener(change ->
				{
                    GlobalSettings.setData(getAddress(), control.isSelected());
				});
			}
			else if (getContent() instanceof ToggleSwitch)
			{
				ToggleSwitch control = (ToggleSwitch) getContent();

				control.setSelected(GlobalSettings.getData(getAddress()));

				control.getSelectedProperty().addListener(change ->
				{
                    GlobalSettings.setData(getAddress(), control.isSelected());
				});
			}
			/* else if (getContent() instanceof ComboBox)
			{
				ComboBox control = (ComboBox) getContent();

				control.select(ThemeType.convert(GlobalSettings.getData(getAddress())));

				control.getSelectedItemProperty().addListener(change ->
				{
					GlobalSettings.setData(getAddress(), control.getSelectedItem().toString());
				});
			} */
		});
	}

	public void setTitle(String title)
	{
		this.title.set(title);
	}

	public void setContent(Node content)
	{
		this.content.set(content);
	}

	public void setAddress(String address)
	{
		this.address.set(address);
	}

	public String getTitle()
	{
		return title.get();
	}

	public String getAddress()
	{
		return address.get();
	}

	public Node getContent()
	{
		return content.get();
	}

	public StringProperty getTitleProperty()
	{
		return title;
	}

	public StringProperty getAddressProperty()
	{
		return address;
	}

	public ObjectProperty<Node> getContentProperty()
	{
		return content;
	}

	@Override
	public OptionBoxSkin setDefaultSkin()
	{
		return new OptionBoxSkin(this);
	}
}