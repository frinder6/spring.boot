package com.rpc.config;

import com.rpc.service.IService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Created by frinder6 on 2017/2/24.
 */
@Configuration
public class RpcContext {

    @Bean
    public RmiServiceExporter rmiServiceExporter(IService rmiServiceImpl) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("rmi.htm");
        exporter.setService(rmiServiceImpl);
        exporter.setServiceInterface(IService.class);
        exporter.setRegistryPort(8888);
        return exporter;
    }

    @Bean(name = "/httpinvoker.htm")
    public HttpInvokerServiceExporter httpInvokerServiceExporter(IService httpInvokerServiceImpl) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(httpInvokerServiceImpl);
        exporter.setServiceInterface(IService.class);
        return exporter;
    }

    @Bean(name = "/hessian.htm")
    public HessianServiceExporter hessianServiceExporter(IService hessianServiceImpl) {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(hessianServiceImpl);
        exporter.setServiceInterface(IService.class);
        return exporter;
    }

    @Bean(name = "/burlap.htm")
    public BurlapServiceExporter burlapServiceExporter(IService burlapServiceImpl) {
        BurlapServiceExporter exporter = new BurlapServiceExporter();
        exporter.setService(burlapServiceImpl);
        exporter.setServiceInterface(IService.class);
        return exporter;
    }

}
