package com.activiti.extension.bean;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;


@Service
public class ActiveMQListener {


	@Autowired
	RepositoryService repositoryService;
	
	@Autowired
	RuntimeService runtimeService;
	
	@JmsListener(destination = "ds12act")
	public void processMessage(String payload) {
	
		
		System.out.println("Message received:"+payload);


		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey(payload)
				.latestVersion().singleResult();
		
/*		runtimeService.createExecutionQuery().
        activityId("wachtenAfgewerkt").
        singleResult();
*/
		
/*		
		runtimeService.signal(executionId) ;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("initiator", user.getId());
		map.put("message", payload);
		activitiService.startProcessInstance (pd.getId(), map, "amq-start");
*/	
		
	
	}
}