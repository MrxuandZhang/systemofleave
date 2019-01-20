package com.hongzan.base;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理器
 */
@Component
public class MyExceptionHandler implements HandlerExceptionResolver {
    //设置日志
    private static Logger log = Logger.getLogger(MyExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView mv = new ModelAndView("error");
        if(e instanceof UserException){//自定义的异常
            log.error(e.getMessage(),e);
            mv.addObject("error",e.getMessage());
        }else{//非自定义的异常
            log.error(e.getMessage(),e);
            UserException ue = new UserException(e);
            mv.addObject("error",ue.getMessage());
        }
        return mv;
    }
}
