package com.haier.prometheus.aspect;


import com.haier.prometheus.annotation.TimerMethodMetric;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
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
public class HttpMethodTimerAspect {

    @Autowired
    private MeterRegistry meterRegistry;

    @Pointcut("@annotation(com.haier.prometheus.annotation.TimerMethodMetric)")
    public void pointcut(){}


    @Around(value = "pointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Method targetMethod = ((MethodSignature)joinPoint.getSignature()).getMethod();
        Method currentMethod = ClassUtils.getUserClass(joinPoint.getTarget().getClass())
                .getDeclaredMethod(targetMethod.getName(),targetMethod.getParameterTypes());
        if (currentMethod.isAnnotationPresent(TimerMethodMetric.class)) {
            TimerMethodMetric methodMetric = currentMethod.getAnnotation(TimerMethodMetric.class);
            return processMetric(joinPoint, currentMethod, methodMetric);
        } else {
            return joinPoint.proceed();
        }
    }

    private Object processMetric(ProceedingJoinPoint joinPoint, Method currentMethod,
                                 TimerMethodMetric methodMetric) throws Throwable {
        String name = methodMetric.name();
        if (!StringUtils.hasText(name)) {
            name = currentMethod.getName();
        }
        String desc = methodMetric.description();
        if (!StringUtils.hasText(desc)) {
            desc = name;
        }
        String[] tags = methodMetric.tags();
        Timer timer = Timer.builder(name + "-TimerName").tags(tags)
                .description(desc + "-TimerDesc")
                .register(meterRegistry);
        return timer.record(() -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new IllegalStateException(throwable);
            }
        });
    }


}
