package business.front.index.service.impl;
import business.front.index.dao.impl.IndexDaoImpl;
import business.front.index.service.IndexService;
import entity.BookTypes;
import entity.Books;

import java.util.List;

public class IndexServiceImpl implements IndexService {
    static IndexDaoImpl bookTypesDao = new IndexDaoImpl();
    @Override
    public List<BookTypes> queryAll() {

        List<BookTypes> bookTypes = bookTypesDao.queryAll();
        return bookTypes;
    }
    @Override
    public List<Books> queryBooks(int TypeId) {
        List<Books> books = bookTypesDao.queryBooks(TypeId);
        return books;
    }

    @Override
    public Books queryBook(String id) {
        Books books = bookTypesDao.queryBook(id);
        return books;
    }
}
