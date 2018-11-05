package business.front.order.dao.impl;

import business.front.index.dao.impl.IndexDaoImpl;
import business.front.order.dao.OrderDao;
import entity.Books;
import entity.OrderLine;
import entity.Orders;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JdbcUitl;
import util.WebUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unchecked")
public class OrderDaoImpl  implements OrderDao {
    private QueryRunner qr=new QueryRunner();
    @Override
    public void saveOrder(Orders order) {
        Connection connection=null;
        try {
            connection= JdbcUitl.getDataSource().getConnection();
            connection.setAutoCommit(false);
            String sql = "insert into orders values(?,?,?,?,?,?,?)";
            qr.update(connection,sql,new Object[]{
                    order.getId(),order.getUser().getId(),order.getPayType(),order.getConsignee(),
                    order.getSum(),order.getState(),order.getOrderDate()
            });
            sql = "insert into orderline values(?,?,?,?,?)";
            List<OrderLine> orderLineList = order.getOrderLineList();
            for(OrderLine orderLine: orderLineList){
                Books book = orderLine.getBook();
                qr.update(connection,sql,new Object[]{
                        WebUtil.uuid(),orderLine.getOrders().getId(),
                        book.getId(),book.getCount(),book.getCurrentPrice()
                });
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
     public List<Orders> queryOrder(int userId){
        Connection connection=null;
        try {
              connection= JdbcUitl.getDataSource().getConnection();
             String sql = "select * from orders where userid=?";
            return  (List<Orders>)qr.query(connection,sql,new BeanListHandler(Orders.class),new Object[]{userId});
         } catch (SQLException e) {
             e.printStackTrace();
             throw  new RuntimeException(e);
         }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     }
     @Override
     public Orders queryDetail(String id) {
         Connection connection = null;
         try {
             connection = JdbcUitl.getDataSource().getConnection();
             connection.setAutoCommit(false);
             String sql = "select * from orders where id=?";
             Orders order = (Orders) qr.query(connection, sql, new BeanHandler(Orders.class), new Object[]{id});
             sql = "select * from orderline where orderid=?";
             List<OrderLine> orderLineList = (List<OrderLine>) qr.query(connection, sql, new MyOrderLineHadnler(), new Object[]{id});
             order.setOrderLineList(orderLineList);
             connection.commit();
             return order;
         } catch (SQLException e) {
             e.printStackTrace();
             try {
                 connection.rollback();
             } catch (SQLException e1) {
                 e1.printStackTrace();
             }
             throw new RuntimeException(e);
         }finally {
             try {
                 connection.close();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
     }
@Override
    public void delOrder (String id) {
    Connection connection=null;
        try {
             connection= JdbcUitl.getDataSource().getConnection();
            String sql = "delete from orders where id=?";
            qr.update(connection,sql,new Object[]{id});

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
class MyOrderLineHadnler implements ResultSetHandler {
    IndexDaoImpl indexDao = new IndexDaoImpl();
    @Override
    public List<OrderLine> handle(ResultSet rs) throws SQLException {
        List<OrderLine>  orderLineList = new ArrayList<>();
        while (rs.next()){
            OrderLine orderLine = new OrderLine();
            orderLine.setId(rs.getString("id"));
            orderLine.setPrice(rs.getDouble("price"));
            orderLine.setOrders(null);
            orderLine.setCount(rs.getInt("amt"));
            Books book = indexDao.queryBook(rs.getString("bookid"));
            orderLine.setBook(book);
            orderLineList.add(orderLine);
        }
        return orderLineList;
    }
}