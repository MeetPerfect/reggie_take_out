package com.itheima.reggie.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.DTO.SetmealDto;
import com.itheima.reggie.entity.Setmeal;

import java.util.List;

/**
 * ClassName: SetmealService
 * Package: com.itheima.reggie.Service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/9 12:46
 * @Version 1.0
 */
public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐及关联的菜品数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
