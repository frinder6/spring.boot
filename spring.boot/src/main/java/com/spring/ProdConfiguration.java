package com.spring;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Created by frinder6 on 2016/9/2.
 */
@Component
@Profile("prod")
public class ProdConfiguration {

    public ProdConfiguration() {
        System.out.println("prod................");
    }
}
