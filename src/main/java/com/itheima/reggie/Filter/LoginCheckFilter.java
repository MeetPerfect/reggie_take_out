package com.itheima.reggie.Filter;

/**
 * ClassName: LoginCheckFilter
 * Package: com.itheima.reggie.Filter
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/8 17:48
 * @Version 1.0
 */

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.Common.BaseContext;
import com.itheima.reggie.Common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否完成登录
 */
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    // 路径匹配器，支持通配符
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1.获取本次请求的url
        String requestURL = request.getRequestURI();
        log.info("拦截请求：{}", requestURL);
        // 定义无需处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/login",
                "/user/sendMsg"
        };

        // 2.判断本次请求是否需要处理
        boolean check = check(urls, requestURL);

        // 3.如果无需处理，放行
        if (check) {
            log.info("本次请求{}无需处理，放行", requestURL);
            filterChain.doFilter(request, response);
            return;
        }

        // 4.1判断登录状态，若以登录，则放行
        if (request.getSession().getAttribute("employee") != null) {
            log.info("用户已登录,用户id为{}", request.getSession().getAttribute("employee"));
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request, response);
            return;
        }

        // 4.2判断移动端登录状态，若以登录，则放行
        if (request.getSession().getAttribute("user") != null) {
            log.info("用户已登录,用户id为{}", request.getSession().getAttribute("user"));
            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request, response);
            return;
        }

        // 5未登录，则返回未登录结果
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    /**
     * 路径匹配，判断本次请求是否放行
     *
     * @param urls
     * @param requestURL
     * @return
     */
    public boolean check(String[] urls, String requestURL) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURL);
            if (match) {
                return true;
            }
        }
        return false;
    }

}
