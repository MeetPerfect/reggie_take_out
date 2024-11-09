package com.itheima.reggie.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.DTO.DishDto;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.mapper.DishMapper;

/**
 * ClassName: DishService
 * Package: com.itheima.reggie.Service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/9 12:46
 * @Version 1.0
 */
public interface DishService extends IService<Dish>{

    /**
     * 新增菜品，同时插入对应的菜品表和口味表数据
     * @param dishDto
     */
    public void saveDishWithFlavor(DishDto dishDto);
}
