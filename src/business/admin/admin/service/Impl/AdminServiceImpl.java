package business.admin.admin.service.Impl;

import business.admin.admin.dao.Impl.AdminDaoImpl;
import business.admin.admin.service.AdminService;
import entity.Admin;

public class AdminServiceImpl implements AdminService {
    static AdminDaoImpl adminDao = new AdminDaoImpl();
    @Override
    public Admin queryAdmin(String name) {
        return  adminDao.queryAdmin(name);

    }
}
