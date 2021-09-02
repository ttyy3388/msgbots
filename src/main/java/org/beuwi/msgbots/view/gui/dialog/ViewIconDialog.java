package org.beuwi.msgbots.view.gui.dialog;

import org.beuwi.msgbots.view.gui.dialog.base.DialogWrapper;

public abstract class ViewIconDialog extends DialogWrapper {
    /* private final ObservableMap<String, Object> namespace;
    private final FormLoader loader;
    private final BorderPane root;

    @FXML private BorderPane brpBoxRoot;
    @FXML private ImageView imvBoxIcon;
    @FXML private StackPane stpBoxMain;

    public ViewIconDialog(BoxType type) {
        loader = new FormLoader("dialog", "yes-or-no-dialog", this);
        namespace = loader.getNamespace();
        root = loader.getRoot();

        switch (type) {
            case INFO : imvBoxIcon.setImage(ResourceUtils.getImage("event_big")); break;
            case WARNING : imvBoxIcon.setImage(ResourceUtils.getImage("warning_big")); break;
            case ERROR : imvBoxIcon.setImage(ResourceUtils.getImage("error_big")); break;
        }
    }

    @Override
    public void setContent(Node content) {
        root.setCenter(content);
        super.setContent(root);
    }

    @Override
    public Node getContent() {
        return root.getCenter();
    } */
}