package business.front.user.service.impl;

import business.front.user.dao.UserDao;
import business.front.user.dao.impl.UserDaoImpl;
import business.front.user.service.UserService;
import entity.User;
import exception.NameExitException;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User query(String username) {
            User user = userDao.query(username);
           return user;
    }
    @Override
    public void insert(User user) throws NameExitException {
        if (query(user.getUsername())==null){
            userDao.insert(user);
        }else{
            throw new NameExitException("用户名已存在！");
        }
    }
}
