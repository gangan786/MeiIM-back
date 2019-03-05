package org.meizhuo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
// 扫描mybatis mapper包路径
@MapperScan(basePackages = "org.meizhuo.mapper")
@ComponentScan(basePackages = {"org.meizhuo", "org.n3r.idworker"})
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
