package business.front.address.web;
import business.front.address.service.AddressService;
import business.front.address.service.impl.AddressServiceImpl;
import entity.Address;
import entity.User;
import exception.MaxAddressException;
import util.BaseServlet;
import util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author chenlin
 */
@WebServlet(name = "address",urlPatterns = "/user/address")
public class AddressServlet extends BaseServlet {
   private AddressService addressService = new AddressServiceImpl();
    private  void addAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MaxAddressException {
        Address address = WebUtil.copyRequestToBean(request, Address.class);
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        address.setId(WebUtil.uuid());
        address.setMkTime(new Date());
        address.setName(user.getUsername());
        address.setUser(user);
        address.setDft(0);
        addressService.insert(address);
       response.sendRedirect(request.getContextPath()+"/pages/front/user/address/addback.jsp");
    }
    private  void queryAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        List<Address> addressList = addressService.queryAddress(user.getId());
        request.setAttribute("addressList",addressList);
        request.getRequestDispatcher("/pages/front/user/address/list.jsp").forward(request,response);
    }
    private  void deleteAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        addressService.delete(id);
        response.sendRedirect(request.getContextPath()+"/user/address?action=queryAddress");
    }
    private void changeToDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        addressService.updateDft(id);
        response.sendRedirect(request.getContextPath()+"/user/address?action=queryAddress");
    }
}