package com.zhengcheng.testom2m;

import com.zhengcheng.testom2m.service.RestService;
import com.zhengcheng.testom2m.service.RestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Testom2mApplication {


	static RestService restService = new RestServiceImpl();

	public static void main(String[] args) {
		SpringApplication.run(Testom2mApplication.class, args);

		//String url = "http://140.143.32.41:8080/~/in-cse";
		String url = "http://127.0.0.1:8080/~/in-cse";
		restService.sendGet(url, 100);
		restService.sendGet(url, 1000);
		restService.sendGet(url, 10000);
		restService.sendGet(url, 100000);
	}
}
