package fm.common;

/**
 * Created by haho on  10/05/2017.
 */
public class ValidationException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private String faultCode;
  private Object[] context;

  public ValidationException() {
    super();
  }

  public ValidationException(String faultCode, Object[] context) {
    super(faultCode);
    this.faultCode = faultCode;
    this.context = context;
  }

  public ValidationException(String faultCode) {
    super(faultCode);
    this.faultCode = faultCode;
  }

  public String getFaultCode() {
    return faultCode;
  }

  public void setFaultCode(String faultCode) {
    this.faultCode = faultCode;
  }

  public Object[] getContext() {
    return context;
  }

  public void setContext(Object[] context) {
    this.context = context;
  }
}
