package com.example.user.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("account")
public class Account {
    @TableId("account_id")
    private String accountId;

    @TableField("user_id")
    private String userId;

    @TableField("balance")
    private BigDecimal balance;

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", userId='" + userId + '\'' +
                ", loginName=" + balance +
                '}';
    }
}
