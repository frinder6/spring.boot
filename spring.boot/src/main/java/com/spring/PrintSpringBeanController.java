package com.spring;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by frinder6 on 2016/9/1.
 */

@RestController
@RequestMapping("bean")
public class PrintSpringBeanController implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PropertiesBean propertiesBean;

    @RequestMapping("print/1")
    public String printBeans() {
        String[] beans = applicationContext.getBeanDefinitionNames();
        return JSON.toJSONString(beans);
    }

    @RequestMapping("print/2")
    public String print() {
        return propertiesBean.getHost() + ":" +
                propertiesBean.getPort() + ":" +
                JSON.toJSONString(propertiesBean.getServers()) + ":" +
                JSON.toJSONString(propertiesBean.getProd()) + ":" +
                JSON.toJSONString(propertiesBean.getDev());
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
