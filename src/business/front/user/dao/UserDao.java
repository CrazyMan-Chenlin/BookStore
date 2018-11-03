package business.front.user.dao;

import entity.User;

public interface UserDao {
    User query(String username);
}
