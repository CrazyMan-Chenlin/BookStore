package business.front.address.dao.Impl;

import business.front.user.dao.Impl.UserDaoImpl;

import entity.Address;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import util.JdbcUitl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("unchecked")
public class AddressDaoImpl  {
    static QueryRunner qr=new QueryRunner(JdbcUitl.getDataSource());
    public void insert(Address address) {
        try {
            String sql="insert into address values(?,?,?,?,?,?,?,?)";
            qr.update(sql,new Object[]{address.getId(),address.getName(),address.getPhone(),address.getZip(),
            address.getDft(),address.getUser().getId(),address.getMkTime(),address.getAddress()});
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public List<Address> queryAddress(int userId)  {
        try {
            String sql="select * from address where userId=? ORDER BY mktime DESC";
            List<Address> query = (List<Address>) qr.query(sql, new MyAddressHandler(), new Object[]{userId});
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public void delete(String id) {
        try {
            String sql="delete from address where id=?";
            qr.update(sql,new Object[]{id});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateDft(String id){
        Connection connection=null;
        try {
            connection = JdbcUitl.getDataSource().getConnection();
            connection.setAutoCommit(false);
            String sql = "update address as a, (select userid from address where id in (?))" +
                    "as b set dft = 0 where a.userid=b.userid;";
            qr.update(sql,new Object[]{id});
            sql="update address set dft = 1 where id=?";
            qr.update(sql,new Object[]{id});
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    }
 class MyAddressHandler implements ResultSetHandler {
     @Override
     public Object handle(ResultSet rs) throws SQLException {
         UserDaoImpl userDao=new UserDaoImpl();
         List<Address> addressList=new ArrayList<>();
         while (rs.next()){
             Address address =new Address();
             address.setId(rs.getString("id"));
             address.setName(rs.getString("name"));
             address.setMkTime(rs.getDate("mktime"));
             address.setDft(rs.getInt("dft"));
             address.setPhone(rs.getString("phone"));
             address.setAddress(rs.getString("address"));
             address.setZip(rs.getInt("zip"));
             User user = userDao.query(rs.getInt("userid"));
             address.setUser(user);
             addressList.add(address);
         }
         return addressList;
     }
 }
