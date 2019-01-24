package com.admin.user.controller;

import com.admin.user.dao.AdminUser;
import com.admin.user.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("/test")
    public Object getTest(){
        return "测试接口1";
    }

    @RequestMapping("/addUser")
    public Object addUser(String password,String userName, String phone,String email){
        AdminUser user = new AdminUser();
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserName(userName);
        return adminUserService.addUser(user);
    }

    @RequestMapping("/loginAdmin")
    public Object getAdminUser(String userId,String password){
        return adminUserService.getAdminUser("11","11");
    }
}
