package be.nrb.o2.dmn;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * This is an empty service implementation illustrating how to use a plain Java 
 * class as a BPMN 2.0 Service Task delegate.
 */
@Component
public class LoggerDelegate implements JavaDelegate {
 
  private final Logger LOGGER = Logger.getLogger(LoggerDelegate.class.getName());
  
  public void execute(DelegateExecution execution) throws Exception {
    
    LOGGER.info("\n\n  ... LoggerDelegate invoked by "
            + "processDefinitionId=" + execution.getProcessDefinitionId()
            + ", activtyId=" + execution.getCurrentActivityId()
            + ", activtyName='" + execution.getCurrentActivityName() + "'"
            + ", processInstanceId=" + execution.getProcessInstanceId()
            + ", businessKey=" + execution.getProcessBusinessKey()
            + ", executionId=" + execution.getId()
            + " \n\n");
    
    LOGGER.warning("The value of workflowIds:"+execution.getVariable("workflowIds"));
    
    LOGGER.warning("The value of precustomisationWorkflowId:"+execution.getVariable("precustomisationWorkflowId")
            + ", customisationWorkflowId="+execution.getVariable("customisationWorkflowId")
            + ", postcustomisationWorkflowId="+execution.getVariable("postcustomisationWorkflowId"));
  }

}
