package client.consumer.annotation;


import sun.jvm.hotspot.interpreter.BytecodeGetStatic;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Consumer {

    public static final String DEFAULT_GROUP = "DEFAULT";
    public static final String EMPTY_GROUP = "";                //use in consuming broadcast messages


    boolean transaction() default true;

    String group() default DEFAULT_GROUP;

    String topic();
}
