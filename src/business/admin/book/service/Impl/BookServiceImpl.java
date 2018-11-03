package business.admin.book.service.Impl;

import business.admin.book.dao.Impl.BookDaoImpl;
import business.admin.book.service.BookService;
import entity.BookTypes;
import entity.Books;

import java.util.List;

public class BookServiceImpl implements BookService{
   static BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    public List<BookTypes> queryAllType() {
        return  bookDao.queryAllType();
    }

    @Override
    public void InsertBook(Books book) {
        bookDao.InsertBook(book);
    }
}
