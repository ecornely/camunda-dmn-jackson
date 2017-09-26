package be.nrb.o2;

import java.io.Serializable;

public class Customisation implements Serializable {

  private String precustomisationWorkflowId;
  private String customisationWorkflowId;
  private String postcustomisationWorkflowId;
  public String getPrecustomisationWorkflowId() {
    return precustomisationWorkflowId;
  }
  public void setPrecustomisationWorkflowId(String precustomisationWorkflowId) {
    this.precustomisationWorkflowId = precustomisationWorkflowId;
  }
  public String getCustomisationWorkflowId() {
    return customisationWorkflowId;
  }
  public void setCustomisationWorkflowId(String customisationWorkflowId) {
    this.customisationWorkflowId = customisationWorkflowId;
  }
  public String getPostcustomisationWorkflowId() {
    return postcustomisationWorkflowId;
  }
  public void setPostcustomisationWorkflowId(String postcustomisationWorkflowId) {
    this.postcustomisationWorkflowId = postcustomisationWorkflowId;
  }
  public Customisation(String precustomisationWorkflowId, String customisationWorkflowId, String postcustomisationWorkflowId) {
    super();
    this.precustomisationWorkflowId = precustomisationWorkflowId;
    this.customisationWorkflowId = customisationWorkflowId;
    this.postcustomisationWorkflowId = postcustomisationWorkflowId;
  }
  
  public Customisation() {
  }
  @Override
  public String toString() {
    return "Customisation [precustomisationWorkflowId=" + precustomisationWorkflowId + ", customisationWorkflowId=" + customisationWorkflowId + ", postcustomisationWorkflowId=" + postcustomisationWorkflowId + "]";
  }
  
  
}
