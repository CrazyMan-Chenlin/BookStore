package business.front.user.dao.Impl;

import business.front.user.dao.UserDao;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.BaseDao;
import util.JdbcUitl;

import java.sql.SQLException;

public class UserDaoImpl extends BaseDao<User> implements UserDao  {
    public User query(String username) {
        try {
            QueryRunner qr = new QueryRunner(JdbcUitl.getDataSource());
            String sql = "select * from User where username=?";
            User user = (User) qr.query(sql, new BeanHandler(User.class), new Object[]{username});
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(User user) {
        super.insert(user);
    }

    @Override
    public User query(int id) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUitl.getDataSource());
        String sql = "select * from User where id=?";
        User user = (User) qr.query(sql, new BeanHandler(User.class), new Object[]{id});
        return user;
    }
}
