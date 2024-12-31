package org.wanho.filter;

import org.wanho.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "MyFilter", urlPatterns = {"/detail.html","/result.html","/index.html","/insertProduct.html", "/insertBanner.html"})
public class MyFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override //此方法是在请求到资源之前和之后调用的方法
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //因为要使用子类型中的方法所以需要将父类类型强转成子类类型
        HttpServletRequest ht = (HttpServletRequest) request;
        HttpServletResponse hs = (HttpServletResponse) response;
        //从请求对象中得到Session 再从Session中得到User
        User user = (User) ht.getSession().getAttribute("user");
        if (user==null){//没登录
            //如果没登录则重定向到登录页面
            hs.sendRedirect("/login.html");
        }else{//代表登录了
            chain.doFilter(request, response);//代表运行执行请求的资源
        }

    }
}
