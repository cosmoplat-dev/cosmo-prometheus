package com.haier.prometheus.annotation;


import java.lang.annotation.*;

/**
 * prometheus拉取接口Timer,Counter,Guage,Summery四个维度数据的注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpMethodMetric {

    /**
     * 监控维度自定义名称
     * @return
     */
    String name() default "";

    /**
     * 监控维度描述
     * @return
     */
    String description() default "";

    /**
     * tag标签，两个值，第一个用来描述项目名称，第二个用来描述接口，以便区分查找
     * @return
     */
    String[] tags() default {};


}
