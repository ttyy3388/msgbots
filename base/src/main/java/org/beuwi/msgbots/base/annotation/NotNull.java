package org.beuwi.msgbots.base.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

import static java.lang.annotation.RetentionPolicy.CLASS;

@Retention(CLASS)
@Target({FIELD, METHOD, PARAMETER, LOCAL_VARIABLE})
public @interface NotNull {String value() default "";}