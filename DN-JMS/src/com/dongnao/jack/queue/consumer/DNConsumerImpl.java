package com.dongnao.jack.queue.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class DNConsumerImpl implements DNConsumer {

	public static String USENAME = ActiveMQConnection.DEFAULT_USER;

	public static String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

	public static String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	ConnectionFactory connectionFactory;

	Connection connection;

	Session session;

	MessageConsumer messageConsumer;

	public void init() {
		try {
			connectionFactory = new ActiveMQConnectionFactory(USENAME, PASSWORD, URL);

			connection = connectionFactory.createConnection();

			connection.start();

			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getMessage(String queueName) {
		try {
			Queue queue = session.createQueue(queueName);

			messageConsumer = session.createConsumer(queue);

			while (true) {
				TextMessage message = (TextMessage) messageConsumer.receive();

				if (message != null) {
					System.out.println("我是短信发送平台，已经成功接收到您的短信发送消息：" + message.getText());
					message.acknowledge();
				} else {
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
