package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import javafx.scene.control.Control;

import org.beuwi.msgbots.platform.gui.enums.ConfigType;
import org.beuwi.msgbots.platform.gui.skins.OptionViewSkin;

public class OptionView extends Control {
	private static final String DEFAULT_STYLE_CLASS = "option-view";

	private final ObservableList<Node> items = FXCollections.observableArrayList();

	private final ObjectProperty<ConfigType> type = new SimpleObjectProperty<>();
	private final StringProperty name = new SimpleStringProperty();

	private final StringProperty title = new SimpleStringProperty();

	public OptionView() {
		getItems().addListener((ListChangeListener<Node>) change -> {
			while (change.next()) {
				for (Node node : change.getRemoved()) {
					if (node instanceof OptionBox) {
						OptionBox item = (OptionBox) node;

						item.setView(null);
					}
				}

				for (Node node : change.getAddedSubList()) {
					if (node instanceof OptionBox) {
						OptionBox item = (OptionBox) node;

						item.setView(this);

						nameProperty().addListener(event -> {
							String type = this.getType().toString();
							String name = this.getName();
							String option = item.getOption();

							if (option != null) {
								item.setAddress(type + ":" + name + ":" + option);
							}
						});
					}
				}
			}
		});

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

	@Override
	public OptionViewSkin createDefaultSkin() {
		return new OptionViewSkin(this);
	}
}
