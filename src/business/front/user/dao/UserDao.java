package business.front.user.dao;

import entity.User;

/**
 * @author chenlin
 */
public interface UserDao {
    User query(String username);
    void insert(User user);
}
