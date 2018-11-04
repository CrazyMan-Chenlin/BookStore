package business.admin.type.dao.impl;

import business.admin.type.dao.TypeDao;
import entity.BookTypes;
import util.BaseDao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author chenlin
 */
public class TypeDaoImpl extends BaseDao<BookTypes> implements TypeDao{
    @Override
    public List<BookTypes> queryAll()  {
        try {
            return super.queryAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void insert(BookTypes bookTypes) {
        super.insert(bookTypes);
    }
}
