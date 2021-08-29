package org.beuwi.msgbots.program.gui.layout;

/*
 * [AnchorPane] 또는 [StackPane]을 이용해도 되지만,
 * [ScrollPane]에선 자체적으로 콘텐츠 기능이 있기 때문에 스크롤 바를 사용 안하고 몇가지 튜닝 후 이용함.
 * 즉 최종적으로 [ContentPane]는 "한 개"의 [Pane]만 [Content]로 지정할 수 있음.
 * [StackPane]에서 한 개의 [Content]만 지정할 수 있다고 보면 됨.
 */
public class ContentPane extends ScrollPane {
    {
        // [StackPane]과 똑같이 동작하도록 양 옆을 맞춤
        setFitToWidth(true);
        setFitToHeight(true);

        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.NEVER);
    }
}
