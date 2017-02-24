package com.rpc.config;

import com.rpc.service.IService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapProxyFactoryBean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * Created by frinder6 on 2017/2/24.
 */
@Configuration
public class RpcContext {

    @Bean(name = "rmiService")
    public RmiProxyFactoryBean rmiProxyFactoryBean() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        String url = "rmi://localhost:8888/rmi.htm";
        bean.setServiceUrl(url);
        bean.setServiceInterface(IService.class);
        return bean;
    }

    @Bean(name = "httpInvokerService")
    public HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean() {
        HttpInvokerProxyFactoryBean bean = new HttpInvokerProxyFactoryBean();
        String url = "http://localhost:8081/httpinvoker.htm";
        bean.setServiceUrl(url);
        bean.setServiceInterface(IService.class);
        return bean;
    }

    @Bean(name = "hessianService")
    public HessianProxyFactoryBean hessianProxyFactoryBean() {
        HessianProxyFactoryBean bean = new HessianProxyFactoryBean();
        String url = "http://localhost:8081/hessian.htm";
        bean.setServiceUrl(url);
        bean.setServiceInterface(IService.class);
        return bean;
    }

    @Bean(name = "burlapService")
    public BurlapProxyFactoryBean burlapProxyFactoryBean() {
        BurlapProxyFactoryBean bean = new BurlapProxyFactoryBean();
        String url = "http://localhost:8081/burlap.htm";
        bean.setServiceUrl(url);
        bean.setServiceInterface(IService.class);
        return bean;
    }

}
