package org.beuwi.msgbots.program.gui.control;

import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import org.beuwi.msgbots.openapi.Project;
import org.beuwi.msgbots.manager.ScriptManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.program.app.action.OpenDesktopAction;
import org.beuwi.msgbots.program.app.view.actions.AddMainAreaTabAction;
import org.beuwi.msgbots.program.app.view.actions.OpenDebugTabAction;
import org.beuwi.msgbots.program.app.view.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.program.app.view.dialogs.DeleteBotDialog;
import org.beuwi.msgbots.program.app.view.dialogs.RenameBotDialog;
import org.beuwi.msgbots.program.app.view.tabs.GlobalConfigTab;
import org.beuwi.msgbots.program.gui.layout.GridPane;
import org.beuwi.msgbots.program.utils.AllSVGIcons;
import org.beuwi.msgbots.program.utils.GlobalActions;

public class BotItem extends GridPane {
	@FXML private CheckBox chkCompiled = new CheckBox();
	@FXML private Label lblName = new Label();
	@FXML private Button btnReload = new Button();
	@FXML private ToggleButton tgbPower = new ToggleButton();

	private final FormLoader loader = new FormLoader();
	// 굳이 [GridPane]로 구현 한 이유는, [HBox]로 할 경우 텍스트가 밖으로 삐져나오기 때문임

	private BotView parent;

	public BotItem(Project project) {
		loader.setName("bot-item-frame");
		loader.setRoot(this);
		loader.setController(this);
		loader.load();

		addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			MouseButton button = event.getButton();
			if (button.equals(MouseButton.PRIMARY)) {
				OpenDebugTabAction.getInstance().execute(project);
			}
		});

		new ContextMenu(
			GlobalActions.COMPILE.custom(event -> {
				ScriptManager.initScript(project, true, false);
			}).toMenu(),
			GlobalActions.TOGGLE_POWER.custom(event -> {
				tgbPower.setSelected(!tgbPower.isSelected());
			}).toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.COPY_PATH.toMenu(),
			GlobalActions.COPY_RELATIVE_PATH.toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.SHOW_IN_EXPLORER.custom(event -> {
				OpenDesktopAction.getInstance().execute(project.getDirectory());
			}).toMenu(),
			MenuItem.getSeparator(),
			GlobalActions.RENAME_BOT.custom(event -> {
				OpenDialogBoxAction.getInstance().execute(RenameBotDialog.getInstance(project.getName()));
			}).toMenu(),
			GlobalActions.DELETE_BOT.custom(event -> {
				OpenDialogBoxAction.getInstance().execute(DeleteBotDialog.getInstance(project.getName()));
			}).toMenu(),
			MenuItem.getSeparator(),
			// 설정 탭을 띄우고, 탭에서 해당 봇을 설정에 포커스가 가도록
			GlobalActions.BOT_SETTING.custom(event -> {
				AddMainAreaTabAction.getInstance().execute(GlobalConfigTab.getInstance());
				NaviView view1 = GlobalConfigTab.getInstance().getNaviView();
				NaviItem item1 = view1.getTab("Bots");
				if (item1 != null) {
					view1.selectTab(item1);
					NaviView view2 = (NaviView) item1.getContent();
					NaviItem item2 = view2.getTab(project.getName());
					if (item2 != null) {
						view2.selectTab(item2);
						item2.requestFocus();
					}
				}
			}).toMenu()
		).setNode(this);

		project.powerProperty().addListener((observable, oldValue, newValue) -> {
			tgbPower.setSelected(newValue);
		});
		project.compiledProperty().addListener((observable, oldValue, newValue) -> {
			chkCompiled.setSelected(newValue);
		});

		btnReload.setGraphic(AllSVGIcons.get("Bot.Reload"));
		btnReload.setOnAction(event -> {
			ScriptManager.initScript(project, true, false);
		});

		tgbPower.setGraphic(AllSVGIcons.get("Bot.Power", 15, 15));
		tgbPower.selectedProperty().addListener((observable, oldValue, newValue) -> {
			project.setPower(newValue);
		});

		lblName.setText(project.getName());
		tgbPower.setSelected(project.getPower());
		chkCompiled.setDisable(true);
		chkCompiled.setSelected(project.isCompiled());

		setId(project.getName());
		getStyleClass().add("bot-item");
	}

	public BotView getView() {
		return parent;
	}
	public void setView(BotView parent) {
		this.parent = parent;
	}

	public String getName() {
		return lblName.getText();
	}

	/* private final BooleanProperty powerProperty = new SimpleBooleanProperty();
	public void setPower(boolean power) {
		powerProperty.set(power);
	}
	public boolean getPower() {
		return powerProperty.get();
	}
	public ReadOnlyBooleanProperty powerProperty() {
		return powerProperty;
	}

	private final BooleanProperty compiledProperty = new SimpleBooleanProperty();
	public void setCompiled(boolean compiled) {
		compiledProperty.set(compiled);
	}
	public boolean isCompiled() {
		return compiledProperty.get();
	}
	public ReadOnlyBooleanProperty compiledProperty() {
		return compiledProperty;
	} */
}