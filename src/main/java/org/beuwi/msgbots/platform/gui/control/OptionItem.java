package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.action.WriteImageFileAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenFileChooserAction;
import org.beuwi.msgbots.platform.app.view.dialogs.ChooseBotsPathDialog;
import org.beuwi.msgbots.platform.gui.editor.Editor;
import org.beuwi.msgbots.platform.gui.enums.TextType;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.layout.VBox;
import org.beuwi.msgbots.platform.util.ResourceUtils;
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

	private final Label titleLabel = new Label();
	private final StackPane contentPanel = new StackPane();

	private OptionView parent;

	{
		VBox.setVgrow(contentPanel, Priority.ALWAYS);
	}

	public void initValue() {
		// 주소에 "{$name}"과 같이 변수 삽입 부분이 남아있다면 작동 X
		if (getAddress().indexOf("$") != -1) {
			return ;
		}

		if (getContent() instanceof Editor) {
			Editor control = (Editor) getContent();
			if (getAddress().equals("control:editor:edit_script_default")) {
				String content;
				// 파일이 존재한다면(수정했던 이력이 있다면)
				if (SharedValues.getFile("SCRIPT_DEFAULT_FILE").exists()) {
					content = FileManager.read(SharedValues.getFile("SCRIPT_DEFAULT_FILE"));
				}
				else {
					content = FileManager.read(ResourceUtils.getInputStream("/data/script_default.js"));
				}
				control.setText(content);
				// 에디터는 포커스 이벤트가 안오므로 텍스트 변경으로 인식
				control.textProperty().addListener(change -> {
					File file = SharedValues.getFile("SCRIPT_DEFAULT_FILE");
					// 처음 수정한다면
					/* if (!file.exists()) {
						FileManager.save(file, control.getText());
					} */
					FileManager.save(file, control.getText());
				});
				titleLabel.getStyleClass().remove("title");
			}
			else if (getAddress().equals("control:editor:edit_script_unified")) {
				String content;
				// 파일이 존재한다면(수정했던 이력이 있다면)
				if (SharedValues.getFile("SCRIPT_UNIFIED_FILE").exists()) {
					content = FileManager.read(SharedValues.getFile("SCRIPT_UNIFIED_FILE"));
				}
				else {
					content = FileManager.read(ResourceUtils.getInputStream("/data/script_unified.js"));
				}
				control.setText(content);
				// 에디터는 포커스 이벤트가 안오므로 텍스트 변경으로 인식
				control.textProperty().addListener(change -> {
					File file = SharedValues.getFile("SCRIPT_UNIFIED_FILE");
					// 처음 수정한다면
					/* if (!file.exists()) {
						FileManager.save(file, control.getText());
					} */
					FileManager.save(file, control.getText());
				});
				titleLabel.getStyleClass().remove("title");
			}
		}
		if (getContent() instanceof Button) {
			Button control = (Button) getContent();
			/* if (getAddress().equals("control:button:cleanup_user_setting")){
				if (SharedValues.GLOBAL_CONFIG_FILE != null) {

				}
			} */
			if (getAddress().equals("control:button:choose_bots_path")){
				control.setOnAction(event->{
					OpenDialogBoxAction.execute(new ChooseBotsPathDialog());
				});
			}
			else if (getAddress().equals("control:button:change_bot_profile")) {
				control.setOnAction(event -> {
					File file = OpenFileChooserAction.execute("Change Bot Profile",
						new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png", "*.gif")
					);
					if (file != null) {
						WriteImageFileAction.execute(file, "png", SharedValues.getFile("PROFILE_BOT_FILE"));
					}
				});
			}
			else if (getAddress().equals("control:button:change_sender_profile")) {
				control.setOnAction(event -> {
					File file = OpenFileChooserAction.execute("Change Sender Profile",
						new FileChooser.ExtensionFilter("Image File", "*.jpg", "*.png", "*.gif")
					);
					if (file != null) {
						WriteImageFileAction.execute(file, "png", SharedValues.getFile("PROFILE_SENDER_FILE"));
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
			else if (getAddress().equals("global:program:text_rendering")) {
				// 텍스트 렌더링 타입
				control.selectItem(TextType.parse(SharedSettings.getData(getAddress())));
			}
			control.selectedItemProperty().addListener(change -> {
				SharedSettings.setData(getAddress(), control.getSelectedItem().toString());
			});
		}
	}

	public OptionItem(/* @NamedArg("type") PrefType type */) {
		titleLabel.getStyleClass().add("title");
		contentPanel.getStyleClass().add("content");
		contentPanel.setAlignment(Pos.CENTER_LEFT);

		titleProperty().addListener(change -> {
			titleLabel.setText(getTitle());
		});

		contentProperty().addListener(change -> {
			contentPanel.getChildren().setAll(getContent());
		});

		addressProperty().addListener(event -> initValue());

		// setPadding(DEFAULT_PADDING_VALUE);
		setSpacing(DEFAULT_SPACING_VALUE);
		getChildren().setAll(titleLabel, contentPanel);
		getStyleClass().add(DEFAULT_STYLE_CLASS);
	}

	// 유저가 설정한 옵션이 있으면 제일 먼저 가져오고
	// 없으면 프로그램 기본 설정 가져옴
	/* public <T> T getValue(String address) {
		String custom = GlobalSettings.getDefaultConfig().getAdd
	} */

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