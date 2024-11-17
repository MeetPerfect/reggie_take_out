package com.itheima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: ReggieApplication
 * Package: com.itheima.reggie
 * Description:
 *
 * @Auther gongkaiming
 * @Create 2024/11/7 21:11
 * @Version 1.0
 */

@Slf4j
@ServletComponentScan
@EnableTransactionManagement
@EnableCaching      // 开启注解缓存
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class, args);
        log.info("项目启动成功");
    }
}



