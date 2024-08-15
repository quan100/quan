package cn.javaquan.code.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 *
 * @author chenshun/sunlightcs@gmail.com
 * @since 1.0.0
 */
@Slf4j
@Component
public class RRExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception exception) {
        R r = new R();
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");

            if (exception instanceof RRException) {
                r.put("code", ((RRException) exception).getCode());
                r.put("msg", ((RRException) exception).getMessage());
            }
            else if (exception instanceof DuplicateKeyException) {
                r = R.error("数据库中已存在该记录");
            }
            else {
                r = R.error();
            }

            // 记录异常日志
            log.error(exception.getMessage(), exception);

            String json = JSON.toJSONString(r);
            response.getWriter().print(json);
        }
        catch (Exception ex) {
            log.error("RRExceptionHandler 异常处理失败", ex);
        }
        return new ModelAndView();
    }

}
