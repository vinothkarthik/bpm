package com.vin.bpm.jms.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class DownStreamMessaging implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		System.out.println("Call JMS ..");
		
	}

	

}
