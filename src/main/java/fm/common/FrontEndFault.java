package fm.common;

/**
 * Created by haho on 10/05/2017.
 */
public class FrontEndFault extends FaultBase {
  private String dump;
  private String stack;
  private String context;

  public String getDump() {
    return dump;
  }

  public void setDump(String dump) {
    this.dump = dump;
  }

  public String getStack() {
    return stack;
  }

  public void setStack(String stack) {
    this.stack = stack;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }
}
