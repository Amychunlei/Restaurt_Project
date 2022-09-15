package com.pro.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pro.reggie.dto.SetmealDto;
import com.pro.reggie.entity.Category;
import com.pro.reggie.entity.Setmeal;

import java.util.List;

public interface  SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param ids
     */
    void removeWithDish(List<Long> ids);
}
