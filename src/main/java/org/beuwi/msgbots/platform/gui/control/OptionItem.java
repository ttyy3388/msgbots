package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.layout.Priority;

import org.beuwi.msgbots.manager.FileManager;
import org.beuwi.msgbots.platform.app.action.WriteImageFileAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.platform.app.view.actions.OpenFileChooserAction;
import org.beuwi.msgbots.platform.app.view.dialogs.ChooseFileDialog;
import org.beuwi.msgbots.platform.gui.editor.Editor;
import org.beuwi.msgbots.platform.gui.enums.TextType;
import org.beuwi.msgbots.platform.gui.enums.ThemeType;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.layout.VBox;
import org.beuwi.msgbots.platform.util.ResourceUtils;
import org.beuwi.msgbots.platform.util.SharedValues;
import org.beuwi.msgbots.setting.GlobalSettings;
import org.beuwi.msgbots.setting.SharedSettings;

import java.io.File;
import java.util.List;

// Option Box
public class OptionItem extends VBox<Node> {
	private static final String DEFAULT_STYLE_CLASS = "option-item";

	// private static final Insets DEFAULT_PADDING_VALUE = new Insets(10, 0, 10, 10);
	// private static final Insets DEFAULT_MARGIN_VALUE = new Insets(15);
	private static final int DEFAULT_SPACING_VALUE = 5;

	// Title [ Content ]
	private final StringProperty titleProperty = new SimpleStringProperty();
	private final ObjectProperty<Node> contentProperty = new SimpleObjectProperty<>();
	private final StringProperty textProperty = new SimpleStringProperty();

	// address : "type:head:value > "global:program:start_auto_compile"
	private final StringProperty addressProperty = new SimpleStringProperty();
	// value start_auto_compile
	// private final StringProperty valueProperty = new SimpleStringProperty();

	private final Label titleLabel = new Label(); // 간단한 제목 (밝음)
	private final StackPane contentArea = new StackPane();
	private final Label textLabel = new Label(); // 자세한 설명 (흐림)

	private OptionView parent;

	{
		VBox.setVgrow(contentArea, Priority.ALWAYS);
	}

	public void initValue() {
		// 주소에 "{$name}"과 같이 변수 삽입 부분이 남아있다면 작동 X
		if (getAddress().indexOf("$") != -1) {
			return ;
		}

		if (getContent() instanceof Editor) {
			Editor control = (Editor) getContent();
			if (getAddress().equals("control:editor:edit_script_default")) {
				// 초기값은 리소스에 내두고 파일 변경 시 데이터 폴더에 생성하는 방식으로 했었으나
				// 그렇게 하면 곳곳에 예외에 관한 코드를 작성해야 하기에 그렇게 까지 할 필요는 없을 거 같아 기존 방식 이용
				control.setFile(SharedValues.getFile("SCRIPT_DEFAULT_FILE"));
				/* String content;
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
					FileManager.save(file, control.getText());
				}); */
			}
			else if (getAddress().equals("control:editor:edit_script_unified")) {
				control.setFile(SharedValues.getFile("SCRIPT_UNIFIED_FILE"));
				/* String content;
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
					FileManager.save(file, control.getText());
				}); */
			}
		}
		if (getContent() instanceof Button) {
			Button control = (Button) getContent();
			/* if (getAddress().equals("control:button:choose_adb_file")){
				File file = OpenFileChooserAction.execute("Choose Adb File",
						"File", "*.exe");
				// 파일 이름으로 체크함 "adb.exe"
				if (file != null || file.getName() != "adb.exe") {
					SharedSettings.setData(getAddress(), );
				}
			} */
			/* if (getAddress().equals("control:button:cleanup_user_setting")){
				if (SharedValues.GLOBAL_CONFIG_FILE != null) {

				}
			} */
			if (getAddress().equals("control:button:choose_bot_path")){
				control.setOnAction(event->{
					// OpenDialogBoxAction.execute(new ChooseFileDialog());
				});
			}
			else if (getAddress().equals("control:button:change_bot_profile")) {
				control.setOnAction(event -> {
					File file = OpenFileChooserAction.execute("Change Bot Profile",
						"Image File", "*.jpg", "*.png", "*.gif");
					if (file != null) {
						WriteImageFileAction.execute(file, "png", SharedValues.getFile("PROFILE_BOT_FILE"));
					}
				});
			}
			else if (getAddress().equals("control:button:change_sender_profile")) {
				control.setOnAction(event -> {
					File file = OpenFileChooserAction.execute("Change Sender Profile",
						"Image File", "*.jpg", "*.png", "*.gif");
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
		// 외부에서 파일 값을 변경했을 경우 : 보류(GlobalSettings 참조)
		// GlobalSettings.addChangeListener(this::initValue);

		titleLabel.getStyleClass().add("title-label");
		textLabel.getStyleClass().add("text-label");
		contentArea.setAlignment(Pos.CENTER_LEFT);
		contentArea.getStyleClass().add("content-area");

		titleProperty().addListener(change -> {
			String title = getTitle();
			List<Node> list = getChildren();
			// 텍스트가 없는 경우 목록에서 제거함(빈공간이 남기때문에 제거)
			if (title == null) {
				// 목록에 이미 포함 돼 있다면 제거
				if (list.contains(titleLabel)) {
					list.remove(titleLabel);
				}
			}
			// 텍스트가 입력된 경우
			else {
				// 목록에 없다면 첫 번째에 추가
				if (!list.contains(titleLabel)) {
					list.add(0, titleLabel);
				}

				titleLabel.setText(title);
			}
		});

		textProperty().addListener(change -> {
			String text = getText();
			List<Node> list = getChildren();
			// 텍스트가 없는 경우 목록에서 제거함(빈공간이 남기때문에 제거)
			if (text == null) {
				// 목록에 이미 포함 돼 있다면 제거
				if (list.contains(textLabel)) {
					list.remove(textLabel);
				}
			}
			// 텍스트가 입력된 경우
			else {
				// 목록에 없다면 끝에 추가
				if (!list.contains(textLabel)) {
					list.add(textLabel);
				}

				textLabel.setText(text);
			}
		});

		contentProperty().addListener(change -> {
			contentArea.getChildren().setAll(getContent());
		});

		addressProperty().addListener(event -> initValue());

		// setPadding(DEFAULT_MARGIN_VALUE);
		// setPadding(DEFAULT_PADDING_VALUE);
		setSpacing(DEFAULT_SPACING_VALUE);
		getChildren().setAll(
			// titleLabel,
			contentArea
			// textLabel
		);
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
	public void setText(String text) {
		textProperty.set(text);
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
	public String getText() {
		return textProperty.get();
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
	public StringProperty textProperty() {
		return textProperty;
	}
	public StringProperty addressProperty() {
		return addressProperty;
	}
	public ObjectProperty<Node> contentProperty() {
		return contentProperty;
	}
}