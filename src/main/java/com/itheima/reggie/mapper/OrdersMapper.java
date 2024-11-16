package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: OrderMapper
 * Package: com.itheima.reggie.mapper
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/16 12:29
 * @Version 1.0
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
