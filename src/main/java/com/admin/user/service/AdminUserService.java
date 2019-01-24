package com.admin.user.service;

import com.admin.user.dao.AdminUser;

public interface AdminUserService {
    /**
     * 注册用户
     * @return
     */
    boolean addUser(AdminUser user);
//    boolean updateUserPassword(AdminUser user);
    AdminUser getAdminUser(String userId, String password);
}
