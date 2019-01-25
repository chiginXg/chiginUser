package com.admin.user.service;

import com.admin.user.ResultBean.ResultCode;
import com.admin.user.ResultBean.ResultData;
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
    public ResultData addUser(AdminUser user) {
        return adminUserMapper.addUser(user)>0?
                new ResultData(ResultCode.SUCCESS):
                new ResultData(ResultCode.ERROR);
    }


    @Override
    public ResultData getAdminUser(String userId, String password) {
        AdminUser adminUser = adminUserMapper.getAdminUser(userId, password);
        return  adminUser!=null?
                new ResultData<>(ResultCode.SUCCESS,adminUser):
                new ResultData(ResultCode.PASSWORDERROR);
    }
}
