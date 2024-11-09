package com.itheima.reggie.Service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.Service.DishService;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.mapper.DishMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ClassName: DishServiceImpl
 * Package: com.itheima.reggie.Service.Impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/9 12:47
 * @Version 1.0
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
