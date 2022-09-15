package com.pro.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pro.reggie.common.CustomException;
import com.pro.reggie.entity.Category;
import com.pro.reggie.entity.Dish;
import com.pro.reggie.entity.Setmeal;
import com.pro.reggie.mapper.CategoryMapper;
import com.pro.reggie.service.CategoryService;
import com.pro.reggie.service.DishService;
import com.pro.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Service
@Slf4j
//两个泛型是实体类对应的mapper，一个是实体类
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    private final DishService dishService;
    private final SetmealService setmealService;

    public CategoryServiceImpl(DishService dishService, SetmealService setmealService) {
        this.dishService = dishService;
        this.setmealService = setmealService;
    }
    /*
     * @Author chuneleiAmy
     * @Date 9/6/2022 2:40 PM
     * @Description  根据id删除分类，删除之前需要进行判断
     * 根据id删除 分类，删除之前需要进行判断是否有关联数据
     * @Param id
     * @Return
     * @Since version-1.0
     *
     */

    @Override
    public void remove (Long id){
//        查询当前分类是否关联了菜品，如果已经关联，抛出一个业务异常
//        查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
//        正常删除分类

        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();

        //添加查询条件
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        //注意:这里使用count方法的时候一定要传入条件查询的对象，否则计数会出现问题，计算出来的是全部的数据的条数
        long count = dishService.count(dishLambdaQueryWrapper);

        //查询当前分类是否关联了菜品，如果已经管理，直接抛出一个业务异常
        if (count > 0){
            //已经关联了菜品，抛出一个业务异常
            throw new CustomException("当前分类项关联了菜品,不能删除");
        }

        //查询当前分类是否关联了套餐，如果已经管理，直接抛出一个业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper;
        setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);

        //注意:这里使用count方法的时候一定要传入条件查询的对象，否则计数会出现问题，计算出来的是全部的数据的条数
        long setmealCount = setmealService.count(setmealLambdaQueryWrapper);
        if (setmealCount > 0){
            //已经关联了套餐，抛出一个业务异常
            throw new CustomException("当前分类项关联了套餐,不能删除");
        }
        //正常删除
        super.removeById(id);

        }

    }

