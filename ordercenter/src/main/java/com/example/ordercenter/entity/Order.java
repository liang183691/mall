package com.example.ordercenter.entity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId("order_id")
    private String orderId;

    @TableField("user_id")
    private String userId;

    @TableField("user_name")
    private String userName;

    @TableField("total_coast")
    private BigDecimal totalCoast;

    //0 取消 1 正常
    @TableField("status")
    private int status;

    //0 待支付 1 已支付 2已退款
    @TableField("pay_status")
    private int payStatus;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", totalCoast=" + totalCoast +
                ", status=" + status +
                ", payStatus=" + payStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
