package business.front.order.service;

import entity.Orders;

import java.util.List;

public interface OrderService {
    void saveOrder(Orders order);
    List<Orders> queryOrder(int userId);
    Orders queryDetail(String id);
    void delOrder (String id);
}
