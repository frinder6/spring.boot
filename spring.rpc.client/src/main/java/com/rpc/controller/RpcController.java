package com.rpc.controller;

import com.caucho.burlap.client.BurlapProxyFactory;
import com.caucho.hessian.client.HessianProxyFactory;
import com.rpc.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

/**
 * Created by frinder6 on 2017/2/24.
 */
@RestController
@RequestMapping("/rpc")
public class RpcController {

    @Autowired
    private IService hessianService;

    @Autowired
    private IService burlapService;

    @Autowired
    private IService httpInvokerService;

    @Autowired
    private IService rmiService;


    @RequestMapping
    public String call() {
        String message = "hello";
        hessianService.call(message);
        burlapService.call(message);
        httpInvokerService.call(message);
        rmiService.call(message);

        return message;
    }


    public static void main(String[] args) {
//        hessian();
        burlap();

    }


    public static void burlap() {
        String url = "http://localhost:8081/burlap.htm";
        BurlapProxyFactory factory = new BurlapProxyFactory();
        try {
            IService burlapService = (IService) factory.create(IService.class, url);
            burlapService.call("hi");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void hessian() {
        String url = "http://localhost:8081/hessian.htm";
        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            IService hessianService = (IService) factory.create(IService.class, url);
            hessianService.call("hello");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
