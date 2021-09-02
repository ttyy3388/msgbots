package org.beuwi.msgbots.platform.gui.control;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;

import org.beuwi.msgbots.base.Dfile;
import org.beuwi.msgbots.platform.gui.editor.Editor;
import org.beuwi.msgbots.platform.gui.layout.StackPane;
import org.beuwi.msgbots.platform.gui.layout.VBox;
import org.beuwi.msgbots.setting.SharedSettings;
import org.beuwi.msgbots.utils.SharedValues;

import java.util.List;

/*
 * [ADDRESS]
 * 1. 옵션 아이템에서 사용하는 방식이며, type:path[global:program.colorTheme] 값임
 * 1.1. 컨트롤과 같은 경우에도 사용하는데, 이 경우에는 control:button.chooseFile과 같은 형식으로 동작함
 */
public class ConfigItem extends VBox {
	// private static final Insets DEFAULT_PADDING_VALUE = new Insets(10, 0, 10, 10);
	// private static final Insets DEFAULT_MARGIN_VALUE = new Insets(15);
	// private static final int DEFAULT_SPACING_VALUE = 5;

	/*
	 * 레이아웃 형식
	 * [ Title ] : 간단한 제목(헤더) 텍스트
	 * [ Control ] : 옵션 컨트롤 노드 - Text Field, Text Area, Check Box ... 지정 가능
	 * [ Content ] : 옵션 내용 노드 - VBox, HBox 등 부수적인 요소 커스텀 가능
	 * [ Text ] : 자세한 설명 텍스트
	 */
	private final StringProperty titleProperty = new SimpleStringProperty(); // 제목은 필수로 지정해야 하며, 없을 경우를 생각하지 않음
	private final ObjectProperty<Node> controlProperty = new SimpleObjectProperty<>(); // 컨트롤은 필수로 지정해야 하며, 없을 경우를 생각하지 않음
	private final ObjectProperty<Node> contentProperty = new SimpleObjectProperty<>();
	private final StringProperty textProperty = new SimpleStringProperty();

	// address : "type.head.value" > "global.program.startAutoCompile"
	private final StringProperty addressProperty = new SimpleStringProperty();
	// private final StringProperty valueProperty = new SimpleStringProperty();

	private final Label titleLabel = new Label(); // 간단한 제목(밝음)
	private final StackPane controlArea = new StackPane();
	private final StackPane contentArea = new StackPane();
	private final Label textLabel = new Label(); // 자세한 설명(흐림)
	
	private ConfigView parent;

	{
		VBox.setVgrow(contentArea, Priority.ALWAYS);
	}

	public void initValue() {
		if (true) return ;

		String address = getAddress();
		Node control = getControl();
		System.out.println(address);
		// 주소에 "{$name}"과 같이 변수 삽입 부분이 남아있다면 작동 X
		if (address.contains("{&") && address.contains("}")) {
			return ;
		}

		if (control instanceof Editor) {
			Editor editor = (Editor) control;
			if (getAddress().equals("control.editor.editScriptDefault")) {
				Dfile dfile = SharedValues.getDfile("SCRIPT_DEFAULT_DFILE");
				editor.setText(dfile.getData());
				// 에디터는 포커스 이벤트가 안오므로 텍스트 변경으로 인식
				editor.textProperty().addListener(change -> {
					dfile.setData(editor.getText());
				});
			}
			else if (getAddress().equals("control:editor.editScriptUnified")) {
				Dfile dfile = SharedValues.getDfile("SCRIPT_UNIFIED_DFILE");
				editor.setText(dfile.getData());
				// 에디터는 포커스 이벤트가 안오므로 텍스트 변경으로 인식
				editor.textProperty().addListener(change -> {
					dfile.setData(editor.getText());
				});
			}
		}
		else if (control instanceof Button) {
		}
		else if (control instanceof CheckBox) {
			CheckBox checkbox = (CheckBox) control;
			checkbox.setSelected(SharedSettings.getBoolean(address));
			checkbox.selectedProperty().addListener(change -> {
				SharedSettings.setData(address, checkbox.isSelected());
			});
		}
		else if (control instanceof TextArea) {
			TextArea textbox = (TextArea) control;
			textbox.setText(SharedSettings.getString(address));
			textbox.focusedProperty().addListener(change -> {
				SharedSettings.setData(address, textbox.getText());
			});
		}
		else if (control instanceof TextField) {
			TextField textbox = (TextField) control;
			textbox.setText(SharedSettings.getString(address));
			textbox.focusedProperty().addListener(change -> {
				SharedSettings.setData(address, textbox.getText());
			});
		}
		else if (control instanceof ToggleButton) {
			ToggleButton button = (ToggleButton) control;
			button.setSelected(SharedSettings.getBoolean(address));
			button.selectedProperty().addListener(change -> {
				SharedSettings.setData(address, button.isSelected());
			});
		}
		else if (control instanceof Slider) {
			Slider slider = (Slider) control;
			slider.setValue(SharedSettings.getInt(address));
			slider.focusedProperty().addListener(change -> {
				SharedSettings.setData(address, slider.getValue());
			});
		}
	}

