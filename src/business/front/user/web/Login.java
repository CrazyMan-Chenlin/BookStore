package business.front.user.web;

import business.front.user.service.UserService;
import business.front.user.service.impl.UserServiceImpl;
import entity.User;
import util.BaseServlet;
import util.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "Login",urlPatterns = "/Login")
public class Login extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        String newCode = (String) session.getAttribute("newCode");
        String validateCode = request.getParameter("code");
        String username,password;
        if(newCode.equals(validateCode)){
            username = request.getParameter("username");
            password = request.getParameter("password");
            User user = userService.query(username);
            if (user!=null){
                if (MD5Util.md5(password).equals(user.getPassword())){
                    session.setAttribute("user",user);
                    response.sendRedirect(request.getContextPath()+"/index?action=queryAllTypes");
                }else{
                    request.setAttribute("PasswordError","密码错误！");
                    request.getRequestDispatcher("/pages/front/login/login.jsp").forward(request,response);
                }
            }else{
                request.setAttribute("NameError","没有此用户");
                request.getRequestDispatcher("/pages/front/login/login.jsp").forward(request,response);
            }
        }else{
            session.removeAttribute("newCode");
            request.setAttribute("CodeError","验证码错误！");
            request.getRequestDispatcher("/pages/front/login/login.jsp").forward(request,response);
        }
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        session.invalidate();
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }
}
