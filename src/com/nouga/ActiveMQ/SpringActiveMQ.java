package com.nouga.ActiveMQ;


import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SpringActiveMQ {

	@Test
	public void testJmsTemplate () throws Exception {
		//初始化Spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
		//加载JmsTemplate
		JmsTemplate template = applicationContext.getBean(JmsTemplate.class);
		//加载Destination
		Destination destination = (Destination) applicationContext.getBean("jmsQueueTemplate");
		//发送消息
		template.send(destination, new MessageCreator(){

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage("Spring ActiveMQ Send Queue Message");
				return message;
			}});
	}
}
