package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.DefaultProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

@DefaultProperty("options")
public class OptionView extends HBox<Region>
{
	private static final PseudoClass SELECTED_PSEUDO_CLASS = PseudoClass.getPseudoClass("selected");

	private static final String DEFAULT_STYLE_CLASS = "option-view";

    private static final int DEFAULT_HEADER_WIDTH = 200;

    private final ObjectProperty<Option> selected = new SimpleObjectProperty<>();

	private final ObservableList<Option> options = FXCollections.observableArrayList();

    private final StackPane content = new StackPane();
    private final VBox header = new VBox();

    {
        HBox.setHgrow(content, Priority.ALWAYS);
    }

	public OptionView()
	{
		this(null);
	}

	public OptionView(Option... options)
	{
	    header.setMinWidth(DEFAULT_HEADER_WIDTH);
	    header.setMaxWidth(DEFAULT_HEADER_WIDTH);

        header.getStyleClass().add("option-header-area");
        content.getStyleClass().add("option-content-area");

        if (options != null)
        {
            addOption(options);
        }

        getOptions().addListener((ListChangeListener<Option>) change ->
        {
            while (change.next())
            {
                for (Option option : change.getRemoved())
                {
                    option.setView(null);
                    header.remove(option);
                }

                for (Option option : change.getAddedSubList())
                {
                    option.setView(this);
                    header.addItem(option);
                    selected.setValue(option);
                }
            }
        });

		getSelectedProperty().addListener((observable, oldOption, newOption) ->
		{
			if (oldOption != null)
			{
                oldOption.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, false);
			}

            newOption.pseudoClassStateChanged(SELECTED_PSEUDO_CLASS, true);
		});

		getSelectedProperty().addListener(change ->
		{
			content.getChildren().clear();
			content.getChildren().add(getSelectedOption().getContent());
		});

		getChildren().addAll(header, content);
		getStyleClass().setAll(DEFAULT_STYLE_CLASS);
	}

	public void select(Option option)
    {
        selected.set(option);
    }

	public void addOption(Option... option)
	{
		options.addAll(option);
	}

	public ObservableList<Option> getOptions()
	{
		return options;
	}

	public Option getSelectedOption()
    {
        return selected.get();
    }

	public ObjectProperty<Option> getSelectedProperty()
    {
        return selected;
    }

    public void setSelectedOption(Option option)
    {
        selected.set(option);
    }
}
