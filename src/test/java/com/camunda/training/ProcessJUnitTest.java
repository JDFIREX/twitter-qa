package com.camunda.training;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.extension.junit5.test.ProcessEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

@ExtendWith(ProcessEngineExtension.class)
public class ProcessJUnitTest {
  
//  @Test
  @Test
  @Deployment(resources = "twitter-qa-3b.bpmn")
  public void testHappyPath() {

    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("approved", true);

    ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("twitter-qa",variables);

    assertThat(processInstance).isEnded();
  }

}
