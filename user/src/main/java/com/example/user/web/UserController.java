package com.example.user.web;

import com.example.common.ResponseResult;
import com.example.user.entity.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping
    public ResponseResult getUserById(@RequestParam("userId") String userId){
        User user = userService.getUserById(userId);
        return ResponseResult.success(user);
    }
}
