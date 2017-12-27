package com.dongnao.jack.queue.consumer;

public class Test {

	public static void main(String[] args) {
		DNConsumer dnp = new DNConsumerImpl();
		dnp.init();
		dnp.getMessage("DN-JACK-QUEUE1");
	}

}
