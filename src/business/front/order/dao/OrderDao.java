package business.front.order.dao;

import entity.Orders;

import java.util.List;

/**
 * @author chenlin
 */
public interface OrderDao  {
      void saveOrder(Orders order);
      List<Orders> queryOrder(int userId);
      Orders queryDetail(String id);
      void delOrder (String id);
}
