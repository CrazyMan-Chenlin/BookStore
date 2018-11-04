package business.admin.book.service.impl;

import business.admin.book.dao.BookDao;
import business.admin.book.dao.impl.BookDaoImpl;
import business.admin.book.service.BookService;
import entity.BookTypes;
import entity.Books;

import java.util.List;

/**
 * @author chenlin
 */
public class BookServiceImpl implements BookService{
    BookDao bookDao = new BookDaoImpl();
    @Override
    public List<BookTypes> queryAllType() {
        return  bookDao.queryAllType();
    }

    @Override
    public void insertBook(Books book) {
        bookDao.insertBook(book);
    }
}
