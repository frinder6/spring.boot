package spring.test;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.contoller.advice.MyException;

import java.util.Random;

/**
 * 测试 spring boot 启动器
 */
//@RestController
@Controller
public class TestController  implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("print")
    public String printBeans(ModelMap map) {
        String[] beans = applicationContext.getBeanDefinitionNames();
        map.addAttribute("beans", beans);
        return "index";
    }

    @RequestMapping("/")
    public String say(){
        return "index";
    }

    @RequestMapping("/exp")
    public String testException() throws Exception {
        throw new Exception("测试@ControllerAdvice");
    }


    @RequestMapping("/my/exp")
    public String testMyException() throws MyException {
        throw new MyException("测试@ControllerAdvice");
    }


//    public static void main(String[] args) {
//        String[] starts = {"spring-boot-starter-thymeleaf","spring-boot-starter-ws","spring-boot-starter-data-couchbase","spring-boot-starter-artemis","spring-boot-starter-web-services","spring-boot-starter-mail","spring-boot-starter-data-redis","spring-boot-starter-web","spring-boot-starter-data-gemfire","spring-boot-starter-activemq","spring-boot-starter-data-elasticsearch","spring-boot-starter-integration","spring-boot-starter-test","spring-boot-starter-hornetq","spring-boot-starter-jdbc","spring-boot-starter-mobile","spring-boot-starter-validation","spring-boot-starter-hateoas","spring-boot-starter-jersey","spring-boot-starter-data-neo4j","spring-boot-starter-websocket","spring-boot-starter-aop","spring-boot-starter-amqp","spring-boot-starter-data-cassandra","spring-boot-starter-social-facebook","spring-boot-starter-jta-atomikos","spring-boot-starter-security","spring-boot-starter-mustache","spring-boot-starter-data-jpa","spring-boot-starter","spring-boot-starter-velocity","spring-boot-starter-groovy-templates","spring-boot-starter-freemarker","spring-boot-starter-batch","spring-boot-starter-redis","spring-boot-starter-social-linkedin","spring-boot-starter-cache","spring-boot-starter-data-solr","spring-boot-starter-data-mongodb","spring-boot-starter-jooq","spring-boot-starter-jta-narayana","spring-boot-starter-cloud-connectors","spring-boot-starter-jta-bitronix","spring-boot-starter-social-twitter","spring-boot-starter-data-rest","spring-boot-starter-actuator","spring-boot-starter-remote-shell"};
//        String template = "<dependency>\n" +
//                "            <groupId>org.springframework.boot</groupId>\n" +
//                "            <artifactId>%s</artifactId>\n" +
//                "            <version>${spring.boot.version}</version>\n" +
//                "        </dependency>";
//
//        for (String start : starts) {
//            System.out.println(String.format(template, start));
//        }
//    }




    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
