package com.lijun.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	@Autowired
	private AmqpTemplate template;

	public void send() {
		template.convertAndSend("server", "", "hello,rabbit~");
	}

	public void send(String msg) {
		template.convertAndSend("server", "", msg);
	}

}
