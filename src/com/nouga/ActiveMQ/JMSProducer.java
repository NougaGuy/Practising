package com.nouga.ActiveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 消息的生产者
 * @author Nouga Date : 8/5
 * Use Version : 5.15
 */
public class JMSProducer {

	// 默认的用户名，密码，连接地址，发送消息数量。
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SENDNUM = 10;
	// 连接工厂
	private static ConnectionFactory connectionFactory;
	// 连接
	private static Connection connection = null;
	// 会话 接收或者发送消息的线程
	private static Session session;
	// 消息的目的地
	private static Destination destination;
	// 消息的生产者
	private static MessageProducer messageProducer;

	private static ActiveMQConnectionFactory getFactory() {
		// 实例化连接工厂
		ActiveMQConnectionFactory factory;
		factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, "tcp://192.168.25.128:61616");
		return factory;
	}

	public static void main(String[] args) throws Exception {

		ActiveMQConnectionFactory factory = getFactory();
		Connection conn = factory.createConnection();
		conn.start();
		session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createTopic("Hello Topics");
		TextMessage mes = session.createTextMessage();
		mes.setText("This is text of topic . ");
		messageProducer = session.createProducer(destination);
		messageProducer.send(destination, mes);
		messageProducer.close();
		session.close();
		conn.close();
	}

/*	protected void testQueue() {
		try {
			ActiveMQConnectionFactory factory = getFactory();
			connection = factory.createConnection();
			connection.start();
			// 第一个参数是否开启事务，一般不实用事务，保证数据的一致性，可使用消息队列实现。
			// 如果第一个参数为true，第二个参数自动忽略。如果不开启事务，第二个参数为消息的应答模式。一般自动应答就可以。
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 这是它的名字
			destination = session.createQueue("HelloActiveMQ");
			messageProducer = session.createProducer(destination);
			TextMessage message = session.createTextMessage("");
			messageProducer.send(destination, message);
			messageProducer.close();
			session.close();
			connection.close();

		} catch (JMSException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}*/

}
