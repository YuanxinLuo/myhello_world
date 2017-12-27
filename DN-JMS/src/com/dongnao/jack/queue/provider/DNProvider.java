package com.dongnao.jack.queue.provider;

public interface DNProvider {

	public void init();

	public void sendMessage(String queueName);

}
