package com.nouga.ActiveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author Nouga
 *Date : 8/5
 */
public class JMSConsumer {

	// 默认的用户名，密码，连接地址，发送消息数量。
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) throws Exception {
		//连接工厂
		ConnectionFactory connectionFactory ;
		//连接
		Connection connection = null ;
		//一次会话
		Session session ;
		//目的地
		Destination destination ;
		//消息消费者
		MessageConsumer messageConsumer ;
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, "tcp://192.168.25.128:61616");
		//得到连接、启动连接
		connection = factory.createConnection();
		connection.start();
		//创建会话，创建队列
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue("HelloActiveMQ");
		//创建消费者
		MessageConsumer consumer = session.createConsumer(destination);
		while(true){
			TextMessage textMessage = (TextMessage) consumer.receive(10000);
			if(textMessage != null ){
				System.out.println(textMessage.getText());
				
			}else{
				break;
			}
		}
		
	}
}
