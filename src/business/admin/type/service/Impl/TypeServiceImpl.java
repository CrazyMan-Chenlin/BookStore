package business.admin.type.service.Impl;

import business.admin.type.dao.Impl.TypeDaoImpl;
import business.admin.type.service.TypeServcie;
import entity.BookTypes;

import java.sql.SQLException;
import java.util.List;

public class TypeServiceImpl implements TypeServcie {
     static  TypeDaoImpl typeDao = new TypeDaoImpl();
    @Override
    public List<BookTypes> queryAll() {
        try {
            return  typeDao.queryAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        typeDao.delete(id);
    }

    @Override
    public void insert(BookTypes bookTypes) {
        typeDao.insert(bookTypes);
    }
}
