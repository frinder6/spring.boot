package com.spring;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created on 2016/9/2.
 */
@Component
@Profile("dev")
public class DevConfiguration {

    public DevConfiguration() {
        System.out.println("dev....................");
    }
}
