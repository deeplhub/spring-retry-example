package com.xh.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author H.Yang
 * @date 2022/9/21
 */
@Slf4j
@EnableRetry
@SpringBootApplication
public class RetryApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(RetryApplication.class, args);
        ConfigurableEnvironment configurableEnvironment = ctx.getEnvironment();

        log.info("# ==============================");
        log.info("# It is running ... ");
        log.info("# APPLICATION     : " + configurableEnvironment.getProperty("spring.application.name"));
        log.info("# ACTIVE          : " + configurableEnvironment.getProperty("spring.profiles.active"));
        log.info("# PORT            : " + configurableEnvironment.getProperty("server.port"));
        log.info("# ==============================");
    }


}
