package com.itheima.reggie.Common;

/**
 * ClassName: BaseContext
 * Package: com.itheima.reggie.Common
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/9 11:37
 * @Version 1.0
 */

/**
 * 基于ThreadLocal封装工具类，用户保存获取当前登录用户id
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前用户id
     * @param id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取当前用户id
     * @return
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
