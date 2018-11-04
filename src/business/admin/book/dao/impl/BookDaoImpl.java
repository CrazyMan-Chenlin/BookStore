package business.admin.book.dao.impl;

import business.admin.book.dao.BookDao;
import entity.BookTypes;
import entity.Books;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JdbcUitl;
import util.WebUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author chenlin
 */
public class BookDaoImpl implements BookDao {

    @Override
    public List<BookTypes> queryAllType() {
        try {
            QueryRunner qr = new QueryRunner(JdbcUitl.getDataSource());
            String sql = "select * from types";
            return  (List<BookTypes>)qr.query(sql,new BeanListHandler(BookTypes.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public void insertBook(Books book){
        QueryRunner qr = new QueryRunner();
        Connection connection=null;
        try {
            connection= JdbcUitl.getDataSource().getConnection();
            connection.setAutoCommit(false);
            String sql = "insert into books (id,name,price,img,author,rebate," +
                    "publisher,stock) value (?,?,?,?,?,?,?,?)";
            book.setId(WebUtil.uuid());
            qr.update(connection,sql,new Object[]{
                    book.getId(),book.getName(),book.getPrice(),book.getImg(),
                    book.getAuthor(),book.getRebate(),book.getPublisher(),book.getStock()
            });
            List<Integer> typeIds = book.getTypeId();
            for (Integer typeId:typeIds){
                sql = "insert into booktype values (?,?)";
                qr.update(connection,sql,new Object[]{book.getId(),typeId});
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
}
