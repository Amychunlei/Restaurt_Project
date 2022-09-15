package com.pro.reggie.dto;


import com.pro.reggie.entity.Setmeal;
import com.pro.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
