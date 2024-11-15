package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserMapper
 * Package: com.itheima.reggie.mapper
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/15 21:09
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