	public ConfigItem(/* @NamedArg("type") PrefType type */) {
		// 외부에서 파일 값을 변경했을 경우 : 보류(GlobalSettings 참조)
		// GlobalSettings.addChangeListener(this::initValue);

		titleLabel.getStyleClass().add("title-label");
		textLabel.getStyleClass().add("text-label");
		controlArea.setAlignment(Pos.CENTER_LEFT);
		contentArea.getStyleClass().add("control-area");
		contentArea.setAlignment(Pos.CENTER_LEFT);
		contentArea.getStyleClass().add("content-area");

		titleProperty().addListener(change -> {
			String title = getTitle();
			/* List<Node> list = getChildren();
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
			} */

			titleLabel.setText(title);
		});

		contentProperty().addListener(change -> {
			Node content = getContent();
			List<Node> list = getChildren();
			// 자식이 없는 경우 목록에서 제거함(빈공간이 남기때문에 제거)
			if (content == null) {
				// 목록에 이미 포함 돼 있다면 제거
				if (list.contains(contentArea)) {
					list.remove(contentArea);
				}
			}
			// 자식이 있는 경우
			else {
				// 목록에 없다면 두번째에 추가
				if (!list.contains(contentArea)) {
					list.add(2, contentArea);
				}
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
				// 목록에 없다면 끝(3)에 추가
				if (!list.contains(textLabel)) {
					list.add(3, textLabel);
				}

				textLabel.setText(text);
			}
		});

		controlProperty().addListener(change -> {
			controlArea.getChildren().setAll(getControl());
		});
		contentProperty().addListener(change -> {
			contentArea.getChildren().setAll(getContent());
		});
		addressProperty().addListener(change -> initValue());

		// setPadding(DEFAULT_MARGIN_VALUE);
		// setPadding(DEFAULT_PADDING_VALUE);
		setSpacing(8);
		getChildren().setAll(
			titleLabel,
			controlArea
		);
		getStyleClass().add("option-item");
	}

	// 유저가 설정한 옵션이 있으면 제일 먼저 가져오고
	// 없으면 프로그램 기본 설정 가져옴
	/* public <T> T getValue(String address) {
		String custom = GlobalSettings.getDefaultConfig().getAdd
	} */

	public StringProperty titleProperty() {
		return titleProperty;
	}
	public void setTitle(String title) {
		titleProperty.set(title);
	}
	public String getTitle() {
		return titleProperty.get();
	}

	public StringProperty textProperty() {
		return textProperty;
	}
	public void setText(String text) {
		textProperty.set(text);
	}
	public String getText() {
		return textProperty.get();
	}

	public ObjectProperty<Node> controlProperty() {
		return controlProperty;
	}
	public void setControl(Node control) {
		controlProperty.set(control);
	}
	public Node getControl() {
		return controlProperty.get();
	}
	
	public ObjectProperty<Node> contentProperty() {
		return contentProperty;
	}
	public void setContent(Node content) {
		contentProperty.set(content);
	}
	public Node getContent() {
		return contentProperty.get();
	}

	public StringProperty addressProperty() {
		return addressProperty;
	}
	public void setAddress(String address) {
		addressProperty.set(address);
	}
	public String getAddress() {
		return addressProperty.get();
	}
}