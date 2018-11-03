package business.front.user.service;

import entity.User;

public interface UserService {
    User query(String username);
}
