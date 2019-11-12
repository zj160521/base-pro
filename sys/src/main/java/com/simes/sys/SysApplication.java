package com.simes.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/9/5 16:17
 */
@SpringBootApplication(scanBasePackages = "com.simes")
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableSwagger2
@MapperScan("com.simes.sys.dao")
@EnableFeignClients(basePackages = "com.simes.sys.feign")
public class SysApplication {
    public static void main( String[] args ) {
        SpringApplication.run(SysApplication.class, args);
    }
}
