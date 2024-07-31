package com.ruoyi.view;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pjh
 * @created 2024/7/2
 */
@SpringBootApplication
@MapperScan("com.ruoyi.view.mapper")
public class ViewApplication {
    public static void main(String[] args) {
        SpringApplication.run(ViewApplication.class, args);
        System.out.println("大屏后台模块启动成功。维护人：浦金宏" );
    }
}
