package com.rpc.service;

import org.springframework.stereotype.Service;

/**
 * Created by frinder6 on 2017/2/24.
 */
@Service
public class RmiServiceImpl implements IService {

    @Override
    public void call(String message) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("rmi : " + message);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&");
    }

}
