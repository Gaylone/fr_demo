package top.sonjong.system.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        ModelAndView mv = new ModelAndView();
        // 判断不同异常类型，做不同视图跳转
        if (ex instanceof ArithmeticException) {
            mv.setViewName("errorAlert");
        }
        if (ex instanceof NullPointerException) {
            mv.setViewName("errorAlert");
        }
        mv.addObject("errorMessage", ex.toString());
        logger.error("系统捕捉到异常：{}", ex.toString());
        return mv;
    }
}
