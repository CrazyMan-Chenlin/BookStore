package business.front.index.service.impl;
import business.front.index.dao.IndexDao;
import business.front.index.dao.impl.IndexDaoImpl;
import business.front.index.service.IndexService;
import entity.BookTypes;
import entity.Books;

import java.util.List;

/**
 * @author chenlin
 */
public class IndexServiceImpl implements IndexService {
    private IndexDao bookTypesDao = new IndexDaoImpl();
    @Override
    public List<BookTypes> queryAll() {

        List<BookTypes> bookTypes = bookTypesDao.queryAll();
        return bookTypes;
    }
    @Override
    public List<Books> queryBooks(int typeId) {
        List<Books> books = bookTypesDao.queryBooks(typeId);
        return books;
    }

    @Override
    public Books queryBook(String id) {
        Books books = bookTypesDao.queryBook(id);
        return books;
    }
}
