package com.example.user.service;

import com.example.user.entity.User;

import java.math.BigDecimal;

public interface UserService {
    int addUser(User user);
    User getUserById(String userId);
    User getUserByLoginName(String loginName);

    int updateAccountByUserId(String userId, BigDecimal amount);
}
