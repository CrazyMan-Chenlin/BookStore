package business.admin.admin.web;

import business.admin.admin.service.AdminService;
import business.admin.admin.service.impl.AdminServiceImpl;
import entity.Admin;
import util.BaseServlet;
import util.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * @author chenlin
 */
@WebServlet(name = "/admin",urlPatterns = "/adminLogin")
public class AdminServlet extends BaseServlet {
    private AdminService adminService = new AdminServiceImpl();
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        Admin admin =adminService.queryAdmin(name);
        if (admin!=null){
            String password = request.getParameter("password");
            if (MD5Util.md5(password).equals(admin.getPassword())){
                HttpSession session = request.getSession(true);
                session.setAttribute("admin",admin);
                response.sendRedirect(request.getContextPath()+"/pages/admin/index.jsp");
            }else {
                request.setAttribute("msg","密码错误");
                request.getRequestDispatcher("/pages/login.jsp").forward(request,response);
            }
        }else{
                request.setAttribute("msg","没有此管理员");
                request.getRequestDispatcher("/pages/login.jsp").forward(request,response);
        }
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        session.removeAttribute("admin");
        session.invalidate();
        response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
    }
}
