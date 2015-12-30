package com.heartkid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("com.heartkid")
public class App extends SpringBootServletInitializer {
    public static void main(final String[] args) {
        SpringApplication.run(new Object[]{App.class}, args);
    }

    @Override
    protected SpringApplicationBuilder configure(
            final SpringApplicationBuilder application) {
        return application.sources(new Object[]{App.class});
    }


}
