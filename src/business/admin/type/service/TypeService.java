package business.admin.type.service;

import entity.BookTypes;

import java.util.List;

public interface TypeService {
    List<BookTypes> queryAll();
    void delete(int id);
    void insert(BookTypes bookTypes);
}
