package com.itheima.reggie.Controller;

import com.itheima.reggie.Common.R;
import com.itheima.reggie.DTO.SetmealDto;
import com.itheima.reggie.Service.SetmealDishService;
import com.itheima.reggie.Service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: SetmealController
 * Package: com.itheima.reggie.Controller
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/12 19:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private SetmealService setmealService;


    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info(setmealDto.toString());
        setmealService.saveWithDish(setmealDto);
        return R.success("新增套餐信息成功");
    }

}
