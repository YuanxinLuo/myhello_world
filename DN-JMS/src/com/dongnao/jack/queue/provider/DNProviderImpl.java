package com.dongnao.jack.queue.provider;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class DNProviderImpl implements DNProvider {

	public static String USENAME = ActiveMQConnection.DEFAULT_USER;

	public static String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

	public static String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	ConnectionFactory connectionFactory;

	Connection connection;

	Session session;

	MessageProducer messageProducer;

	public void init() {
		try {
			connectionFactory = new ActiveMQConnectionFactory(USENAME, PASSWORD, URL);

			connection = connectionFactory.createConnection();

			connection.start();

			session = connection.createSession(true, Session.SESSION_TRANSACTED);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void sendMessage(String queueName) {
		try {
			Queue queue = session.createQueue(queueName);

			messageProducer = session.createProducer(queue);

			for (int i = 0; i < 100; i++) {
				TextMessage tm = session.createTextMessage("我是XX平台，我需要发送短信，短信内容是：content" + i);
				messageProducer.send(tm);
			}

			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
