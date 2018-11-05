package business.front.index.service;

import entity.BookTypes;
import entity.Books;

import java.util.List;

/**
 * @author chenlin
 */
public interface IndexService {
    List<BookTypes> queryAll();
    List<Books> queryBooks(int typeId);
    Books queryBook(String id);
}
