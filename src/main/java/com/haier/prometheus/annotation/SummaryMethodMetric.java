package com.haier.prometheus.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SummaryMethodMetric {

    String name() default "";

    String description() default "";

    String[] tags() default {};

}
