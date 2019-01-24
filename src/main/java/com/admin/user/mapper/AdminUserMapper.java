package com.admin.user.mapper;

import com.admin.user.dao.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface AdminUserMapper {
    int addUser(AdminUser adminUser);
    AdminUser getAdminUser(@Param("userId")String userId, @Param("password")String password);

    AdminUser selectAll();
}
