package org.beuwi.msgbots.program.gui.dialog.base;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import org.beuwi.msgbots.openapi.FormLoader;
import org.beuwi.msgbots.program.app.manager.ViewManager;
import org.beuwi.msgbots.program.gui.control.Label;
import org.beuwi.msgbots.program.gui.layout.BorderPane;
import org.beuwi.msgbots.program.gui.layout.HBox;
import org.beuwi.msgbots.program.gui.window.WindowType;
import org.beuwi.msgbots.program.gui.window.WindowWrapper;
import org.beuwi.msgbots.utils.ResourceUtils;

import java.awt.Toolkit;

// [DialogWrapper]로만 접근 가능
abstract class DialogFrame extends WindowWrapper {
	// 다이얼로그 OK 버튼 또는 엔터 키 등 이벤트 시 발동
	// 각 다이얼로그의 액션이 실행될 때 동작함
	private EventHandler<ActionEvent> onActionHandler;
	// 다이얼로그가 종료될 때 동작함
	private EventHandler<ActionEvent> onCloseHandler;
	// 다이얼로그가 오픈될 때 동작함
	private EventHandler<ActionEvent> onOpenHandler;
	// 다이얼로그가 초기화될 때 동작함
	private EventHandler<ActionEvent> onInitHandler;

	// 이벤트는 보통 [Init > Open > Action > Close]순으로 작동함
	public void setOnAction(EventHandler<ActionEvent> handler) {
		onActionHandler = handler;
	}
	public void setOnOpen(EventHandler<ActionEvent> handler) {
		onOpenHandler = handler;
	}
	public void setOnClose(EventHandler<ActionEvent> handler) {
		onCloseHandler = handler;
	}
	public void setOnInit(EventHandler<ActionEvent> handler) {
		onInitHandler = handler;
	}

	private StageStyle winStyle = StageStyle.UNDECORATED; // Default
	private Modality modality = Modality.WINDOW_MODAL; // Default
	private Stage owner = ViewManager.getStage(); // Default
	public void setModality(Modality modality) {
		this.modality = modality;
	}
	public void setWinStyle(StageStyle winStyle) {
		this.winStyle = winStyle;
	}
	public void setOwner(Stage stage) {
		this.owner = stage;
	}

	// DIALOG
	@FXML private BorderPane brpBoxMain;
	@FXML private ImageView imvBoxIcon;
	// @FXML private HBox<Button> hbxBtnBar;

	// FOOTER
	@FXML private HBox hbxFooterBar;
	@FXML private Label lblFooterText;

	private final FormLoader loader;
	// Default Type : None
	private final DialogType type;
	private final Stage stage;

	// private Insets insets = null;
	private int margin = -1;
	private Node content;

	protected DialogFrame(DialogType type, Stage stage) {
		super(WindowType.DIALOG, stage);

		loader = new FormLoader();
		loader.setName("dialog-box-frame");
		loader.setController(this);
		loader.load();

		this.type = type;
		this.stage = stage;

		getStyleClass().add("dialog-box");
	}

