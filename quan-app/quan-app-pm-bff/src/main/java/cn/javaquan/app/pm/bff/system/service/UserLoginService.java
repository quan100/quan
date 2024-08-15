package cn.javaquan.app.pm.bff.system.service;

import cn.javaquan.tools.captcha.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录业务实现.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class UserLoginService {

    private final CaptchaService captchaService;

    /**
     * 获取验证码.
     * @param request http servlet请求信息
     * @param response http servlet响应信息
     */
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getSession().getId();
        setResponseHeaders(response, HttpStatus.ACCEPTED);
        try {
            captchaService.createImage(sessionId, response.getOutputStream());
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 设置http头.
     * @param response http servlet响应信息
     * @param httpStatus http状态码
     */
    protected void setResponseHeaders(HttpServletResponse response, HttpStatus httpStatus) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
        response.setStatus(httpStatus.value());
    }

}
