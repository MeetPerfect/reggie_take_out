package com.itheima.reggie.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.Service.UserService;
import com.itheima.reggie.entity.User;
import com.itheima.reggie.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Package: com.itheima.reggie.Service.Impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/15 21:11
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
