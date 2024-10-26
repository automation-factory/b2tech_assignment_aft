package annotation;

import java.lang.annotation.*;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Page {

    String url() default EMPTY;

}
