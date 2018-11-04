package business.admin.admin.service.impl;

import business.admin.admin.dao.AdminDao;
import business.admin.admin.dao.impl.AdminDaoImpl;
import business.admin.admin.service.AdminService;
import entity.Admin;

/**
 * @author chenlin
 */
public class AdminServiceImpl implements AdminService {
    static AdminDao adminDao = new AdminDaoImpl();
    @Override
    public Admin queryAdmin(String name) {
        return  adminDao.queryAdmin(name);

    }
}
