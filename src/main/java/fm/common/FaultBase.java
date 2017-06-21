package fm.common;

/**
 * Created by haho on 10/05/2017.
 */
public class FaultBase {
  /**
   * Fault code
   */
  private String faultCode;

  /**
   * Fault message
   */
  private String faultMessage;

  public FaultBase() {
  }

  public FaultBase(String faultCode, String faultMessage) {
    this.faultCode = faultCode;
    this.faultMessage = faultMessage;
  }

  public String getFaultCode() {
    return faultCode;
  }

  public void setFaultCode(String faultCode) {
    this.faultCode = faultCode;
  }

  public String getFaultMessage() {
    return faultMessage;
  }

  public void setFaultMessage(String faultMessage) {
    this.faultMessage = faultMessage;
  }

}