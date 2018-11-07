package com.haier.prometheus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan("com.haier")
@EnableSwagger2
@MapperScan("com.haier.prometheus.dao")
public class CosmoPrometheusApplication {

    public static void main(String[] args) {
        SpringApplication.run(CosmoPrometheusApplication.class, args);
    }
}
