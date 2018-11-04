package business.admin.type.service.impl;

import business.admin.type.dao.TypeDao;
import business.admin.type.dao.impl.TypeDaoImpl;
import business.admin.type.service.TypeService;
import entity.BookTypes;

import java.util.List;

/**
 * @author chenlin
 */
public class TypeServiceImpl implements TypeService {
     private TypeDao typeDao = new TypeDaoImpl();
    @Override
    public List<BookTypes> queryAll() {
            return  typeDao.queryAll();
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
