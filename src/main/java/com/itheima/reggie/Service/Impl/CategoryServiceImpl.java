package com.itheima.reggie.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.Common.CustomException;
import com.itheima.reggie.Service.CategoryService;
import com.itheima.reggie.Service.DishService;
import com.itheima.reggie.Service.SetmealService;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.itheima.reggie.Service.Impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/9 12:07
 * @Version 1.0
 */
@Service
@Slf4j
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 根据id删除分类，删除之前进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件，根据分类id查询
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);

        int count1 = dishService.count(dishLambdaQueryWrapper);
        // 查询当前分类是否关联菜品，若已关联，抛出异常
        if (count1 > 0) {
            // 关联菜品，抛出异常
            throw new CustomException("当前分类下关联菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        // 查询当前分类是否关联套餐，若已关联，抛出异常
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if(count2 > 0) {
            // 关联套餐，抛出异常
            throw new CustomException("当前分类下关联套餐，不能删除");
        }

        // 正常删除
        super.removeById(id);

    }
}