	/*
	 * 함수에서 root.setContent(content)...로 안하고 this.content = content로하고
	 * create()에서 setContent(content)로 하는 이유는
	 * 1. create 사용이 용이함
	 * 2. 상속 시, setContent나 getContent를 상속할 경우가 있기 때문임
	 * 즉, 이러면 상속에서 오버라이딩을 해도 안전함
	 * create()에서는 content를 통해 접근하지, getContent로 접근하지 않기 때문임
	 * 나머지 함수도 동일하게 작동함
	 */
	@Override
	public void setContent(Node content) {
		this.content = content;
	}
	@Override
	public Node getContent() {
		return content;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	/* ------------------------------------------------------ */

	public void setUseFooterBar(boolean value) {
		Parent parent = hbxFooterBar.getParent();
		if (value) {
			if (parent == null) {
				brpBoxMain.setBottom(hbxFooterBar);
			}
		}
		else {
			if (parent != null) {
				brpBoxMain.getChildren().remove(hbxFooterBar);
			}
		}
	}
	public HBox getFooterBar() {
		return hbxFooterBar;
	}
	public Label getFooterLabel() {
		return lblFooterText;
	}

	/* ------------------------------------------------------ */


	public void create() {
		Insets insets = null;
		// 마진을 지정했을 경우 해당 마진 적용
		if (margin > 0) { insets = new Insets(margin); }
		// 마진을 0으로 지정한 경우 > 테두리를 위해 1로 변정
		if (margin == 0) { insets = new Insets(1); }
		// 마진을 지정하지 않았을 경우 건들지 않음(기본 마진 적용)
		else { insets = new Insets(15); }
		BorderPane.setMargin(brpBoxMain, insets);

		// 아이콘 부분도 [YesOrNoDialog]같이 [ViewIconDialog]로 따로 빼려고 했으나
		// 버튼과 아이콘 둘 다 가져오기 위해서는 다중상속을 해야하는데 불가능 하기 때문임

		switch (type) {
			// None : 아이콘 미포함 및 여백 제거
			case NONE :
				// StackPane.setMargin(brpBoxMain, null);
				brpBoxMain.getChildren().remove(imvBoxIcon);
				break;

			// Others : 아이콘 포함 및 여백 추가
			case INFO : imvBoxIcon.setImage(ResourceUtils.getImage("info_big")); break;
			case WARNING : imvBoxIcon.setImage(ResourceUtils.getImage("warning_big")); break;
			case ERROR : imvBoxIcon.setImage(ResourceUtils.getImage("error_big")); break;
		}

		brpBoxMain.setCenter(content);

		/* ------------------------------------------------------ */

		// 다이얼로그의 경우 최상위 이벤트로 보기 때문에 필터로 설정함
		stage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			switch (event.getCode()) {
				case ESCAPE : this.close(); break;
				/* case ENTER :
					this.daction();
					this.dclose();
					break; */
			}
		});
		stage.addEventFilter(WindowEvent.WINDOW_HIDING, event -> {
			/* if (onCloseHandler != null) {
				onCloseHandler.handle(null);
			} */
			this.close();
		});

		stage.initModality(modality);
		stage.initStyle(winStyle);
		stage.initOwner(owner);

		super.setContent(brpBoxMain);
		// super.setStage(stage);
		super.create();
	}

	/*
	 * 상속용도, 반환 값은 성공 여부
	 * setOnOpen(event ...), setOnClose(event ...)는 외부에서 접근할 때 사용,
	 * onOpen(), onClose()는 내부에서 선언될 때 사용함
	 */

	protected abstract boolean onInit();
	protected abstract boolean onOpen();
	protected abstract boolean onAction();
	protected abstract boolean onClose();

	private boolean created = false;
	// 호출용도
	public void open() {
		// Init 실행 후 Open 함수 실행
		onInit();
		// [setOnInit]이 설정됐으면
		if (onInitHandler != null) {
			onInitHandler.handle(null);
		}

		onOpen();
		// [setOnOpen]이 설정됐으면
		if (onOpenHandler != null) {
			onOpenHandler.handle(null);
		}

		// 처음 생성 시에만 호출
		if (!created) { create(); created = true; }
		// 이미 생성이 됐으면 띄우기만
		else { stage.show(); }
	}
	// 호출용도
	public void action() {
		boolean value = onAction();
		// 액션이 실패하면 비프음만 출력함
		if (!value) {
			Toolkit.getDefaultToolkit().beep(); // 비프음 출력
		}
		// 액션이 성공하면 핸들러 실행 및 닫음
		else {
			// [setOnAction]이 설정됐으면
			if (onActionHandler != null) {
				onActionHandler.handle(null);
			}
			close();
		}
	}
	protected void close() {
		onClose();
		// [setOnClose]이 설정됐으면
		if (onCloseHandler != null) {
			onCloseHandler.handle(null);
		}
		// this.close();
		stage.close();
	}
	/* protected void init() { } */
}