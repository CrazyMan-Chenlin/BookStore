package business.admin.type.web;

import business.admin.type.service.Impl.TypeServiceImpl;
import entity.BookTypes;
import util.BaseServlet;
import util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/type",urlPatterns = "/admin/type")
public class Type extends BaseServlet {
    static TypeServiceImpl typeService = new TypeServiceImpl();
    private void queryTypes(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<BookTypes> bookTypes = typeService.queryAll();
        request.setAttribute("types",bookTypes);
        request.getRequestDispatcher("/pages/admin/type/list.jsp").forward(request,response);
    }
    private void delType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        typeService.delete(Integer.parseInt(id));
        response.sendRedirect(request.getContextPath()+"/admin/type?action=queryTypes");
    }
    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BookTypes bookTypes = WebUtil.copyRequestToBean(request, BookTypes.class);
        typeService.insert(bookTypes);
        response.sendRedirect(request.getContextPath()+"/pages/admin/type/addback.jsp");
    }
}
