package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import javafx.scene.layout.Priority;
import org.beuwi.msgbots.platform.gui.enums.ConfigType;

public class OptionView extends VBox {
	private static final String DEFAULT_STYLE_CLASS = "option-view";

	private static final int DEFAULT_SPACING_VALUE = 10;
	private static final int DEFAULT_HEADER_HEIGHT = 50;

	private final ObservableList<Node> items = FXCollections.observableArrayList();

	private final ObjectProperty<ConfigType> type = new SimpleObjectProperty<>();
	private final StringProperty name = new SimpleStringProperty();

	private final StringProperty title = new SimpleStringProperty();

	// Title label
	private final Label label = new Label();

	// Content안에 Optionbox가 들어감
	private final VBox content = new VBox();

	{
		VBox.setVgrow(content, Priority.ALWAYS);
	}

	public OptionView() {
		super.getItems().setAll(label, content);

		label.setMinHeight(DEFAULT_HEADER_HEIGHT);
		label.getStyleClass().add("h2");

		content.getItems().setAll(getItems());
		content.setSpacing(30.0);
		content.getStyleClass().add("content1");

		titleProperty().addListener(change -> {
			label.setText(getTitle());

            /* if (title.getText().isEmpty()) {
                root.remove(title);
            }
            else {
                if (!root.contains(title)) {
                    root.addItem(0, title);
                }
            } */
		});
		getItems().addListener((ListChangeListener<Node>) change -> {
			while (change.next()) {
				for (Node node : change.getRemoved()) {
					if (node instanceof OptionBox) {
						((OptionBox) node).setView(null);
					}
				}
				for (Node node : change.getAddedSubList()) {
					if (node instanceof OptionBox) {
						OptionBox item = (OptionBox) node;
						nameProperty().addListener(event -> {
							String type = this.getType().toString();
							String name = this.getName();
							String option = item.getOption();

							if (option != null) {
								item.setAddress(type + ":" + name + ":" + option);
							}
						});
						item.setView(this);
					}
				}
			}
		});
		getItems().addListener((ListChangeListener<Node>) change -> {
			content.getItems().setAll(getItems());
		});

		setSpacing(DEFAULT_SPACING_VALUE);
		getStyleClass().addAll(DEFAULT_STYLE_CLASS);
	}

	// Link Bot Name
	public void setName(String name) {
        this.name.set(name);
    }

	public void setTitle(String title) {
		this.title.set(title);
	}

	public void setType(ConfigType type) {
		this.type.set(type);
	}

	public String getName() {
        return name.get();
    }

	public ConfigType getType() {
		return type.get();
	}

	public String getTitle() {
		return title.get();
	}

	@Override
	public ObservableList<Node> getItems() {
		return items;
	}

	public StringProperty nameProperty() {
		return name;
	}

	public ObjectProperty<ConfigType> typeProperty() {
		return type;
	}

	public StringProperty titleProperty() {
		return title;
	}
}
