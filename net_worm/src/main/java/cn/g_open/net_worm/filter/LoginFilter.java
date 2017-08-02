package cn.g_open.net_worm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.g_open.net_worm.domainModel.loginUser;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/a/*", initParams = {
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/ip/*,/login.html,/druid/*") })
public class LoginFilter implements Filter
{
    private String exclusions;

    @Override
    public void destroy()
    {
        exclusions = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException
    {
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession();
        if(exclusionsCheck(exclusions.split(","), httpRequest)){
            filterChain.doFilter(request, response);
        }else {
            loginUser user = (loginUser)session.getAttribute("loginUser");
            if(null != user && !user.isFailure()){
                filterChain.doFilter(request, response);
            }else {
                httpResponse.sendRedirect("/login.html");
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        exclusions = filterConfig.getInitParameter("exclusions");
    }

    private static String getClientIp(ServletRequest request)
    {
        HttpServletRequest http = (HttpServletRequest) request;
        String ip = http.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = http.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = http.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = http.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = http.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = http.getRemoteAddr();
        }
        return ip;
    }
    
    private static boolean exclusionsCheck(String[] paths, HttpServletRequest httpRequest){
        boolean flag = false;
        if(null == paths || paths.length == 0){
            return flag;
        }
        String text = httpRequest.getRequestURI();
        for (int i = 0; i < paths.length; i++)
        {
            String regex = paths[i].replace(".", "\\.").replace("*", ".*");
            if(text.matches(regex)){
                flag = true;
                break;
            }
        }
        return flag;
    }

}
