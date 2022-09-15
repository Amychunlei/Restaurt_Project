package com.pro.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pro.reggie.entity.Dish;
import com.pro.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {
}
