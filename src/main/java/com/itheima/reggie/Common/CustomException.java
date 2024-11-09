package com.itheima.reggie.Common;

/**
 * ClassName: CustomException
 * Package: com.itheima.reggie.Common
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/9 13:10
 * @Version 1.0
 */

/**
 * 自定义业务异常类
 */
public class CustomException extends RuntimeException{

    public CustomException(String message) {
        super(message);
    }

}
