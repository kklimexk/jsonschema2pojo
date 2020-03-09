package org.jsonschema2pojo.valuehints;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValueHintDecimal {
    int precision() default 10;
    int scale() default 0;
}
