/*
信息:
*/
package yd.itcast.core.filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import taxServices.user.entity.User;
import yd.itcast.core.constant.Constant;
import yd.itcast.core.utils.PermissionCheck;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        String uri=request.getRequestURI();
        if (!uri.contains("/sys/login_")){
            if (request.getSession().getAttribute(Constant.USER)!=null){
               if (uri.contains("taxService")){
                   User user=(User)request.getSession().getAttribute(Constant.USER);
//                   获取容器中的鉴定类
                   WebApplicationContext webApplicationContext = WebApplicationContextUtils.findWebApplicationContext(request.getSession().getServletContext());
                   PermissionCheck permissionCheck=(PermissionCheck) webApplicationContext.getBean("permissionCheck");
                   if (permissionCheck.isAccess(user,"taxService")){
                        filterChain.doFilter(request,response);
                   }else {
                       response.sendRedirect(request.getContextPath()+"/sys/login_noPermissionUI.action");
                   }

               }else {
                   filterChain.doFilter(request,response);
               }

            }else {
                response.sendRedirect(request.getContextPath()+"/sys/login_toLoginUI.action");
            }
        }else {
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
