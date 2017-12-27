package com.dongnao.jack.queue.consumer;

public interface DNConsumer {

	void init();
	
	void getMessage(String queueName);
}
