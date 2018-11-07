package com.haier.prometheus.aspect;

import com.haier.prometheus.annotation.HttpMethodMetric;
import io.micrometer.core.instrument.*;
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
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class HttpMethodAspect {

    @Autowired
    private MeterRegistry meterRegistry;

    @Pointcut("@annotation(com.haier.prometheus.annotation.HttpMethodMetric)")
    public void pointcut(){}

    @Around(value = "pointcut()")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        Method targetMethod = ((MethodSignature)joinPoint.getSignature()).getMethod();
        Method currentMethod = ClassUtils.getUserClass(joinPoint.getTarget().getClass())
                .getDeclaredMethod(targetMethod.getName(),targetMethod.getParameterTypes());
        if (currentMethod.isAnnotationPresent(HttpMethodMetric.class)) {
            HttpMethodMetric methodMetric = currentMethod.getAnnotation(HttpMethodMetric.class);
            return processMetric(joinPoint, currentMethod, methodMetric);
        } else {
            return joinPoint.proceed();
        }
    }

    private Object processMetric(ProceedingJoinPoint joinPoint, Method currentMethod,
                                 HttpMethodMetric methodMetric) throws Throwable {
        String name = methodMetric.name();
        if (!StringUtils.hasText(name)) {
            name = currentMethod.getName();
        }
        String desc = methodMetric.description();
        if (!StringUtils.hasText(desc)) {
            desc = name;
        }
        String[] tags = methodMetric.tags();
        if (tags.length == 0) {
            tags = new String[2];
            tags[0] = name;
            tags[1] = name;
        }
        try {
            counterMetric(name,desc,tags);
            gaugeMetric(name,desc,tags);
            summaryMetric(name,desc,tags);
            return timerMetric(name,desc,tags,joinPoint);
        } catch (Throwable throwable) {
            throw new IllegalStateException(throwable);
        }
    }

    private void counterMetric(String name, String desc, String[] tags){
        Counter counter = Counter.builder(name + "-CounterName")
                .tag(tags[0] + "-Counter",tags[1] + "-Counter")
                .description(desc + "-counterDesc")
                .register(meterRegistry);
        counter.increment();
    }

    private Object timerMetric(String name, String desc, String[] tags,ProceedingJoinPoint joinPoint){
        Timer timer = Timer.builder(name + "-TimerName")

                .sla(Duration.ofMillis(1)
                        ,Duration.ofMillis(2)
                        ,Duration.ofMillis(4)
                        ,Duration.ofMillis(6)
                        ,Duration.ofMillis(8)
                        ,Duration.ofMillis(10)
                        ,Duration.ofMillis(15)
                        ,Duration.ofMillis(20)
                        ,Duration.ofMillis(40)
                        ,Duration.ofMillis(60)
                        ,Duration.ofMillis(80)
                        ,Duration.ofMillis(100))
//                .publishPercentiles(0.5, 0.95, 0.98, 0.99, 0.999)
                .minimumExpectedValue(Duration.ofMillis(1))
                .maximumExpectedValue(Duration.ofMillis(600))
//                .publishPercentileHistogram()

                .tags(tags)
                .description(desc + "-TimerDesc")
                .register(meterRegistry);
        return timer.record(() ->{
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new IllegalStateException(throwable);
            }
        });
    }
    private void gaugeMetric(String name, String desc, String[] tags){
        AtomicInteger atomicInteger = new AtomicInteger();
        Gauge gauge = Gauge.builder(name + "-gaugeName", atomicInteger, AtomicInteger::get)
                .tag(tags[0], tags[1] + "-Gauge")
                .description(desc + "-gaugeDesc")
                .register(meterRegistry);

        atomicInteger.addAndGet(1);
    }
    private void summaryMetric(String name, String desc, String[] tags){
        DistributionSummary summary = DistributionSummary.builder(name + "-summaryName")
//                .scale(100)
//                .sla(70, 80, 90)
                .tag(tags[0] , tags[1] + "-Summary" )
                .description(desc + "-summaryDesc")
                .register(meterRegistry);
        summary.record(1D);
    }

}
