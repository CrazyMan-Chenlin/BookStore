package business.admin.type.dao.Impl;

import entity.BookTypes;
import util.BaseDao;

import java.sql.SQLException;
import java.util.List;

public class TypeDaoImpl extends BaseDao<BookTypes> {
    @Override
    public List<BookTypes> queryAll() throws SQLException {
        return super.queryAll();
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
