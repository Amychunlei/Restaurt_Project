package com.pro.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.pro.reggie.common.BaseContext;
import com.pro.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author chunleiAmy
 * @Date 8/31/2022 6:37 PM
 * @Description 检查用户是否已经完成登陆，urlPatterns拦截的请求，这里是拦截所有的请求
 * @Since version-1.0
 */

@WebFilter(filterName = "LongCheckFilter",urlPatterns = "/*")
@Slf4j
@Component
//Component 如果不加就拦截不了
public class LongCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //对请求和响应进行强转,我们需要的是带http
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1、获取本次请求的URI
        String requestURI = request.getRequestURI();
        //定义不需要处理的请求路径  比如静态资源(静态页面我们不需要拦截,因为此时的静态页面是没有数据的)
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg", //移动端发送短信
                "/user/login" //移动端登录
        };


        //做调试用的
        log.info("拦截到请求：{}",requestURI);

        //2、判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //3、如果不需要处理，则直接放行
        if(check){
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        //4-1判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("employee") != null){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("employee"));
//            long id = Thread.currentThread().getId();
//            log.info("线程id为:{}",id);
            long empId = (long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
             return;
        }
        log.info("用户未登录");

        //4-2判断登录状态，如果已登录，则直接放行"
        if(request.getSession().getAttribute("user") != null){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("user"));
//            long id = Thread.currentThread().getId();
//            log.info("线程id为:{}",id);
            long empId = (long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }
        log.info("用户未登录");
        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据,具体响应什么数据，看前端的需求，然后前端会根据登陆状态做页面跳转
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }


    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls
     * @param requestURI
     * @return
     */


    private boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            //把浏览器发过来的请求和我们定义的不拦截的url做比较，匹配则放行
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }


}
