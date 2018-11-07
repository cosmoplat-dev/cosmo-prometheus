package com.haier.prometheus.aspect;

import com.haier.prometheus.annotation.GaugeMethodMetric;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;


@Aspect
@Component
public class HttpMethodGaugeAspect {


    @Autowired
    private MeterRegistry meterRegistry;

    @Pointcut("@annotation(com.haier.prometheus.annotation.GaugeMethodMetric)")
    public void pointcut(){}


    @Around(value = "pointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Method targetMethod = ((MethodSignature)joinPoint.getSignature()).getMethod();
        Method currentMethod = ClassUtils.getUserClass(joinPoint.getTarget().getClass())
                .getDeclaredMethod(targetMethod.getName(),targetMethod.getParameterTypes());
        if (currentMethod.isAnnotationPresent(GaugeMethodMetric.class)) {
            GaugeMethodMetric gaugeMethodMetric = currentMethod.getAnnotation(GaugeMethodMetric.class);
            return processMetric(joinPoint, currentMethod, gaugeMethodMetric);
        } else {
            return joinPoint.proceed();
        }
    }

    private Object processMetric(ProceedingJoinPoint joinPoint, Method currentMethod,
                                 GaugeMethodMetric gaugeMethodMetric) throws Throwable {
        String name = gaugeMethodMetric.name();
        if (!StringUtils.hasText(name)) {
            name = currentMethod.getName();
        }
        String desc = gaugeMethodMetric.description();
        if (!StringUtils.hasText(desc)) {
            desc = name;
        }
        String[] tags = gaugeMethodMetric.tags();
        AtomicInteger atomicInteger = new AtomicInteger();
        Gauge gauge = Gauge.builder(name + "-gaugeName", atomicInteger, AtomicInteger::get)
                .tag(tags[0], tags[1])
                .description(desc + "-gaugeDesc")
                .register(meterRegistry);
        atomicInteger.addAndGet(1);
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }
    }

}
