package com.camunda.training;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.process_test_coverage.junit5.ProcessEngineCoverageExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(ProcessEngineCoverageExtension.class)
public class ProcessJUnitTest {
  
//  @Test
  @Test
  @Deployment(resources = "twitter-qa-5.bpmn")
  public void testHappyPath() {

    Map<String, Object> startVariables = new HashMap<String, Object>();
    startVariables.put("content", "Exercise 4 test  ;p");


    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("twitter-qa-5", startVariables);

    List<Task> taskList = taskService()
            .createTaskQuery()
            .taskCandidateGroup("management")
            .processInstanceId(processInstance.getId())
            .list();

    assertThat(taskList).isNotNull();
    assertThat(taskList).hasSize(1);

    Map<String, Object> taskVariables = new HashMap<String, Object>();
    taskVariables.put("approved", true);

    Task task = taskList.get(0);

    taskService().complete(task.getId(), taskVariables);

    assertThat(processInstance).isEnded();
  }

}
