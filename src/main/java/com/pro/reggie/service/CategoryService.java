package com.pro.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pro.reggie.entity.Category;
import com.pro.reggie.entity.Employee;

public interface CategoryService extends IService<Category> {
    //在CategoryService中定义自己需要的方法，直接写就行
    void remove(Long id);
}
