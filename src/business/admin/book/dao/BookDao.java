package business.admin.book.dao;

import entity.BookTypes;
import entity.Books;

import java.util.List;

public interface BookDao {
    List<BookTypes> queryAllType();
    void insertBook(Books book);
}
