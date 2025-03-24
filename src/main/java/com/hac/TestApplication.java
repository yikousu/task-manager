package com.hac;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Slf4j
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class, args);
        long endTime = System.currentTimeMillis();
        Environment env = context.getEnvironment();
        String port = env.getProperty("server.port", "8080");
        String springBootVersion = SpringBootVersion.getVersion();
        log.info("------------------------------------------------------------");
        log.info("Spring Boot 版本: {}", springBootVersion);
        log.info("启动成功，浏览器访问URL: http://localhost:{}", port);
        log.info("启动耗时: {} ms ({} s)", endTime - startTime, (endTime - startTime) / 1000.0);
        log.info("------------------------------------------------------------");
    }
}