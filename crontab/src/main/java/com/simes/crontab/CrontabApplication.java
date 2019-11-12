package com.simes.crontab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Description:
 * @Author: zhouj
 * @Date: 2019/10/22 9:21
 */
@SpringBootApplication(scanBasePackages = "com.simes")
@EnableTransactionManagement
@EnableDiscoveryClient
@EnableSwagger2
@MapperScan("com.simes.crontab.dao")
@EnableFeignClients(basePackages = "com.simes.crontab.feign")
public class CrontabApplication {
    public static void main( String[] args ) {
        SpringApplication.run(CrontabApplication.class, args);
    }
}
