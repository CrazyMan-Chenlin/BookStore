package business.admin.admin.dao;

import entity.Admin;
import util.BaseDao;

public interface AdminDao  {
    Admin queryAdmin(String name);
}
