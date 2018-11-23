package com.activiti.extension.bean;

import javax.annotation.PostConstruct;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class DownStreamMessaging implements JavaDelegate , ApplicationContextAware{

	@Autowired
	ActiveMQSender activeMQSender;
	
	ApplicationContext applicationContext;
	
	
	public DownStreamMessaging() {
	}
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		System.out.println("Call JMS ..");
		activeMQSender.send(execution.getId());
	}
	
	public void setActiveMQSender(ActiveMQSender activeMQSender) {
		this.activeMQSender = activeMQSender;
	}

	@Override
	@PostConstruct
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		applicationContext=arg0;
		activeMQSender=(ActiveMQSender) applicationContext.getBean("activeMQSender");
	}

	

}
