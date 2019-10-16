package com.example.user.service;

import com.example.user.entity.User;

public interface UserService {
    int addUser(User user);
    User getUserById(String userId);
    User getUserByLoginName(String loginName);
}
