package com.activiti.extension.bean;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("activeMQSender")
public class ActiveMQSender {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(String processInstanceId) throws Exception {
		System.out.println("Downstream Messaging - Active MQ");
		jmsTemplate.send(new ActiveMQQueue("act2ds1"), new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session
						.createObjectMessage("from activiti, for process with id: " + processInstanceId);
				return objectMessage;
			}
		});
		System.out.println("Downstream Messaging - Active MQ - Sent {execution.getProcessInstanceId()}" + processInstanceId);

	}

}
