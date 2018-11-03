package filter;

import entity.Admin;
import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter",urlPatterns = {"/admin/*","/pages/admin/*"})
public class AdminFilter implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //判断Session有没有存在
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        HttpSession session=request.getSession(false);
        if (session==null){
            response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
            return;
        }else{
            Admin admin = (Admin) session.getAttribute("admin");
            if (admin==null){
                response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
                return;
            }
        }
        //如果登录成功，放行
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
