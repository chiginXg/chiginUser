package com.admin.user.mapper;

import com.admin.user.dao.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserMapper {
    int addUser(AdminUser adminUser);
//    int updateUserPassword();
    int getAdminUser(@Param("userId")String userId, @Param("password")String password);
}
