package business.front.index.web;


import business.front.index.dao.impl.IndexDaoImpl;
import business.front.index.service.IndexService;
import business.front.index.service.impl.IndexServiceImpl;
import entity.BookTypes;
import entity.Books;
import util.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author chenlin
 */
@WebServlet(name = "index",urlPatterns = "/index")
public class index extends BaseServlet  {
   private IndexService indexService =new IndexServiceImpl();
    private void queryAllTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<BookTypes> bookTypes = indexService.queryAll();
        request.setAttribute("bookTypes",bookTypes);
        request.getRequestDispatcher("/pages/front/index/shopIndex.jsp").forward(request,response);
    }
    private  void queryBooks(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Books> books=null;
        if (id!=null&&!id.equals("")){
            books = indexService.queryBooks(Integer.parseInt(id));
        }else{
             books =indexService.queryBooks(0);
        }
        request.setAttribute("books",books);
        request.getRequestDispatcher("/pages/front/book/list.jsp").forward(request,response);
    }
    private  void queryBook(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        Books book = indexService.queryBook(id);
        request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/front/book/detail.jsp").forward(request,response);
    }
}
