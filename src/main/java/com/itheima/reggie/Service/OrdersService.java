package com.itheima.reggie.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Orders;

/**
 * ClassName: OrderService
 * Package: com.itheima.reggie.Service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/16 12:30
 * @Version 1.0
 */
public interface OrdersService extends IService<Orders> {

    public void submit(Orders orders);
}
