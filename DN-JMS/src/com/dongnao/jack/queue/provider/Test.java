package com.dongnao.jack.queue.provider;

public class Test {

	public static void main(String[] args) {
		DNProvider dnp = new DNProviderImpl();
		dnp.init();
		dnp.sendMessage("DN-JACK-QUEUE1");
	}

}
