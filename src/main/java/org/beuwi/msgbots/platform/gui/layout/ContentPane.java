package org.beuwi.msgbots.platform.gui.layout;

import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;

/*
 * AnchorPane 또는 StackPane을 이용해도 되지만
 * ScrollPane에선 자체적으로 콘텐츠 기능이 있기 떄문에
 * 스크롤 바를 사용 안하고 몇가지 튜닝 후 이용함
 * 즉 최종적으로 ContentPane는 "한 개"의 Pane만 Content로 지정할 수 있음
 * StackPane에서 한 개의 Content만 지정할 수 있다고 보면 됨
 */
public class ContentPane extends ScrollPane {
    {
        // StackPane과 똑같이 동작하도록
        setFitToWidth(true);
        setFitToHeight(true);

        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.NEVER);
    }
}
