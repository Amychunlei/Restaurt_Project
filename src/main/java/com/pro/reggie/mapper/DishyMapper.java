package com.pro.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pro.reggie.entity.Category;
import com.pro.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishyMapper extends BaseMapper<Dish> {
}
