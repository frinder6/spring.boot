package spring.contoller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2016/9/16.
 */
@ControllerAdvice
public class MyExceptionHandler {

    // 当抛出 Exception 时，使用的响应处理器
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.addObject("url", req.getRequestURL());
        mv.setViewName("error");
        return mv;
    }



    // 当抛出 MyException时，使用的响应处理器
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Map<String, Object> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        Map<String, Object> error = new HashMap<String, Object>();
        error.put("Exception", e);
        error.put("code", 0000);
        error.put("url", req.getRequestURL().toString());
        return error;
    }


//    @ExceptionHandler(value = MyException.class)
//    @ResponseBody
//    public Error<Object> jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//        Error<Object> error = new Error<Object>();
//        error.setData(e);
//        error.setCode(0000);
//        error.setUrl(req.getRequestURL().toString());
//        error.setMessage(e.getMessage());
//        return error;
//    }


}
