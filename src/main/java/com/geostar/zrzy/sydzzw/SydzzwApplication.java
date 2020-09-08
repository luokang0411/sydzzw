package com.geostar.zrzy.sydzzw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@MapperScan(basePackages="com.geostar.zrzy.sydzzw.dao")
@EnableScheduling
public class SydzzwApplication {

	public static void main(String[] args) {
		SpringApplication.run(SydzzwApplication.class, args);
	}

}
