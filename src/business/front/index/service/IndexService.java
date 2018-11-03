package business.front.index.service;

import entity.BookTypes;
import entity.Books;

import java.util.List;

public interface IndexService {
    List<BookTypes> queryAll();
    List<Books> queryBooks(int TypeId);
    Books queryBook(String id);
}
