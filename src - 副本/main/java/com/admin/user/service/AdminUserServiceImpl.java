package com.admin.user.service;

import com.admin.user.dao.AdminUser;
import com.admin.user.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl implements AdminUserService{
    @Autowired
    private AdminUserMapper adminUserMapper;


    @Override
    public boolean addUser(AdminUser user) {
        return adminUserMapper.addUser(user)>0;
    }

//    @Override
//    public boolean updateUserPassword(AdminUser user) {
//        return false;
//    }

    @Override
    public AdminUser getAdminUser(String userId, String password) {
        System.out.println("chishila");
        int i =adminUserMapper.getAdminUser(userId,password);
        return new AdminUser();
    }
}
