package study.stepup.log;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface LogTransformation {
    String value() default "LogTransformation.log";
}
