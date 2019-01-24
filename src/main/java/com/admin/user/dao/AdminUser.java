package com.admin.user.dao;

import lombok.Data;

@Data
public class AdminUser {
    private String userId;
    private String password;
    private String userName;
    private String email;
    private String phone;
    private Integer level;
    private Integer sex;
}
