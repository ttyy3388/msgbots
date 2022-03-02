package org.beuwi.msgbots.view.gui.base;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.css.Styleable;
import javafx.scene.Node;
import javafx.scene.layout.Region;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/*
 * <참고사항 1>
 * 어떤건 [getStyleClass().add()]같이 [ObservableList]를 가져와서 적용시키는 반면,
 * 어떤건 [setValue()]같이 바로 적용시키는 등 통합된 구현 방법이 없기에 번거롭지만 따로 구현함
 * 기준이 되는 플렛폼은 [Java Swing, WPF]을 기준으로 작성하며,
 * 각 컨트롤마다 재정의해야 할 부분이 다르기 때문에 꼭 구현해야 하는 공용은 [void], 일부만 구현하는 옵션은 [default void]로 정의함
 * 대표적인 예시는 [ButtonBase]를 참고바람
 *
 * <참고사항 2>
 * [Control]을 상속했거나 해당 클래스의 루트에 포함된 객체의 경우 상속된 메소드를 그대로 사용하나(getProperty("test")),
 * JavaFX 기본 객체거나 하위 객체의 경우 직접 객체를 인자로 전달하여 사용함(getProperty(object, "test")).
 * (JavaFX 기본 객체의 경우 따로 [Control]을 상속시키기엔 복잡하기 때문임)
 * 즉, 간단히 말하면 [Control]이 상속된 객체에선 (Object)인자를 생략 가능함
 *
 * <참고사항 3>
 * 컨트롤이라고 해서 모두 [Node, Region]의 하위 객체가 아니므로
 * 특수한 메소드의 경우 [Object]로 인자를 넘겨받아 [Invoke]로 처리함
 */

// EventTarget
public interface Control /* <N extends Node> */ {
	// String STYLED_PROPERTY = "styled";

	// private <T> ObservableValue<T> getProperty(String name);

	// (Property)라고 하면 (Key, Value)와 혼동될 수 있으므로 (FXProperty)라 명함
	// [Object] 안에 [...Property] 메소드가 포함 돼 있어야함
	<T> ObservableValue<T> getFXProperty(String name);
	default <T> ObservableValue<T> getFXProperty(Object object, String name) {
		ObservableValue result = null;

		Class<?> clazz = object.getClass();
		String mname = name + "Property";
		// Method[] methods = clazz.getMethods();
		Method method = null;

		// [타입1] 해당 클래스에서 직접 선언한 (private)등 제한 메소드라면
		try {
			// Method[] methods = clazz.getDeclaredMethods();
			method = clazz.getMethod(mname);
		}
		catch (NoSuchMethodException e1) {
			// [타입2] 기본 클래스에 있는 메소드라면
			try {
				method = clazz.getDeclaredMethod(mname);
			}
			// 여기서도 찾지 못했다면 에러 출력
			catch (NoSuchMethodException e2) {
				// [타입3] 상속받았는데 부모 클래스에 선언된 (private)메소드를 가져오는 경우라면
				// 아래와 같은 경우면 상당히 복잡해지기 때문에, (property)는 (private)로 선언을 금함
				/* Class<?> sclazz = clazz.getSuperclass();
				try {
					method = sclazz.getDeclaredMethod(mname);
				} */

				e2.printStackTrace();
				// return null;
			}
		}

		try {
			// if private
			assert method != null;
			method.setAccessible(true);
			result = (ObservableValue) method.invoke(object);
		}
		catch (InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return result;
	}

	void setPseudoClass(String pseudo, boolean active);
	default void setPseudoClass(Node node, String pseudo, boolean active) {
		PseudoClass clazz = PseudoClass.getPseudoClass(pseudo);
		// Node node = styleable.getStyleableNode();
		if (node != null) {
			node.pseudoClassStateChanged(clazz, active);
		}
	}
	default void setPseudoClass(Styleable styleable, String pseudo, boolean active) {
		Node node = styleable.getStyleableNode();
		System.out.println(styleable + " : " + node);
	}

	// 루트 객체의 경우 사용하고, 하위 객체의 경우 [getProperty()]를 이용하여 리스너 등록
	<T> void addChangeListener(String property, ChangeListener <T> listener);
	void addChangeListener(String property, InvalidationListener listener);
	default <T> void addChangeListener(ObservableValue property, ChangeListener<T> listener) {
		property.addListener(listener);
	}
	default void addChangeListener(ObservableValue property, InvalidationListener listener) {
		property.addListener(listener);
	}

	void removeChangeListener(String property, ChangeListener listener);
	void removeChangeListener(String property, InvalidationListener listener);
	default void removeChangeListener(ObservableValue property, ChangeListener listener) {
		property.removeListener(listener);
	}
	default void removeChangeListener(ObservableValue property, InvalidationListener listener) {
		property.removeListener(listener);
	}

	Node findById(String id);
	default Node findById(Styleable styleable, String id) {
		List<Node> children = null;
		Node node = styleable.getStyleableNode();
		/* if (control instanceof ListView) {
			children = ((ListView) control).getItems();
		}*/

		if (node instanceof Region region) {
			// [getChildren()] 과 달리 리스트 수정은 불가능함 : 반환만 가능
			children = region.getChildrenUnmodifiable();
		}

		assert children != null;
		for (Node child : children) {
			if (child.getId().equals(id)) {
				return child;
			}
		}

		return null;
	}

	void addStyleClass(String... style);
	default void addStyleClass(Styleable styleable, String... style) {
		styleable.getStyleClass().addAll(style);
	}
	void addStyleClass(int index, String style);
	default void addStyleClass(Styleable styleable,int index,  String style) {
		styleable.getStyleClass().add(index, style);
	}
	void setStyleClass(int index, String style);
	default void setStyleClass(Styleable styleable, int index, String style) {
		styleable.getStyleClass().set(index, style);
	}
	void initStyleClass(String... style);
	default void initStyleClass(Styleable styleable, String... style) {
		styleable.getStyleClass().setAll(style);
	}
}
