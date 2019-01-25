package com.admin.user.service;

import com.admin.user.ResultBean.ResultData;
import com.admin.user.dao.AdminUser;

public interface AdminUserService {
    /**
     * 注册用户
     * @return
     */
    ResultData addUser(AdminUser user);
//    boolean updateUserPassword(AdminUser user);
    ResultData getAdminUser(String userId, String password);
}
