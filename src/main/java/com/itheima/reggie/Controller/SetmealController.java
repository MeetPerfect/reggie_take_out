package com.itheima.reggie.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.Common.R;
import com.itheima.reggie.DTO.SetmealDto;
import com.itheima.reggie.Service.CategoryService;
import com.itheima.reggie.Service.SetmealDishService;
import com.itheima.reggie.Service.SetmealService;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Setmeal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private CategoryService categoryService;
    /**
     * 新增套餐信息
     *
     * @param setmealDto
     * @return
     */
    @PostMapping
    @CacheEvict(value="setmealCache", allEntries = true)
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info(setmealDto.toString());
        setmealService.saveWithDish(setmealDto);
        return R.success("新增套餐信息成功");
    }

    /**
     * 套餐分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        // 分页构造器
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> pageDto = new Page<>();


        LambdaQueryWrapper<Setmeal> QueryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        QueryWrapper.like(name != null, Setmeal::getName, name);

        QueryWrapper.orderByAsc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo, QueryWrapper);

        BeanUtils.copyProperties(pageInfo, pageDto, "records");

        List<Setmeal> records = pageInfo.getRecords();

        List<SetmealDto> list =records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);

            setmealDto.setCategoryName(category.getName());
            return  setmealDto;
        }).collect(Collectors.toList());
        pageDto.setRecords(list);
        return R.success(pageDto);

    }

    /**
     *  删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    @CacheEvict(value="setmealCache", allEntries = true)
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("ids: {}", ids);
        setmealService.removeWithDish(ids);
        return R.success("套餐删除成功");
    }

    /**
     * 根据条件查询套餐数据
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    @Cacheable(value = "setmealCache", key="#setmeal.categoryId + '_' + #setmeal.status")
    public R<List<Setmeal>> list(Setmeal setmeal) {

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();

        setmealLambdaQueryWrapper.eq(setmeal.getCategoryId() != null, Setmeal::getCategoryId, setmeal.getCategoryId());
        setmealLambdaQueryWrapper.eq(setmeal.getStatus() != null, Setmeal::getStatus, setmeal.getStatus());
        setmealLambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);

        List<Setmeal> list = setmealService.list(setmealLambdaQueryWrapper);
        return R.success(list);
    }

}
