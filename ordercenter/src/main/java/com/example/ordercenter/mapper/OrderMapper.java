package com.example.ordercenter.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.ordercenter.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
