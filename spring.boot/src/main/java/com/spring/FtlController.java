package com.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by frinder6 on 2016/9/6.
 */
@Controller
@RequestMapping("ftl")
public class FtlController {


    @RequestMapping("say")
    public String say(ModelMap map){
        map.addAttribute("name", "world !");
        return "fm";
    }



}
