package com.endava.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

/**
 * Created by vsaban on 3/15/2017.
 */
@Configuration
@ComponentScan("com.endava")
public class AppConfig {


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
