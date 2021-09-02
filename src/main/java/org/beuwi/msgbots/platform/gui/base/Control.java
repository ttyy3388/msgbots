package org.beuwi.msgbots.platform.gui.base;

import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import java.util.List;

/*
 * 어떤건 [getStyleClass().add()]같이 [ObservableList]를 가져와서 적용시키는 반면,
 * 어떤건 [setValue()]같이 바로 적용시키는 등 [JavaFX]는 불필요한 구현 방법이 너무 많아서 모든것을 [addStyleClass()]같이 통합시키기 위해 번거롭지만 따로 구현한다.
 * 기준이 되는 플렛폼은 [Java Swing, WPF]을 기준으로 작성하며,
 * 각 컨트롤마다 재정의해야 할 부분이 다르기 때문에 꼭 구현해야 하는 공용은 [void], 일부만 구현하는 옵션은 [default void]로 정의한다.
 * <T, C>에서 <T : Type = ListView<Type>, <C : Control = EventTarget(Node)>이다. 만약 <T>가 없을 경우, <Void>를 전달한다.
 * 대표적인 예시는 [ButtonBase]를 참고하길 바란다.
 */
public interface Control<T, C extends EventTarget> {

	default Node findById(String id) {
		return null;
	}
	default Node findById(Region region, String id) {
		List<Object> children = null;
		/* if (control instanceof ListView) {
			children = ((ListView) control).getItems();
		} else */ {
			// getChildren() 과 달리 리스트 수정은 불가능함 : 반환만 가능
			region.getChildrenUnmodifiable();
		}

		for (Object child : children) {
			if (child instanceof Node) {
				Node node = (Node) child;
				if (node.getId().equals(id)) {
					return (Node) child;
				} else {
					continue;
				}
			}
			// else {
			// continue ;
			// }
		}

		return null;
	}
}
