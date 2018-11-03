package business.front.user.web;

import business.front.user.service.Impl.UserServiceImpl;
import entity.User;
import exception.NameExitException;
import util.BaseServlet;
import util.MD5Util;
import util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Register",urlPatterns = "/Register")
public class register extends BaseServlet {
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String newCode = (String) session.getAttribute("newCode");
        String sCode = request.getParameter("sCode");
        if (newCode.equals(sCode)){
            User user = WebUtil.copyRequestToBean(request, User.class);
            user.setPassword(MD5Util.md5(user.getPassword()));
            UserServiceImpl userService = new UserServiceImpl();
            try {
                userService.insert(user);
                response.sendRedirect(request.getContextPath()+"/pages/front/login/login.jsp");
            } catch (NameExitException e) {
                request.setAttribute("NameError",e.getMessage());
                request.getRequestDispatcher("/pages/front/login/reg.jsp").forward(request,response);
            }
        }else {
            request.setAttribute("CodeError","验证码错误！");
            request.getRequestDispatcher("/pages/front/login/reg.jsp").forward(request,response);
        }
        }

}
