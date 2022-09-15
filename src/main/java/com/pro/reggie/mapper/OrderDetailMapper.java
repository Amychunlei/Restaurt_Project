package com.pro.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pro.reggie.entity.OrderDetail;
import com.pro.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
