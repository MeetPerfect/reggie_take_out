package com.itheima.reggie.Controller;

/**
 * ClassName: DishController
 * Package: com.itheima.reggie.Controller
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/9 21:40
 * @Version 1.0
 */

import com.itheima.reggie.Common.R;
import com.itheima.reggie.DTO.DishDto;
import com.itheima.reggie.Service.DishFlavorService;
import com.itheima.reggie.Service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜品管理
 */

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品
     *
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        log.info("新增菜品, 参数: {}", dishDto);
        dishService.saveDishWithFlavor(dishDto);

        return R.success("新增菜品成功");
    }


}
