package org.beuwi.msgbots.view.gui.control;

import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import org.beuwi.msgbots.actions.OpenDesktopAction;
import org.beuwi.msgbots.base.Project;
import org.beuwi.msgbots.manager.ScriptManager;
import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.view.app.actions.AddMainAreaTabAction;
import org.beuwi.msgbots.view.app.actions.OpenDebugTabAction;
import org.beuwi.msgbots.view.app.actions.OpenDialogBoxAction;
import org.beuwi.msgbots.view.app.dialogs.DeleteBotDialog;
import org.beuwi.msgbots.view.app.dialogs.RenameBotDialog;
import org.beuwi.msgbots.view.app.tabs.GlobalConfigTab;
import org.beuwi.msgbots.view.gui.layout.GridPane;
import org.beuwi.msgbots.view.util.AllSVGIcons;
import org.beuwi.msgbots.view.util.StdActions;

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
			StdActions.COMPILE.handler(event -> {
				ScriptManager.initScript(project, true, false);
			}).toMenuItem(),
			StdActions.TOGGLE_POWER.handler(event -> {
				tgbPower.setSelected(!tgbPower.isSelected());
			}).toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.COPY_PATH.toMenuItem(),
			StdActions.COPY_RELATIVE_PATH.toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.SHOW_IN_EXPLORER.handler(event -> {
				OpenDesktopAction.getInstance().execute(project.getDirectory());
			}).toMenuItem(),
			MenuItem.getSeparator(),
			StdActions.RENAME_BOT.handler(event -> {
				OpenDialogBoxAction.getInstance().execute(RenameBotDialog.getInstance(project.getName()));
			}).toMenuItem(),
			StdActions.DELETE_BOT.handler(event -> {
				OpenDialogBoxAction.getInstance().execute(DeleteBotDialog.getInstance(project.getName()));
			}).toMenuItem(),
			MenuItem.getSeparator(),
			// 설정 탭을 띄우고, 탭에서 해당 봇을 설정에 포커스가 가도록
			StdActions.BOT_SETTING.handler(event -> {
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
			}).toMenuItem()
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