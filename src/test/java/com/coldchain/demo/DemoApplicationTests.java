package com.coldchain.demo;

import com.coldchain.demo.mapper.Itblcar;
import com.coldchain.demo.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	Itblcar itblcar;
	@Test
	public void contextLoads() {
		String createdData = DateUtil.getNow();
		System.out.println("时间"+createdData);
		DateUtil.toTimestamp(createdData);
		System.out.println(DateUtil.toTimestamp(createdData));



	}

}

