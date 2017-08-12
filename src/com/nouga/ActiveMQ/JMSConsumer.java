package com.nouga.ActiveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author Nouga Date : 8/5
 * Use Version : 5.15
 */
public class JMSConsumer {
	// 默认的用户名，密码，连接地址，发送消息数量。
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	// 连接工厂
	private static ConnectionFactory connectionFactory;
	// 连接
	private static Connection connection = null;
	// 一次会话
	private static Session session;
	// 目的地
	private static Destination destination;
	// 消息消费者
	private static MessageConsumer messageConsumer;

	private static ActiveMQConnectionFactory factory;

	private static ActiveMQConnectionFactory getFactory() {
		factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, "tcp://192.168.25.128:61616");
		return factory;
	}

	public static void main(String[] args) throws Exception {
		ActiveMQConnectionFactory fc = getFactory();
		connection = fc.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createTopic("Hello Topics");
		messageConsumer = session.createConsumer(destination);
		while(true) {
			TextMessage receive = (TextMessage) messageConsumer.receive(10000);
			if(receive != null ){
				System.out.println(receive.getText());
			} else {
				break;
			}
		}
	}
	
	/*protected void testQueueConsumer() throws Exception {
		ActiveMQConnectionFactory factory = getFactory();
		// 得到连接、启动连接
		connection = factory.createConnection();
		connection.start();
		// 创建会话，创建队列
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue("HelloActiveMQ");
		// 创建消费者
		MessageConsumer consumer = session.createConsumer(destination);
		while (true) {
			TextMessage textMessage = (TextMessage) consumer.receive(10000);
			if (textMessage != null) {
				System.out.println(textMessage.getText());

			} else {
				break;
			}
		}
	}*/
}
