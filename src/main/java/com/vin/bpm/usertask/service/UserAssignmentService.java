package com.vin.bpm.usertask.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Event;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vin.bpm.usertask.model.User;
import com.vin.bpm.usertask.repo.UserRepository;


@Service
public class UserAssignmentService {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;	
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void startProcess(String assignee) {
		
		Map<String, Object> variables = new HashMap<String, Object>();
		User csm_initiator = userRepository.findByName(assignee);
		variables.put("csm_initiator", csm_initiator);
		ProcessInstance processInstance =  runtimeService.startProcessInstanceByKey("ft_process", variables);
		System.out.println("Process Instance"+ processInstance.getName() +" Started..");
	
	}
	
	public void completeTask(String taskId) {
		System.out.println("Complete Task->"+taskId);
		List<Event> lstEvents = taskService.getTaskEvents(taskId);
		lstEvents.forEach(n -> System.out.println(n.getMessage()));
		taskService.complete(taskId);
	}
	
	
	public List<Task> getTask(String assignee) {
		return taskService.createTaskQuery().taskAssignee(assignee).list();
	}
	
	public void initiateUsers(String[] users) {
		if (userRepository.findAll().size() == 0) {
			userRepository.save(new User( users[0],"csmi"));
			userRepository.save(new User( users[1],"csmr"));
			userRepository.save(new User( users[2],"wfl"));
		}

		Map<String, Object> variables = new HashMap<String, Object>();

		User csm_initiator = userRepository.findByName(users[0]);
		User csm_releaser = userRepository.findByName(users[1]);
		User wfl_approver = userRepository.findByName(users[2]);

		variables.put("csm_initiator", csm_initiator);
		variables.put("csm_releaser", csm_releaser);
		variables.put("wfl_approver", wfl_approver);
		
		runtimeService.startProcessInstanceByKey("ft_process", variables);
	
	}
	

	
	
	
	
}
