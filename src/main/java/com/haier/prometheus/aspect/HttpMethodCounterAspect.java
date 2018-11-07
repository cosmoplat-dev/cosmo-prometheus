package com.haier.prometheus.aspect;

import com.haier.prometheus.annotation.CounterMethodMetric;
import io.micrometer.core.instrument.Counter;
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


@Aspect
@Component
public class HttpMethodCounterAspect{

    @Autowired
    private MeterRegistry meterRegistry;

    @Pointcut("@annotation(com.haier.prometheus.annotation.CounterMethodMetric)")
    public void pointcut(){}


    @Around(value = "pointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Method targetMethod = ((MethodSignature)joinPoint.getSignature()).getMethod();
        Method currentMethod = ClassUtils.getUserClass(joinPoint.getTarget().getClass())
                .getDeclaredMethod(targetMethod.getName(),targetMethod.getParameterTypes());
        if (currentMethod.isAnnotationPresent(CounterMethodMetric.class)) {
            CounterMethodMetric counterMethodMetric = currentMethod.getAnnotation(CounterMethodMetric.class);
            return processMetric(joinPoint, currentMethod, counterMethodMetric);
        } else {
            return joinPoint.proceed();
        }
    }

    private Object processMetric(ProceedingJoinPoint joinPoint, Method currentMethod,
                                 CounterMethodMetric counterMethodMetric) throws Throwable {
        String name = counterMethodMetric.name();
        if (!StringUtils.hasText(name)) {
            name = currentMethod.getName();
        }
        String desc = counterMethodMetric.description();
        if (!StringUtils.hasText(desc)) {
            desc = name;
        }
        String[] tags = counterMethodMetric.tags();
        Counter counter = Counter.builder(name + "-counterName")
                .tag(tags[0], tags[1])
                .description(desc + "-counterDesc")
                .register(meterRegistry);
        counter.increment();
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }
    }

}
