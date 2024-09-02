package cjy.aigis.javagis.interceptor;

import cjy.aigis.javagis.util.JwtUtils;
import cjy.aigis.javagis.util.Result;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author cjy
 * @description 拦截器
 * @date 2023/08/03 16:01:01
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    // 请求处理之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("请求URI: {}", request.getRequestURI());

        String token = request.getHeader("token");
        if (!StringUtils.hasLength(token)) {
            log.info("未登录");
            Result<?> res = Result.noLogin();
            String json = JSONObject.toJSONString(res);
            // 设置响应类型为JSON, 设置响应编码为UTF-8,防止中文乱码
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(json);
            return false;
        }

        try {
            // 解析JWT令牌
//            JwtUtils.parseJWT(token);
            log.info("解析JWT令牌成功");
        } catch (Exception e) {
            log.info("解析JWT令牌失败");
            Result<?> res = Result.tokenInvalid();
            String json = JSONObject.toJSONString(res);
            // 设置响应类型为JSON, 设置响应编码为UTF-8,防止中文乱码
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(json);
            return false;
        }

        return true; // 返回true表示继续流程；如果返回false，则中断后续操作。
    }

    // 请求处理之后，视图解析之前调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("拦截器处理后");
    }

    // 当整个请求完成之后被调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("拦截器完成");
    }
}
