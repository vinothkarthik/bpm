package com.vin.bpm.usertask;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.vin.bpm.usertask.model.User;
import com.vin.bpm.usertask.repo.UserRepository;
import com.vin.bpm.usertask.service.UserAssignmentService;

/**
 * Unit test for simple App.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
public class AppTest 
{
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	UserAssignmentService userAssignmentService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskService taskService;

/*	@Autowired
	ActiveMQSender activeMQSender;
*/	
	@Test
	public void contextLoads() {
	}
	
	
//	@Test
	public void assignTasks() {
		String[] users = new String[]{"csmi","csmr","wfl"};
	    userAssignmentService.initiateUsers(users);	
	}

	@Test
	public void initiateFT() {
		
/*		
		String[] users = new String[]{"csmi","csmr","wfl"};
	    userAssignmentService.initiateUsers(users);	

		String initiator = "csmi";
		userAssignmentService.startProcess(initiator);
*/	
		String[] users = new String[]{"csmi","csmr","wfl"};

/*		userRepository.save(new User( users[0],"csmi"));
		userRepository.save(new User( users[1],"csmr"));
		userRepository.save(new User( users[2],"wfl"));
*/
		
		Map<String, Object> variables = new HashMap<String, Object>();

		User csm_initiator = userRepository.findByName(users[0]);
		User csm_releaser = userRepository.findByName(users[1]);
		User wfl_approver = userRepository.findByName(users[2]);
		


		variables.put("csm_initiator", csm_initiator.getName());
		variables.put("csm_releaser", csm_releaser.getName());
		variables.put("wfl_approver", wfl_approver.getName());
		
		ProcessInstance processInstance =  runtimeService.startProcessInstanceByKey("ft_process", variables);
		System.out.println("Process Instance"+ processInstance.getId() +" Started..");
// Initiated
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

	    System.out.println("FT Initiate Assigned.. ");
	    System.out.println("Task Id:"+task.getId() +"|| Assignee:"+task.getAssignee());
	    
	    taskService.complete(task.getId());
	    System.out.println("FT Initiate.. Task Completed ");
	
// Ready to release	    
	    Task task2 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
	    System.out.println("Ready to release : Task Id:"+task2.getId() +"|| Assignee:"+task2.getAssignee());
	    taskService.complete(task2.getId());

	    System.out.println("FT Release.. Task Completed ");
	    
	    Task task3 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
	    Map<String, Object> task3_variable = new HashMap<String, Object>();
	    System.out.println("Work Flow Lead release : Task Id:"+task3.getId() +"|| Assignee:"+task3.getAssignee());
	    taskService.complete(task3.getId(),CollectionUtil.singletonMap("Approved", true));
	    System.out.println("WFL Approved.. Task Completed ");
	    
/*	    Task task4 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
	    System.out.println("Send Message : Task Id:"+task4.getId());
	    System.out.println("Send Message : Task Id:"+task4.getId() +" Send JMS Message");
	    taskService.complete(task4.getId());
	    
	    
	    Task task5 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
	    System.out.println("Receive Message : Task Id:"+task5.getId() +"|| Assignee:"+task5.getAssignee());
	    taskService.complete(task5.getId());
*/	    
	    
	    
	    
	}
	
	
	
//	@Test
	public void initiateFTReject() {
		
/*		
		String[] users = new String[]{"csmi","csmr","wfl"};
	    userAssignmentService.initiateUsers(users);	

		String initiator = "csmi";
		userAssignmentService.startProcess(initiator);
*/	
		String[] users = new String[]{"csmi","csmr","wfl"};

/*		userRepository.save(new User( users[0],"csmi"));
		userRepository.save(new User( users[1],"csmr"));
		userRepository.save(new User( users[2],"wfl"));
*/
		
		Map<String, Object> variables = new HashMap<String, Object>();

		User csm_initiator = userRepository.findByName(users[0]);
		User csm_releaser = userRepository.findByName(users[1]);
		User wfl_approver = userRepository.findByName(users[2]);
		


		variables.put("csm_initiator", csm_initiator.getName());
		variables.put("csm_releaser", csm_releaser.getName());
		variables.put("wfl_approver", wfl_approver.getName());
		
		ProcessInstance processInstance =  runtimeService.startProcessInstanceByKey("ft_process", variables);
		System.out.println("Process Instance"+ processInstance.getId() +" Started..");
// Initiated
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

	    System.out.println("FT Initiate Assigned.. ");
	    System.out.println("Task Id:"+task.getId() +"|| Assignee:"+task.getAssignee());
	    
	    taskService.complete(task.getId());
	    System.out.println("FT Initiate.. Task Completed ");
	
// Ready to release	    
	    Task task2 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
	    System.out.println("Ready to release : Task Id:"+task2.getId() +"|| Assignee:"+task2.getAssignee());
	    taskService.complete(task2.getId());

	    System.out.println("FT Release.. Task Completed ");
// work flow lead rejected 	    
	    Task task3 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
	    System.out.println("Work Flow Lead release : Task Id:"+task3.getId() +"|| Assignee:"+task3.getAssignee());
	    taskService.complete(task3.getId(),CollectionUtil.singletonMap("Approved", false));
//	    
	    System.out.println("WFL Rejected.. Task Completed ");
	 // Ready to release	    
	    Task task4 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
	    System.out.println("Ready to release : Task Id:"+task4.getId());
	    taskService.complete(task4.getId());
	    System.out.println("Completed : Task Id:"+task4.getId());

	    
	 // work flow lead approved 	    
	    Task task5 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
	    System.out.println("Work Flow Lead release : Task Id:"+task5.getId() +"|| Assignee:"+task5.getAssignee());
	    taskService.complete(task5.getId(),CollectionUtil.singletonMap("Approved", true));

	    
	}
	

}
