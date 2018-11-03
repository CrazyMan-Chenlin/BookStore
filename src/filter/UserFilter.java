package filter;

import entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//String 字符串，所以使用以下格式{"",""}
@WebFilter(filterName = "UserFilter",urlPatterns = {"/user/*","/pages/front/user/*"})
public class UserFilter implements Filter {
    public void destroy() {
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //判断Session有没有存在
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;
        HttpSession session=request.getSession(false);
        if (session==null){
            response.sendRedirect(request.getContextPath()+"/pages/front/login/login.jsp");
            return;
        }else{
            User user = (User) session.getAttribute("user");
            if (user==null){
                response.sendRedirect(request.getContextPath()+"/pages/front/login/login.jsp");
                return;
            }
        }
        //如果登录成功，放行
        chain.doFilter(req, resp);

    }


    public void init(FilterConfig config) throws ServletException {

    }

}
