package business.admin.type.dao;

import entity.BookTypes;

import java.util.List;

/**
 * @author chenlin
 */
public interface TypeDao {
    List<BookTypes> queryAll();
    void delete(int id);
    void insert(BookTypes bookTypes);
}
