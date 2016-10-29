package com.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2016/9/6.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        System.out.println("test...................");
        return "index";
    }

}
