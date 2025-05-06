package org.example.tliasweb.filter;

/* 
    @author nanchao 
    @date 2025/4/24
*/

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.example.tliasweb.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 令牌校验过滤器
 */

//@WebFilter(urlPatterns = "/*")//配置过滤器要拦截的请求路径（ /* 表示拦截浏览器的所有请求 ）
public class TokenFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(TokenFilter.class);


    //拦截到请求时,调用该方法,可以调用多次
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //1. 获取请求url。
        String url = request.getRequestURL().toString();

        //2. 判断请求url中是否包含login，如果包含，说明是登录操作，放行。
        if (url.contains("login")) { //登录请求
            log.info("登录请求 , 直接放行");
            chain.doFilter(request, response);//放行
            return;
        }

        //3. 获取请求头中的令牌（token）。
        String jwt = request.getHeader("token");

        //4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if (jwt == null || jwt.isEmpty()) { //jwt为空
            log.info("获取到jwt令牌为空, 响应401");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);//401状态码
            return;
        }

        //5. 解析token，如果解析失败，返回错误结果（未登录）。
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败, 返回401");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }

        //6. 放行。
        log.info("令牌合法, 放行");
        chain.doFilter(request, response);//放行
    }

}
