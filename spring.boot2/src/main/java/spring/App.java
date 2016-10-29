package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * spring boot 启动器
 */
@SpringBootApplication(scanBasePackages = {"spring.batch",
        "spring.datasource",
        "spring.runner",
        "spring.yaml"})
@EnableAsync
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
