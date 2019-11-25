package com.example.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.user.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
