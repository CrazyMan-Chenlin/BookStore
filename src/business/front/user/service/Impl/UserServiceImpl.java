package business.front.user.service.Impl;

import business.front.user.dao.Impl.UserDaoImpl;
import business.front.user.service.UserService;
import entity.User;
import exception.NameExitException;

public class UserServiceImpl implements UserService {
    private  static  UserDaoImpl userDao = new UserDaoImpl();
    @Override
    public User query(String username) {
            User user = userDao.query(username);
           return user;
    }
    public void insert(User user) throws NameExitException {
        if (query(user.getUsername())==null){
            userDao.insert(user);
        }else{
            throw new NameExitException("用户名已存在！");
        }
    }
}
