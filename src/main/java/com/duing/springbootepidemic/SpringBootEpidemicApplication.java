package com.duing.springbootepidemic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.duing.springbootepidemic.mapper")
@EnableScheduling
public class SpringBootEpidemicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEpidemicApplication.class, args);
    }

}
