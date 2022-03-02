/* package org.beuwi.msgbots.base;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 이 인터페이스를 선언한 이유와 사용법은 하위 클래스 [Control]클래스에서 설명함
public interface FXProperty {
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
    
    // 루트 객체의 경우 사용하고, 하위 객체의 경우 [getProperty()]를 이용하여 리스너 등록
    void addChangeListener(String property, ChangeListener listener);
    void addChangeListener(String property, InvalidationListener listener);
    default void addChangeListener(ObservableValue property, ChangeListener listener) {
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
} */
