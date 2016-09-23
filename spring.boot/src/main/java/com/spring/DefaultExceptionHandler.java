package com.spring;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by frinder6 on 2016/9/12.
 */

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Map<String, Object> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        Map<String, Object> error = new HashMap<String, Object>();
        error.put("Exception", e);
        error.put("code", 0000);
        error.put("url", req.getRequestURL().toString());
        return error;
    }

}
