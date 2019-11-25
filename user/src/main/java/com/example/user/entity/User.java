package com.example.user.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableId("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("login_name")
    private String loginName;

    @TableField("password")
    private String password;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("age")
    private String age;

    @TableField("sex")
    private int sex;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", sex=" + sex +
                '}';
    }
}
