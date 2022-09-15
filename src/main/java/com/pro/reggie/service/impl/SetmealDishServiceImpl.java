package com.pro.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pro.reggie.entity.SetmealDish;
import com.pro.reggie.mapper.SetmealDishMapper;
import com.pro.reggie.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//两个泛型是实体类对应的mapper，一个是实体类
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
}
