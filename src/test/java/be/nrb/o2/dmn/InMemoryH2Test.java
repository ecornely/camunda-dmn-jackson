package be.nrb.o2.dmn;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.init;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.execute;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.job;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.LoggerFactory;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

  @ClassRule
  @Rule
  public static ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create().build();

  private static final String PROCESS_DEFINITION_KEY = "dmn-jackson";

  static {
    LogFactory.useSlf4jLogging(); // MyBatis
  }

  @Before
  public void setup() {
    init(rule.getProcessEngine());
  }

  /**
   * Just tests if the process definition is deployable.
   */
  @Test
  @Deployment(resources = "process.bpmn")
  public void testParsingAndDeployment() {
    // nothing is done here, as we just want to check for exceptions during deployment
  }

  @Test
  @Deployment(resources = {"process.bpmn", "DetermineCustomisationWorkflows.dmn"})
  public void testHappyPath() {
    LoggerDelegate loggerDelegate = new LoggerDelegate();
    Mocks.register("loggerDelegate", loggerDelegate);
	  ProcessEngine processEngine = processEngine();
    RuntimeService runtimeService = processEngine.getRuntimeService();
	  ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
    execute(job(processInstance));
	  assertThat(processInstance).hasPassed("Task_1l0ld93");
	  assertThat(processInstance).hasVariables("templateName");
	  LoggerFactory.getLogger(this.getClass()).debug("The templateName created by javascript is : {}", runtimeService.getVariable(job(processInstance).getExecutionId(), "templateName"));
	  execute(job(processInstance));
	  execute(job(processInstance));
	  assertThat(processInstance).hasPassed("Task_09iy3jw");
	  assertThat(processInstance).hasVariables("workflowIds");
	  Object workflowIds = runtimeService.getVariable(job(processInstance).getExecutionId(), "workflowIds");
    LoggerFactory.getLogger(this.getClass()).debug("The workflowIds created by javascript is : {}", workflowIds);
	  execute(job(processInstance));
	  execute(job(processInstance));
	  assertThat(processInstance).hasPassed("Task_1lcxg6e");
	  execute(job(processInstance));
	  assertThat(processInstance).isEnded();
  }

}
