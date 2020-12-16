package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import org.beuwi.msgbots.platform.gui.skins.OptionBoxSkin;

// Option Box
public class OptionBox extends Control
{
	private static final String DEFAULT_STYLE_CLASS = "option-box";

	private final StringProperty title = new SimpleStringProperty();
	private final ObjectProperty<Node> content = new SimpleObjectProperty<>();

	// address : "type:name:option" > "global:program:start_auto_compile"
	private final StringProperty address = new SimpleStringProperty();
	private final StringProperty option = new SimpleStringProperty();

	private OptionView parent;

	public OptionBox()
	{
		addStyleClass(DEFAULT_STYLE_CLASS);

		/* getAddressProperty().addListener(event ->
		{
			if (getContent() instanceof Button)
			{
				Button control = (Button) getContent();
			}
			else if (getContent() instanceof CheckBox)
			{
				CheckBox control = (CheckBox) getContent();

				control.setSelected(SharedSettings.getData(getAddress()));

				control.getSelectedProperty().addListener(change ->
				{
					SharedSettings.setData(getAddress(), control.isSelected());
				});
			}
			else if (getContent() instanceof TextArea)
			{
				TextArea control = (TextArea) getContent();

				control.setText(SharedSettings.getData(getAddress()));

                control.getTextProperty().addListener(change ->
                {
                    SharedSettings.setData(getAddress(), control.getText());
                });
			}
			else if (getContent() instanceof TextField)
			{
				TextField control = (TextField) getContent();

				control.setText(SharedSettings.getData(getAddress()));

				control.getTextProperty().addListener(change ->
				{
                   	SharedSettings.setData(getAddress(), control.getText());
				});
			}
			else if (getContent() instanceof ToggleButton)
			{
				ToggleButton control = (ToggleButton) getContent();

				control.setSelected(SharedSettings.getData(getAddress()));

				control.getSelectedProperty().addListener(change ->
				{
                    SharedSettings.setData(getAddress(), control.isSelected());
				});
			}
			else if (getContent() instanceof ToggleSwitch)
			{
				ToggleSwitch control = (ToggleSwitch) getContent();

				control.setSelected(SharedSettings.getData(getAddress()));

				control.getSelectedProperty().addListener(change ->
				{
                    SharedSettings.setData(getAddress(), control.isSelected());
				});
			}
			else if (getContent() instanceof Slider)
			{
				Slider control = (Slider) getContent();

				control.setValue(SharedSettings.getInt(getAddress()));

				control.getValueProperty().addListener(change ->
				{
					SharedSettings.setData(getAddress(), control.getValue());
				});
			}
			/* else if (getContent() instanceof ComboBox)
			{
				ComboBox control = (ComboBox) getContent();

				control.select(ThemeType.convert(SharedSettings.getData(getAddress())));

				control.getSelectedItemProperty().addListener(change ->
				{
					SharedSettings.setData(getAddress(), control.getSelectedItem().toString());
				});
			}
		}); */
	}

	public void setView(OptionView parent)
	{
		this.parent = parent;
	}

	public void setTitle(String title)
	{
		this.title.set(title);
	}

	public void setContent(Node content)
	{
		this.content.set(content);
	}

	public void setOption(String option)
	{
		this.option.set(option);
	}

	protected void setAddress(String address)
	{
		this.address.set(address);
	}

	public OptionView getView()
	{
		return parent;
	}

	public String getTitle()
	{
		return title.get();
	}

	public String getOption()
	{
		return option.get();
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

	public StringProperty getOptionProperty()
	{
		return option;
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