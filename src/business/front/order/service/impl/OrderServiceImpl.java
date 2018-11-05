package business.front.order.service.impl;
import business.front.order.dao.OrderDao;
import business.front.order.dao.impl.OrderDaoImpl;
import business.front.order.service.OrderService;
import entity.Orders;
import java.util.List;
/**
 * @author chenlin
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    @Override
    public void saveOrder(Orders order) {
        orderDao.saveOrder(order);
    }

    @Override
    public List<Orders> queryOrder(int userId) {
        return orderDao.queryOrder(userId);
    }

    @Override
    public Orders queryDetail(String id) {
       return orderDao.queryDetail(id);
    }

    @Override
    public void delOrder(String id) {
      orderDao.delOrder(id);
    }


}
