package business.admin.book.service;

import entity.BookTypes;
import entity.Books;

import java.util.List;
public interface BookService {
    List<BookTypes> queryAllType();
    void insertBook(Books book);
}
