package com;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by frinder_liu on 2016/8/25.
 */

//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
@EnableWebMvc
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        System.setProperty("spring.devtools.restart.exclude", "static/**,public/**");
//        System.setProperty("spring.devtools.restart.enabled", "false");
//        System.setProperty("spring.profiles.active", "dev");

        SpringApplication.run(Application.class, args);


//        SpringApplication app = new SpringApplication(Application.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.run(args);

//        new SpringApplicationBuilder()
//                .sources(Application.class)
//                .bannerMode(Banner.Mode.OFF)
//                .run(args);
    }

}
