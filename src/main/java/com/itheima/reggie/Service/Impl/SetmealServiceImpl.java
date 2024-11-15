package com.itheima.reggie.Service.Impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.Common.CustomException;
import com.itheima.reggie.DTO.SetmealDto;
import com.itheima.reggie.Service.SetmealDishService;
import com.itheima.reggie.Service.SetmealService;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.entity.SetmealDish;
import com.itheima.reggie.mapper.SetmealMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: SetmealServiceImpl
 * Package: com.itheima.reggie.Service.Impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/9 12:47
 * @Version 1.0
 */
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {

        // 保存套餐基本信息，操作setemal,执行insert语句
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();

        setmealDishes = setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        // 保存套餐和菜品的相关信息，操作setmeal_dish,执行insert语句
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐以及关联的关系表中的菜品数据
     * @param ids
     */
    @Transactional
    public void removeWithDish(List<Long> ids) {

        // 查询套餐状态，确定可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);
        int count = this.count(queryWrapper);
        // 如果状态处于起售状态，抛出异常不能删除
        if (count > 0) {
            throw new CustomException("套餐正在起售中，不能删除");
        }
        // 如果状态处于下架状态，可以删除
        // 删除套餐，操作setmeal表
        this.removeByIds(ids);

        // 删除关系表中的菜品
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);

        setmealDishService.remove(setmealDishLambdaQueryWrapper);
    }
}
