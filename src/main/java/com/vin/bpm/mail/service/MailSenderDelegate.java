package com.vin.bpm.mail.service;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class MailSenderDelegate implements JavaDelegate {
@Autowired
@Qualifier("ftsmailsender")
	MailSender mailSender;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(new Date() + "Mail sender execution id"+execution.getEngineServices().getRuntimeService().getVariables(execution.getId()));;
	}

}
