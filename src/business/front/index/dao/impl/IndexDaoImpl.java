package business.front.index.dao.impl;
import business.front.index.dao.IndexDao;
import entity.BookTypes;
import entity.Books;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.JdbcUitl;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;
/**
 * @author chenlin
 */
@SuppressWarnings("unchecked")
public class IndexDaoImpl implements IndexDao {
   private  QueryRunner qr=new QueryRunner(JdbcUitl.getDataSource());
    @Override
    public List<BookTypes> queryAll() {
        try {
            String sql = "select  * from types";
            List<BookTypes> query = (List<BookTypes>) qr.query(sql, new BeanListHandler(BookTypes.class));
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
     @Override
     /*通过子查询实现功能*/
    public List<Books> queryBooks(int TypeId) {
        List<Books> booksList = null;
        try {
            StringBuffer sb =new StringBuffer("select id,name,price,round(price*rebate,2)as currentPrice,img,rebate,stock,publisher,publishdate,author from books ");
            if (TypeId!=0){
                sb.append("where id in (select bookId from bookType where TypeId="+TypeId+")");
            }
            booksList = (List<Books>) qr.query(sb.toString(), new BeanListHandler(Books.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksList;
    }
    //通过id查询图书
    @Override
    public Books queryBook(String id) {
        try {
            String sql="select id,name,price,round(price*rebate,2)as currentPrice,img,rebate," +
                    "stock,publisher,publishdate,author from books where id=?";
            Books query = (Books) qr.query(sql, new BeanHandler(Books.class), new Object[]{id});
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
