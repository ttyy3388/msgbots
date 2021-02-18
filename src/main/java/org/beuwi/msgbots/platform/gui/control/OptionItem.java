package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;

import org.beuwi.msgbots.platform.app.action.WriteImageFileAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenFileChooserAction;
import org.beuwi.msgbots.platform.app.view.dialogs.ChooseBotsPathDialog;
import org.beuwi.msgbots.platform.gui.enums.TextType;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.layout.VBox;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.setting.SharedSettings;

import java.io.File;

// Option Box
public class OptionItem extends VBox {
	private static final String DEFAULT_STYLE_CLASS = "option-item";

	// private static final Insets DEFAULT_PADDING_VALUE = new Insets(10, 0, 10, 10);
	private static final int DEFAULT_SPACING_VALUE = 10;

	// Title [ Content ]
	private final StringProperty titleProperty = new SimpleStringProperty();
	private final ObjectProperty<Node> contentProperty = new SimpleObjectProperty<>();

	// address : "type:head:value > "global:program:start_auto_compile"
	private final StringProperty addressProperty = new SimpleStringProperty();
	// value start_auto_compile
	// private final StringProperty valueProperty = new SimpleStringProperty();

	// Title Label
	private final Label label = new Label();
	// Content Panel
	private final StackPane panel = new StackPane();

	private OptionView parent;

	{
		VBox.setVgrow(panel, Priority.ALWAYS);
	}

	public void initValue() {
		// 주소에 "{$name}"과 같이 변수 삽입 부분이 남아있다면 작동 X
		if (getAddress().indexOf("$") != -1) {
			return ;
		}

		if (getContent() instanceof Button) {
			Button control = (Button) getContent();
			if (getAddress().equals("control:button:choose_bots_path")){
				control.setOnAction(event->{
					OpenDialogBoxAction.execute(new ChooseBotsPathDialog());
				});

			}
			if (getAddress().equals("control:button:change_bot_profile")) {
				control.setOnAction(event -> {
					File file = OpenFileChooserAction.execute("Change Bot Profile",
						new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png", "*.gif")
					);

					if (file != null) {
						WriteImageFileAction.execute(file, "png", SharedValues.PROFILE_BOT_FILE);
					}
				});
			}
			if (getAddress().equals("control:button:change_sender_profile")) {
				control.setOnAction(event -> {
					File file = OpenFileChooserAction.execute("Change Sender Profile",
							new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png", "*.gif")
					);

					if (file != null) {
						WriteImageFileAction.execute(file, "png", SharedValues.PROFILE_SENDER_FILE);
					}
				});
			}
		}
		else if (getContent() instanceof CheckBox) {
			CheckBox control = (CheckBox) getContent();
			control.setSelected(SharedSettings.getBoolean(getAddress()));
			control.selectedProperty().addListener(change -> {
				SharedSettings.setData(getAddress(), control.isSelected());
			});
		}
		else if (getContent() instanceof TextArea) {
			TextArea control = (TextArea) getContent();
			control.setText(SharedSettings.getString(getAddress()));
			control.focusedProperty().addListener(change -> {
				SharedSettings.setData(getAddress(), control.getText());
			});
		}
		else if (getContent() instanceof TextField) {
			TextField control = (TextField) getContent();
			control.setText(SharedSettings.getString(getAddress()));
			control.focusedProperty().addListener(change -> {
				SharedSettings.setData(getAddress(), control.getText());
			});
		}
		else if (getContent() instanceof ToggleButton) {
			ToggleButton control = (ToggleButton) getContent();
			control.setSelected(SharedSettings.getBoolean(getAddress()));
			control.selectedProperty().addListener(change -> {
				SharedSettings.setData(getAddress(), control.isSelected());
			});
		}
		else if (getContent() instanceof ToggleSwitch) {
			ToggleSwitch control = (ToggleSwitch) getContent();
			control.setSelected(SharedSettings.getBoolean(getAddress()));
			control.selectedProperty().addListener(change -> {
				SharedSettings.setData(getAddress(), control.isSelected());
			});
		}
		else if (getContent() instanceof Slider) {
			Slider control = (Slider) getContent();
			control.setValue(SharedSettings.getInt(getAddress()));
			control.focusedProperty().addListener(change -> {
				SharedSettings.setData(getAddress(), control.getValue());
			});
		}
		else if (getContent() instanceof ComboBox) {
			ComboBox control = (ComboBox) getContent();
			if (getAddress().equals("global:program:color_theme")) {
				// 프로그램 컬러 테마
				control.selectItem(ThemeType.parse(SharedSettings.getData(getAddress())));
			}
			if (getAddress().equals("global:program:text_rendering")) {
				// 텍스트 렌더링 타입
				control.selectItem(TextType.parse(SharedSettings.getData(getAddress())));
			}
			control.selectedItemProperty().addListener(change -> {
				SharedSettings.setData(getAddress(), control.getSelectedItem().toString());
			});
		}
	}

	public OptionItem(/* @NamedArg("type") PrefType type */) {
		label.getStyleClass().add("title");
		panel.getStyleClass().add("content");
		panel.setAlignment(Pos.CENTER_LEFT);

		titleProperty().addListener(change -> {
			label.setText(getTitle());
		});

		contentProperty().addListener(change -> {
			panel.getChildren().setAll(getContent());
		});

		addressProperty().addListener(event -> initValue());

		// setPadding(DEFAULT_PADDING_VALUE);
		setSpacing(DEFAULT_SPACING_VALUE);
		getChildren().setAll(label, panel);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	public void setTitle(String title) {
		titleProperty.set(title);
	}
	public void setContent(Node content) {
		contentProperty.set(content);
	}
	public void setAddress(String address) {
		addressProperty.set(address);
	}

	public String getTitle() {
		return titleProperty.get();
	}
	public String getAddress() {
		return addressProperty.get();
	}
	public Node getContent() {
		return contentProperty.get();
	}

	public StringProperty titleProperty() {
		return titleProperty;
	}
	public StringProperty addressProperty() {
		return addressProperty;
	}
	public ObjectProperty<Node> contentProperty() {
		return contentProperty;
	}
}