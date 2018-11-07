package com.haier.prometheus.aspect;

import com.haier.prometheus.annotation.SummaryMethodMetric;
import io.micrometer.core.instrument.DistributionSummary;
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
public class HttpMethodSummaryAspect {

    @Autowired
    private MeterRegistry meterRegistry;

    @Pointcut("@annotation(com.haier.prometheus.annotation.SummaryMethodMetric)")
    public void pointcut(){}


    @Around(value = "pointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Method targetMethod = ((MethodSignature)joinPoint.getSignature()).getMethod();
        Method currentMethod = ClassUtils.getUserClass(joinPoint.getTarget().getClass())
                .getDeclaredMethod(targetMethod.getName(),targetMethod.getParameterTypes());
        if (currentMethod.isAnnotationPresent(SummaryMethodMetric.class)) {
            SummaryMethodMetric methodMetric = currentMethod.getAnnotation(SummaryMethodMetric.class);
            return processMetric(joinPoint, currentMethod, methodMetric);
        } else {
            return joinPoint.proceed();
        }
    }

    private Object processMetric(ProceedingJoinPoint joinPoint, Method currentMethod,
                                 SummaryMethodMetric methodMetric) throws Throwable {
        String name = methodMetric.name();
        if (!StringUtils.hasText(name)) {
            name = currentMethod.getName();
        }
        String desc = methodMetric.description();
        if (!StringUtils.hasText(desc)) {
            desc = name;
        }
        String[] tags = methodMetric.tags();
        DistributionSummary summary = DistributionSummary.builder(name + "-summaryName")
                .tag(tags[0] , tags[1] )
                .description(desc + "-summaryDesc")
                .register(meterRegistry);
        summary.record(1D);
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }
    }


}
