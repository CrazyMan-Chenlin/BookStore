package business.admin.admin.dao.Impl;

import business.admin.admin.dao.AdminDao;
import entity.Admin;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import util.JdbcUitl;

import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin queryAdmin(String name) {
        try {
            QueryRunner qr = new QueryRunner(JdbcUitl.getDataSource());
            String sql = "select * from admin where name=?";
            Admin admin = (Admin) qr.query(sql, new BeanHandler(Admin.class), new Object[]{name});
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
