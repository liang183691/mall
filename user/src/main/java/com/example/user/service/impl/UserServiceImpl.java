package com.example.user.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.user.entity.Account;
import com.example.user.entity.User;
import com.example.user.mapper.AccountMapper;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    AccountMapper accountMapper;

    @Override
    public int addUser(User user) {
        return 1;
    }

    @Override
    public User getUserById(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return null;
    }

    @Override
    public int updateAccountByUserId(String userId, BigDecimal amount) {
        Wrapper wrapper = Condition.create().eq("user_id",userId);
        List<Account> accountList = accountMapper.selectList(wrapper);
        BigDecimal balance = accountList.get(0).getBalance().divide(amount);
        accountList.get(0).setBalance(balance);
        return accountMapper.updateById(accountList.get(0));
    }
}
