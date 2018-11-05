package business.front.user.service;

import entity.User;
import exception.NameExitException;

/**
 * @author chenlin
 */
public interface UserService {
    User query(String username);
    void insert(User user) throws NameExitException;
}
