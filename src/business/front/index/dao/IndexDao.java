package business.front.index.dao;

import entity.BookTypes;
import entity.Books;

import java.util.List;

public interface IndexDao {
    List<BookTypes> queryAll();
    List<Books> queryBooks(int TypeId);
    Books queryBook(String id);
}
