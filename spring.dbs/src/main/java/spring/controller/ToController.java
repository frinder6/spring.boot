package spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by frinder6 on 2016/12/20.
 */
@Controller
public class ToController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToController.class);

    @RequestMapping("/")
    public String index() {
        LOGGER.info("..........................");
        return "index";
    }

}
