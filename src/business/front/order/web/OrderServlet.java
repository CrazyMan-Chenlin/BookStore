package business.front.order.web;

import business.front.order.dao.Impl.OrderDaoImpl;
import business.front.order.service.Impl.OrderServiceImpl;
import entity.Books;
import entity.OrderLine;
import entity.Orders;
import entity.User;
import util.BaseServlet;
import util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "order",urlPatterns = "/user/order")
public class OrderServlet extends BaseServlet {
    OrderServiceImpl orderService = new OrderServiceImpl();
    private  void toOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String consignee = request.getParameter("consignee");
        Orders order = new Orders();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        order.setUser(user);
        order.setId(WebUtil.getOrderId(user));
        order.setConsignee(consignee.trim());
        request.setAttribute("order",order);
        request.getRequestDispatcher("/pages/front/user/order/order.jsp").forward(request,response);
    }
    private  void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Orders order = WebUtil.copyRequestToBean(request, Orders.class);
        order.setState("0");
        order.setOrderDate(new Date());
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        order.setUser(user);
        order.setId(WebUtil.getOrderId(user));
        Map<String, Books> carMap = (Map<String, Books>) session.getAttribute("carMap");
        Set<Map.Entry<String, Books>> entries = carMap.entrySet();
        List<OrderLine> orderLines = new ArrayList<>();
        for (Map.Entry<String,Books> entry: entries){
            Books value = entry.getValue();
            OrderLine orderLine = new OrderLine();
            orderLine.setBook(value);
            orderLine.setCount(value.getCount());
            orderLine.setOrders(order);
            orderLine.setPrice(value.getCurrentPrice());
            orderLines.add(orderLine);
        }
        order.setOrderLineList(orderLines);
        orderService.saveOrder(order);
        request.setAttribute("order",order);
        request.getRequestDispatcher("/pages/front/user/order/ordersucc.jsp").forward(request,response);
    }
    private  void queryOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        List<Orders> orders = orderService.queryOrder(user.getId());
        request.setAttribute("orders",orders);
        request.getRequestDispatcher("/pages/front/user/order/list.jsp").forward(request,response);
    }
    private  void  queryDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Orders order = orderService.queryDetail(id);
        request.setAttribute("order",order);
        request.getRequestDispatcher("/pages/front/user/order/detail.jsp").forward(request,response);
    }
    private  void  delOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        orderService.delOrder(id);
        response.sendRedirect(request.getContextPath()+"/user/order?action=queryOrders");
    }
}
