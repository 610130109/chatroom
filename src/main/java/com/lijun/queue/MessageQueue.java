package com.lijun.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lijun.App;

@Configuration
public class MessageQueue {

	@Bean(name = "queue")
	public Queue queue() {
		return new Queue(App.QUEUE_ID);
	}

	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange("server");// 配置广播路由器
	}

	@Bean
	Binding bindingExchangeA(@Qualifier("queue") Queue queue, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(queue).to(fanoutExchange);
	}

}
