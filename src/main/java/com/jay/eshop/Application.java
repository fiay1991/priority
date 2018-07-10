package com.jay.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.jay.eshop.common.config.DruidDataSourceConfig;

/**
 * 系统启动类
 * @author jayjluo
 *
 */
@SpringBootApplication
@Import(DruidDataSourceConfig.class)
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
