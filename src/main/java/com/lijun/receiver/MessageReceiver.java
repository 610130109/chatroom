package com.lijun.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lijun.view.Window;

@Component
public class MessageReceiver {

	@Autowired
	private Window window;

	@RabbitListener(queues = "#{app.QUEUE_ID}") // 监听器监听指定的Queue
	public void process1(String str) {
		window.addHistory(str);
	}

}
