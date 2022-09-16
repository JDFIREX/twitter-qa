package com.camunda.training;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.process_test_coverage.junit5.ProcessEngineCoverageExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

@ExtendWith(ProcessEngineCoverageExtension.class)
public class ProcessJUnitTest {
  
//  @Test
  @Test
  @Deployment(resources = "twitter-qa-3b.bpmn")
  public void testHappyPath() {

    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("approved", true);
    variables.put("content", "Exercise 4 test  ;p");

    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("twitter-qa",variables);

    assertThat(processInstance).isEnded();
  }

}
