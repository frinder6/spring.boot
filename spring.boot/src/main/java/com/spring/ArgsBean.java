package com.spring;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created on 2016/9/2.
 */
@Component
public class ArgsBean {

    @Autowired
    public ArgsBean(ApplicationArguments args) {
        boolean debug = args.containsOption("debug");
        List<String> files = args.getNonOptionArgs();
        System.out.println(debug + "  " + JSON.toJSONString(files));
    }
}
