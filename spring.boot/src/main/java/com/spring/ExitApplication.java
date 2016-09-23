package com.spring;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

/**
 * Created by frinder6 on 2016/9/2.
 */

@Component
public class ExitApplication implements ExitCodeGenerator {

    public int getExitCode() {
        return 168;
    }
}
