package com.yonyou.lijun.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lijun.App;
import com.lijun.sender.MessageSender;

@SpringBootTest(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MyTest {

	@Autowired
	private MessageSender messageSender;

	@Test
	public void testRabbit() {
		messageSender.send();
	}
}
