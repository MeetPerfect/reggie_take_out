package com.itheima.reggie.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.Service.AddressBookService;
import com.itheima.reggie.entity.AddressBook;
import com.itheima.reggie.mapper.AddressBookMapper;
import org.springframework.stereotype.Service;

/**
 * ClassName: AddressBookServiceImpl
 * Package: com.itheima.reggie.Service.Impl
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/15 22:37
 * @Version 1.0
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
