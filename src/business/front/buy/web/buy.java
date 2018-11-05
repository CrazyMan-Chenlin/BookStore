package business.front.buy.web;

import entity.Books;
import util.BaseServlet;
import util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * @author chenlin
 */
@SuppressWarnings("unchecked")
@WebServlet(name = "buy",urlPatterns = "/user/buy")
public class buy extends BaseServlet {
    private  void addToCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Books books = WebUtil.copyRequestToBean(request, Books.class);
        HttpSession session = request.getSession(false);
        Map<String, Books> carMap = (Map<String, Books>) session.getAttribute("carMap");
        if (carMap==null){
            carMap=new HashMap<>();
        }
        if(carMap.containsKey(books.getId())){
            books.setCount(carMap.get(books.getId()).getCount()+1);
        }else{
            books.setCount(1);
        }
        carMap.put(books.getId(),books);
        session.setAttribute("carMap",carMap);
        response.sendRedirect(request.getContextPath()+"/pages/front/user/buy/car.jsp");
    }
    private  void modifyCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String num = request.getParameter("num");
        String id =request.getParameter("id");
        HttpSession session = request.getSession(false);
        Map<String, Books> carMap = (Map<String, Books>) session.getAttribute("carMap");
        Books books = carMap.get(id);
        if (Integer.parseInt(num)==-1){
            if (books.getCount()==1){
                carMap.remove(books.getId());
            }else{
                books.setCount(books.getCount()+Integer.parseInt(num));
                carMap.put(books.getId(),books);
            }
        }else{
            books.setCount(books.getCount()+Integer.parseInt(num));
            carMap.put(books.getId(),books);
        }
        response.sendRedirect(request.getContextPath()+"/pages/front/user/buy/car.jsp");
    }
    private  void cleanAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        session.setAttribute("carMap",null);
        response.sendRedirect(request.getContextPath()+"/pages/front/user/buy/car.jsp");
    }
}
