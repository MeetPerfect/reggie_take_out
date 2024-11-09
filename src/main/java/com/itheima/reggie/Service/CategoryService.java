package com.itheima.reggie.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Category;

/**
 * ClassName: CategoryService
 * Package: com.itheima.reggie.Service
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/9 12:06
 * @Version 1.0
 */
public interface CategoryService extends IService<Category> {

    /**
     * 根据id删除分类
     * @param id
     */
    public void remove(Long id);
}
